package iuh.chillteam.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import iuh.chillteam.dto.ai.ChatRequest;
import iuh.chillteam.dto.ai.ChatResponse;
import iuh.chillteam.dto.common.ApiResponse;
import iuh.chillteam.security.UserDetailsServiceImpl;
import iuh.chillteam.service.ChatAIService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@Slf4j
@RestController
@RequestMapping("/api/chat-ai")
@RequiredArgsConstructor
@Tag(name = "Chat AI", description = "AI chatbot endpoints for product recommendations and style advice")
public class ChatAIController {
    
    private final ChatAIService chatAIService;
    
    /**
     * Send message to AI chatbot
     * POST /api/chat-ai/chat
     */
    @PostMapping("/chat")
    @Operation(
        summary = "Chat with AI assistant",
        description = "Send a message to AI chatbot and get recommendations"
    )
    public ResponseEntity<ApiResponse<ChatResponse>> chat(
            @Valid @RequestBody ChatRequest request,
            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUserDetails userDetails) {
        
        log.info("Chat request from user: {}", userDetails != null ? userDetails.getUserId() : "guest");
        
        Long userId = userDetails != null ? userDetails.getUserId() : null;
        ChatResponse response = chatAIService.chat(request, userId);
        
        return ResponseEntity.ok(ApiResponse.success(response));
    }
    
    /**
     * Stream chat response in real-time
     * POST /api/chat-ai/stream
     */
    @PostMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @Operation(
        summary = "Stream chat response",
        description = "Get real-time streaming response from AI chatbot"
    )
    public Flux<String> streamChat(
            @Valid @RequestBody ChatRequest request,
            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUserDetails userDetails) {
        
        log.info("Stream chat request from user: {}", userDetails != null ? userDetails.getUserId() : "guest");
        
        Long userId = userDetails != null ? userDetails.getUserId() : null;
        return chatAIService.streamChat(request, userId);
    }
    
    /**
     * Get product recommendations
     * POST /api/chat-ai/recommendations
     */
    @PostMapping("/recommendations")
    @Operation(
        summary = "Get product recommendations",
        description = "Get AI-powered product recommendations based on user preferences"
    )
    public ResponseEntity<ApiResponse<ChatResponse>> getRecommendations(
            @RequestParam String preferences,
            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUserDetails userDetails) {
        
        log.info("Product recommendation request: {}", preferences);
        
        Long userId = userDetails != null ? userDetails.getUserId() : null;
        ChatResponse response = chatAIService.getProductRecommendations(preferences, userId);
        
        return ResponseEntity.ok(ApiResponse.success(response));
    }
    
    /**
     * Get style advice based on face shape and skin tone
     * POST /api/chat-ai/style-advice
     */
    @PostMapping("/style-advice")
    @Operation(
        summary = "Get style advice",
        description = "Get personalized style advice based on face shape and skin tone"
    )
    public ResponseEntity<ApiResponse<ChatResponse>> getStyleAdvice(
            @RequestParam String faceShape,
            @RequestParam String skinTone,
            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUserDetails userDetails) {
        
        log.info("Style advice request - Face: {}, Skin: {}", faceShape, skinTone);
        
        Long userId = userDetails != null ? userDetails.getUserId() : null;
        ChatResponse response = chatAIService.getStyleAdvice(faceShape, skinTone, userId);
        
        return ResponseEntity.ok(ApiResponse.success(response));
    }
    
    /**
     * Public endpoint - no authentication required
     * For guest users to try AI chatbot
     */
    @PostMapping("/guest-chat")
    @Operation(
        summary = "Guest chat (no auth required)",
        description = "Allow guest users to chat with AI without authentication"
    )
    public ResponseEntity<ApiResponse<ChatResponse>> guestChat(
            @Valid @RequestBody ChatRequest request) {
        
        log.info("Guest chat request");
        
        ChatResponse response = chatAIService.chat(request, null);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
