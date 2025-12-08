package iuh.chillteam.dto.ai;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Chat AI response with product suggestions")
public class ChatResponse {
    
    @Schema(
        description = "AI chatbot response message. May contain [PRODUCT_ID:X] markers that frontend will convert to clickable links.",
        example = "Tôi gợi ý [PRODUCT_ID:5] - Kính gọng tròn đen phù hợp với khuôn mặt vuông của bạn..."
    )
    private String message;
    
    @Schema(description = "Response timestamp", example = "2025-12-09T10:30:00")
    private LocalDateTime timestamp;
    
    @Schema(
        description = "List of 3 suggested products with full details. Frontend will display these as product cards.",
        example = "[{\"productId\":5,\"productName\":\"Kính gọng tròn\",\"price\":500000}]"
    )
    private List<ProductSuggestion> suggestedProducts;
    
    @Schema(
        description = "Response type: 'text' for AI response, 'faq' for FAQ answers, 'error' for errors",
        example = "text",
        allowableValues = {"text", "faq", "error"}
    )
    private String responseType;
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "Product suggestion from AI")
    public static class ProductSuggestion {
        
        @Schema(description = "Product ID", example = "5")
        private Long productId;
        
        @Schema(description = "Product name", example = "Kính gọng tròn đen")
        private String productName;
        
        @Schema(description = "Product slug for SEO-friendly URL", example = "kinh-gong-tron-den")
        private String productSlug;
        
        @Schema(description = "Product URL path", example = "/products/kinh-gong-tron-den")
        private String productUrl;
        
        @Schema(description = "Product image URL", example = "https://res.cloudinary.com/...")
        private String imageUrl;
        
        @Schema(description = "Product price in VND", example = "500000.0")
        private Double price;
        
        @Schema(description = "Reason why AI recommends this product", example = "Phù hợp với khuôn mặt vuông")
        private String reason;
    }
}
