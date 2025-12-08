package iuh.chillteam.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import iuh.chillteam.config.FAQConfig;
import iuh.chillteam.dto.ai.ChatRequest;
import iuh.chillteam.dto.ai.ChatResponse;
import iuh.chillteam.entity.Order;
import iuh.chillteam.entity.Product;
import iuh.chillteam.entity.ProductImage;
import iuh.chillteam.entity.User;
import iuh.chillteam.repository.OrderRepository;
import iuh.chillteam.repository.ProductImageRepository;
import iuh.chillteam.repository.ProductRepository;
import iuh.chillteam.repository.UserRepository;
import iuh.chillteam.service.ChatAIService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import iuh.chillteam.exception.AIServiceException;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.data.domain.PageRequest;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.retry.RetryUtils;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatAIServiceImpl implements ChatAIService {
    
    private final ChatClient.Builder chatClientBuilder;
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ObjectMapper objectMapper;
    private final FAQConfig faqConfig;
    
    private static final String SYSTEM_PROMPT = """
        Bạn là chuyên gia tư vấn kính mắt thời trang của ChillGlasses.
        
        🎯 NHIỆM VỤ CHÍNH:
        - Tư vấn kính phù hợp với khuôn mặt, tông da, phong cách sống
        - LUÔN LUÔN giới thiệu ĐÚNG 3 sản phẩm CỤ THỂ từ danh sách
        - Giải thích rõ ràng TẠI SAO mỗi sản phẩm phù hợp
        
        ✅ QUY TẮC BẮT BUỘC:
        1. PHẢI giới thiệu ĐÚNG 3 sản phẩm (không nhiều hơn, không ít hơn)
        2. PHẢI chèn [PRODUCT_ID:123] cho MỖI sản phẩm
        3. CHỈ dùng ID có trong danh sách được cung cấp
        4. KHÔNG tự tạo sản phẩm hoặc ID không tồn tại
        
        📝 CẤU TRÚC TRẢ LỜI CHUẨN:
        "[Lời mở đầu ngắn gọn về đặc điểm khách hàng]
        
        Tôi gợi ý 3 sản phẩm sau:
        
        1️⃣ [PRODUCT_ID:X] - [Tên] - [Lý do cụ thể phù hợp]
        2️⃣ [PRODUCT_ID:Y] - [Tên] - [Lý do cụ thể phù hợp]  
        3️⃣ [PRODUCT_ID:Z] - [Tên] - [Lý do cụ thể phù hợp]
        
        [Lời khuyên thêm nếu cần]"
        
        ⚠️ XỬ LÝ CÂU HỎI NGOÀI LỀ:
        - Nếu hỏi về chính sách/liên hệ: "Bạn có thể xem thông tin chi tiết tại trang Giới thiệu hoặc Liên hệ của chúng tôi."
        - Nếu hỏi hoàn toàn không liên quan: "Tôi chỉ có thể tư vấn về kính mắt. Bạn có câu hỏi gì về sản phẩm kính không?"
        
        💡 LƯU Ý:
        - Frontend sẽ chuyển [PRODUCT_ID:123] thành link click được
        - Luôn thân thiện, chuyên nghiệp, cụ thể
        - Tập trung vào lợi ích thực tế cho khách hàng
        """;
    
    @Override
    @Retryable(
        retryFor = {ResourceAccessException.class, HttpServerErrorException.class},
        maxAttempts = 3,
        backoff = @Backoff(delay = 1000, multiplier = 2)
    )
    public ChatResponse chat(ChatRequest request, Long userId) {
        log.info("Processing chat request from user: {}", userId);
        
        // Step 1: Check for FAQ/basic greetings - no API call needed
        ChatResponse faqResponse = handleFAQ(request.getMessage());
        if (faqResponse != null) {
            log.info("Handled by FAQ - no API call");
            return faqResponse;
        }
        
        try {
            // Step 2: Build context with user orders if authenticated
            String enhancedPrompt = buildEnhancedPrompt(request, userId);
            
            // Step 3: Call OpenAI API
            ChatClient chatClient = chatClientBuilder.build();
            String aiResponse = chatClient.prompt()
                .system(SYSTEM_PROMPT)
                .user(enhancedPrompt)
                .call()
                .content();
            
            log.info("OpenAI response received successfully");
            
            // Step 4: Extract 3 product suggestions from AI response
            List<ChatResponse.ProductSuggestion> suggestions = extractProductSuggestionsFromText(aiResponse);
            
            return ChatResponse.builder()
                .message(aiResponse)
                .timestamp(LocalDateTime.now())
                .suggestedProducts(suggestions)
                .responseType("text")
                .build();
                
        } catch (HttpClientErrorException.Unauthorized e) {
            log.error("OpenAI API key is invalid: {}", e.getMessage());
            throw new AIServiceException.OpenAIInvalidKeyException("API key không hợp lệ. Vui lòng kiểm tra cấu hình.");
        } catch (HttpClientErrorException.TooManyRequests e) {
            log.error("OpenAI rate limit exceeded: {}", e.getMessage());
            throw new AIServiceException.OpenAIRateLimitException("Đã vượt quá giới hạn số lượng request. Vui lòng thử lại sau.");
        } catch (HttpClientErrorException e) {
            if (e.getMessage().contains("insufficient_quota")) {
                log.error("OpenAI quota exceeded: {}", e.getMessage());
                throw new AIServiceException.OpenAIQuotaExceededException("Đã hết quota OpenAI. Vui lòng nạp thêm credits.");
            }
            log.error("OpenAI client error: {}", e.getMessage(), e);
            throw new AIServiceException("Lỗi khi gọi OpenAI API: " + e.getMessage(), e);
        } catch (ResourceAccessException e) {
            log.error("OpenAI timeout/network error: {}", e.getMessage());
            throw new AIServiceException.OpenAITimeoutException("Timeout khi gọi OpenAI. Vui lòng thử lại.");
        } catch (Exception e) {
            log.error("Unexpected error processing chat request: {}", e.getMessage(), e);
            log.error("Error type: {}", e.getClass().getName());
            if (e.getCause() != null) {
                log.error("Caused by: {}", e.getCause().getMessage());
            }
            return ChatResponse.builder()
                .message("Xin lỗi, tôi đang gặp sự cố kỹ thuật. Vui lòng thử lại sau.")
                .timestamp(LocalDateTime.now())
                .responseType("error")
                .build();
        }
    }
    
    /**
     * Handle FAQ and basic greetings without calling OpenAI API
     * Returns null if not a FAQ question
     */
    private ChatResponse handleFAQ(String message) {
        // Use FAQConfig to find matching FAQ
        FAQConfig.FAQResponse faqResponse = faqConfig.findMatchingFAQ(message);
        
        if (faqResponse != null) {
            log.info("FAQ matched for message: {}", message.substring(0, Math.min(30, message.length())));
            return ChatResponse.builder()
                    .message(faqResponse.getMessage())
                    .timestamp(LocalDateTime.now())
                    .suggestedProducts(Collections.emptyList())
                    .responseType(faqResponse.getResponseType())
                    .build();
        }
        
        return null; // Not a FAQ, need to call API
    }
    
    /**
     * Build enhanced prompt with user context and order history
     */
    private String buildEnhancedPrompt(ChatRequest request, Long userId) {
        StringBuilder prompt = new StringBuilder();
        
        // Add user context and order history if authenticated
        if (userId != null) {
            userRepository.findById(userId).ifPresent(user -> {
                prompt.append("👤 Thông tin khách hàng: ");
                prompt.append("Tên: ").append(user.getFullName());
                
                // Add order history for personalization
                try {
                    List<Order> recentOrders = orderRepository.findTop3ByUserIdOrderByCreatedAtDesc(userId, 
                        PageRequest.of(0, 3));
                    if (!recentOrders.isEmpty()) {
                        prompt.append("\n\n📦 Lịch sử mua hàng gần đây:");
                        for (Order order : recentOrders) {
                            prompt.append("\n- Đơn hàng ").append(order.getOrderCode())
                                  .append(" (").append(order.getStatus()).append(")");
                            // You could add order items here if needed for better context
                        }
                        prompt.append("\n\n💡 Khách hàng này đã mua hàng, hãy tư vấn dựa trên trải nghiệm của họ!");
                    }
                } catch (Exception e) {
                    log.warn("Failed to load order history for user {}", userId, e);
                }
                
                prompt.append("\n");
            });
        }
        
        // Add context data
        if (request.getContextData() != null && !request.getContextData().isEmpty()) {
            try {
                Map<String, Object> contextMap = objectMapper.readValue(
                    request.getContextData(), 
                    Map.class
                );
                prompt.append("Đặc điểm: ");
                contextMap.forEach((key, value) -> 
                    prompt.append(key).append(": ").append(value).append(", ")
                );
            } catch (Exception e) {
                log.warn("Failed to parse context data", e);
            }
        }
        
        // Add main message
        prompt.append("\n\n📝 Câu hỏi của khách hàng: ").append(request.getMessage());
        
        // ALWAYS include product list for better recommendations
        List<Product> availableProducts = productRepository.findTop10ByActiveTrue();
        if (!availableProducts.isEmpty()) {
            prompt.append("\n\n🏪 === DANH SÁCH SẢN PHẨM HIỆN CÓ TRONG CỬA HÀNG ===\n");
            prompt.append("⚠️ CHỈ được giới thiệu các sản phẩm sau (PHẢI chèn PRODUCT_ID vào câu trả lời):\n\n");
            
            int index = 1;
            for (Product p : availableProducts) {
                prompt.append(String.format("%d. ID: %d | Tên: %s | Loại: %s | Giá: %,.0f VNĐ", 
                    index++, p.getId(), p.getName(), p.getCategory().getName(), p.getPrice()));
                
                if (p.getDescription() != null && !p.getDescription().isEmpty()) {
                    String desc = p.getDescription().length() > 150 
                        ? p.getDescription().substring(0, 150) + "..." 
                        : p.getDescription();
                    prompt.append("\n   Đặc điểm: ").append(desc);
                }
                prompt.append("\n\n");
            }
            
            prompt.append("📌 HƯỚNG DẪN:\n");
            prompt.append("- BẮT BUỘC giới thiệu ĐÚNG 3 sản phẩm từ danh sách trên\n");
            prompt.append("- Chèn ID theo format: [PRODUCT_ID:123] ngay trong câu giới thiệu\n");
            prompt.append("- VÍ DỤ: \"Tôi gợi ý [PRODUCT_ID:5] - đây là lựa chọn hoàn hảo vì...\"\n");
            prompt.append("- Giải thích CỤ THỂ tại sao sản phẩm đó phù hợp với yêu cầu của khách\n");
            prompt.append("- DEFAULT: Luôn gợi ý 3 sản phẩm phù hợp nhất với câu hỏi\n");
        } else {
            prompt.append("\n\n⚠️ Hiện không có sản phẩm nào. Chỉ đưa ra lời khuyên chung về phong cách kính mắt.");
        }
        
        return prompt.toString();
    }
    
    /**
     * Extract exactly 3 product suggestions from [PRODUCT_ID:X] in AI response
     * Frontend will display these as product cards
     */
    private List<ChatResponse.ProductSuggestion> extractProductSuggestionsFromText(String aiResponse) {
        List<ChatResponse.ProductSuggestion> suggestions = new ArrayList<>();
        
        try {
            // Extract all [PRODUCT_ID:X] from AI response using regex
            java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("\\[PRODUCT_ID:(\\d+)\\]");
            java.util.regex.Matcher matcher = pattern.matcher(aiResponse);
            
            java.util.Set<Long> productIds = new java.util.LinkedHashSet<>(); // Use Set to avoid duplicates
            while (matcher.find() && productIds.size() < 3) {
                productIds.add(Long.parseLong(matcher.group(1)));
            }
            
            // Get product details for each ID
            for (Long productId : productIds) {
                productRepository.findById(productId).ifPresent(product -> {
                    if (Boolean.TRUE.equals(product.getIsActive()) && product.getDeletedAt() == null) {
                        String imageUrl = productImageRepository.findPrimaryImageByProductId(product.getId())
                            .map(ProductImage::getImageUrl)
                            .orElse(null);
                        
                        String productUrl = "/products/" + (product.getSlug() != null ? product.getSlug() : product.getId());
                        
                        suggestions.add(ChatResponse.ProductSuggestion.builder()
                            .productId(product.getId())
                            .productName(product.getName())
                            .productSlug(product.getSlug())
                            .productUrl(productUrl)
                            .imageUrl(imageUrl)
                            .price(product.getPrice())
                            .reason("Được AI gợi ý phù hợp với nhu cầu của bạn")
                            .build());
                    }
                });
            }
            
            log.info("Extracted {} product suggestions from AI response", suggestions.size());
            
        } catch (Exception e) {
            log.error("Error extracting product suggestions from text", e);
        }
        
        return suggestions;
    }
}
