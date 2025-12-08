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
@Schema(description = "Chat AI request")
public class ChatRequest {
    
    @NotBlank(message = "Message is required")
    @Schema(description = "User message to AI", example = "Tôi muốn tìm kính gọng tròn phù hợp với mặt vuông")
    private String message;
    
    @Schema(description = "Chat context type", example = "product_recommendation", 
            allowableValues = {"product_recommendation", "style_advice", "general_chat"})
    private String contextType;
    
    @Schema(description = "Additional context data (user preferences, face shape, etc.)", 
            example = "{\"faceShape\": \"square\", \"skinTone\": \"fair\"}")
    private String contextData;
}
