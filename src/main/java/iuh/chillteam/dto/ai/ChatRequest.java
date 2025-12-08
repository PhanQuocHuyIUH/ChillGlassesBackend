package iuh.chillteam.dto.ai;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Chat AI request - Simple and smart")
public class ChatRequest {
    
    @NotBlank(message = "Message is required")
    @Schema(
        description = "User message to AI chatbot. Ask anything about eyeglasses!", 
        example = "Tôi muốn tìm kính gọng tròn phù hợp với mặt vuông",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String message;
    
    // Optional: Keep for backward compatibility, but no longer used
    @Schema(
        description = "Context type (deprecated - auto-detected by backend)", 
        example = "product_recommendation",
        deprecated = true
    )
    @Deprecated
    private String contextType;
    
    @Schema(
        description = "Additional context data (deprecated - handled by backend)", 
        example = "{\"faceShape\": \"square\"}",
        deprecated = true
    )
    @Deprecated
    private String contextData;
}
