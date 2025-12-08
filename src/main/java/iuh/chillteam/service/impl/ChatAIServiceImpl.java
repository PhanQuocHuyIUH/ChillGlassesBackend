package iuh.chillteam.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import iuh.chillteam.dto.ai.ChatRequest;
import iuh.chillteam.dto.ai.ChatResponse;
import iuh.chillteam.entity.Product;
import iuh.chillteam.entity.ProductImage;
import iuh.chillteam.entity.User;
import iuh.chillteam.repository.ProductImageRepository;
import iuh.chillteam.repository.ProductRepository;
import iuh.chillteam.repository.UserRepository;
import iuh.chillteam.service.ChatAIService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import iuh.chillteam.exception.AIServiceException;
import org.springframework.ai.chat.client.ChatClient;
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
    private final ObjectMapper objectMapper;
    
    private static final String SYSTEM_PROMPT = """
        Bạn là chuyên gia tư vấn kính mắt thời trang của ChillGlasses với kiến thức sâu về:
        - Phong cách kính phù hợp với từng dáng mặt (oval, tròn, vuông, trái xoan, dài)
        - Màu sắc và chất liệu gọng kính phù hợp với tông da
        - Xu hướng thời trang kính mắt hiện đại
        - Tư vấn dựa trên hoạt động và phong cách sống
        
        CÁC QUY TẮC QUAN TRỌNG:
        1. ✅ HÃY luôn giới thiệu 2-3 sản phẩm CỤ THỂ từ danh sách được cung cấp
        2. ✅ PHẢI chèn [PRODUCT_ID:123] vào trong câu giới thiệu sản phẩm
        3. ✅ Giải thích TẠI SAO sản phẩm đó phù hợp với yêu cầu của khách hàng
        4. ❌ TUYỆT ĐỐI KHÔNG tự tạo tên sản phẩm hoặc ID không có trong danh sách
        5. ❌ KHÔNG nói chung chung như "có nhiều loại kính" mà phải giới thiệu SẢN PHẨM CỤ THỂ
        
        CẤU TRÚC TRẢ LỜI MẪU:
        "Dựa trên [đặc điểm khách hàng], tôi gợi ý:
        
        1. [PRODUCT_ID:5] - [Tên sản phẩm] với gọng [đặc điểm] rất phù hợp vì [lý do cụ thể]
        2. [PRODUCT_ID:8] - [Tên sản phẩm] có thiết kế [đặc điểm] giúp [lợi ích cụ thể]
        3. [PRODUCT_ID:12] - [Tên sản phẩm] là lựa chọn tuyệt vời nếu bạn [tình huống]
        
        [Lời khuyên thêm về cách phối hợp hoặc chăm sóc]"
        
        LƯU Ý:
        - Chỉ tư vấn về kính mắt của cửa hàng
        - Nếu khách hỏi ngoài lề, lịch sự từ chối và hướng về sản phẩm kính
        - Frontend sẽ tự động chuyển [PRODUCT_ID:123] thành link có thể click
        - Luôn nhiệt tình, chuyên nghiệp và cụ thể
        """;
    
    @Override
    @Retryable(
        retryFor = {ResourceAccessException.class, HttpServerErrorException.class},
        maxAttempts = 3,
        backoff = @Backoff(delay = 1000, multiplier = 2)
    )
    public ChatResponse chat(ChatRequest request, Long userId) {
        log.info("Processing chat request from user: {} (attempt)", userId);
        
        try {
            // Build context based on request type
            String enhancedPrompt = buildEnhancedPrompt(request, userId);
            
            // Call OpenAI with retry
            ChatClient chatClient = chatClientBuilder.build();
            String aiResponse = chatClient.prompt()
                .system(SYSTEM_PROMPT)
                .user(enhancedPrompt)
                .call()
                .content();
            
            log.info("OpenAI response received successfully");
            
            // Parse response and get product suggestions if applicable
            List<ChatResponse.ProductSuggestion> suggestions = extractProductSuggestions(aiResponse, request);
            
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
    
    @Override
    public Flux<String> streamChat(ChatRequest request, Long userId) {
        log.info("Streaming chat request from user: {}", userId);
        
        try {
            String enhancedPrompt = buildEnhancedPrompt(request, userId);
            
            ChatClient chatClient = chatClientBuilder.build();
            return chatClient.prompt()
                .system(SYSTEM_PROMPT)
                .user(enhancedPrompt)
                .stream()
                .content();
                
        } catch (Exception e) {
            log.error("Error streaming chat", e);
            return Flux.just("Xin lỗi, tôi đang gặp sự cố kỹ thuật. Vui lòng thử lại sau.");
        }
    }
    
    @Override
    public ChatResponse getProductRecommendations(String preferences, Long userId) {
        ChatRequest request = ChatRequest.builder()
            .message("Tôi muốn tìm kính với đặc điểm: " + preferences)
            .contextType("product_recommendation")
            .build();
        
        return chat(request, userId);
    }
    
    @Override
    public ChatResponse getStyleAdvice(String faceShape, String skinTone, Long userId) {
        String message = String.format(
            "Tôi có khuôn mặt %s và tông da %s. Hãy tư vấn kiểu kính phù hợp cho tôi.",
            faceShape, skinTone
        );
        
        ChatRequest request = ChatRequest.builder()
            .message(message)
            .contextType("product_recommendation") // Changed to include product list
            .contextData(String.format("{\"faceShape\": \"%s\", \"skinTone\": \"%s\"}", faceShape, skinTone))
            .build();
        
        return chat(request, userId);
    }
    
    /**
     * Build enhanced prompt with user context
     */
    private String buildEnhancedPrompt(ChatRequest request, Long userId) {
        StringBuilder prompt = new StringBuilder();
        
        // Add user context if available
        if (userId != null) {
            userRepository.findById(userId).ifPresent(user -> {
                prompt.append("Thông tin khách hàng: ");
                prompt.append("Tên: ").append(user.getFullName()).append(". ");
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
            prompt.append("- BẮT BUỘC giới thiệu ÍT NHẤT 2 sản phẩm từ danh sách trên\n");
            prompt.append("- Chèn ID theo format: [PRODUCT_ID:123] ngay trong câu giới thiệu\n");
            prompt.append("- VÍ DỤ: \"Tôi gợi ý [PRODUCT_ID:5] - đây là lựa chọn hoàn hảo vì...\"\n");
            prompt.append("- Giải thích CỤ THỂ tại sao sản phẩm đó phù hợp với yêu cầu của khách\n");
        } else {
            prompt.append("\n\n⚠️ Hiện không có sản phẩm nào. Chỉ đưa ra lời khuyên chung về phong cách kính mắt.");
        }
        
        return prompt.toString();
    }
    
    /**
     * Extract product suggestions from AI response
     */
    private List<ChatResponse.ProductSuggestion> extractProductSuggestions(
            String aiResponse, 
            ChatRequest request) {
        
        List<ChatResponse.ProductSuggestion> suggestions = new ArrayList<>();
        
        if (!"product_recommendation".equals(request.getContextType())) {
            return suggestions;
        }
        
        try {
            // Get top 3 products based on AI response keywords
            List<Product> products = productRepository.findTop3ByActiveTrueOrderByCreatedAtDesc();
            
            for (Product product : products) {
                // Get primary image or first image
                String imageUrl = productImageRepository.findPrimaryImageByProductId(product.getId())
                    .map(ProductImage::getImageUrl)
                    .orElse(null);
                
                // Generate product URL using slug or ID
                String productUrl = "/products/" + (product.getSlug() != null ? product.getSlug() : product.getId());
                
                suggestions.add(ChatResponse.ProductSuggestion.builder()
                    .productId(product.getId())
                    .productName(product.getName())
                    .productSlug(product.getSlug())
                    .productUrl(productUrl)
                    .imageUrl(imageUrl)
                    .price(product.getPrice())
                    .reason("Phù hợp với phong cách bạn đang tìm kiếm")
                    .build());
            }
            
        } catch (Exception e) {
            log.error("Error extracting product suggestions", e);
        }
        
        return suggestions;
    }
}
