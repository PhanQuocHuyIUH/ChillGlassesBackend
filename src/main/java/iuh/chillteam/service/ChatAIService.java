package iuh.chillteam.service;

import iuh.chillteam.dto.ai.ChatRequest;
import iuh.chillteam.dto.ai.ChatResponse;
import reactor.core.publisher.Flux;

public interface ChatAIService {
    
    /**
     * Send message to AI and get response
     */
    ChatResponse chat(ChatRequest request, Long userId);
    
    /**
     * Stream chat response (for real-time streaming)
     */
    Flux<String> streamChat(ChatRequest request, Long userId);
    
    /**
     * Get product recommendations based on user preferences
     */
    ChatResponse getProductRecommendations(String preferences, Long userId);
    
    /**
     * Get style advice based on face shape and skin tone
     */
    ChatResponse getStyleAdvice(String faceShape, String skinTone, Long userId);
}
