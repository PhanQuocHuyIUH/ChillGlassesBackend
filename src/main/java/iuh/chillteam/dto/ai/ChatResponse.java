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
@Schema(description = "Chat AI response")
public class ChatResponse {
    
    @Schema(description = "AI response message")
    private String message;
    
    @Schema(description = "Response timestamp")
    private LocalDateTime timestamp;
    
    @Schema(description = "Suggested products (if applicable)")
    private List<ProductSuggestion> suggestedProducts;
    
    @Schema(description = "Response type", example = "text")
    private String responseType;
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductSuggestion {
        private Long productId;
        private String productName;
        private String productSlug;
        private String productUrl;
        private String imageUrl;
        private Double price;
        private String reason;
    }
}
