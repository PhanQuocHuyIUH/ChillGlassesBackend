package iuh.chillteam.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import iuh.chillteam.dto.ai.ChatRequest;
import iuh.chillteam.dto.ai.ChatResponse;
import iuh.chillteam.dto.common.ApiResponse;
import iuh.chillteam.security.UserDetailsServiceImpl;
import iuh.chillteam.service.ChatAIService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/chat-ai")
@RequiredArgsConstructor
@Tag(name = "Chat AI", description = "AI chatbot for product recommendations - Smart, simple, and personalized")
public class ChatAIController {
    
    private final ChatAIService chatAIService;
    
    /**
     * Universal Chat Endpoint
     * Handles all chat interactions:
     * - FAQ/greetings (no API call)
     * - Authenticated users (with order history)
     * - Guest users (without auth)
     * - Always returns 3 product suggestions when relevant
     * 
     * POST /api/chat-ai/chat
     */
    @PostMapping("/chat")
    @Operation(
        summary = "Chat with AI assistant",
        description = "Universal endpoint for all chat interactions. Supports both authenticated and guest users. " +
                      "Handles FAQ automatically and provides personalized recommendations with order history context."
    )
    public ResponseEntity<ApiResponse<ChatResponse>> chat(
            @Valid @RequestBody ChatRequest request,
            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUserDetails userDetails) {
        
        Long userId = userDetails != null ? userDetails.getUserId() : null;
        log.info("Chat request from {}: {}", 
                userId != null ? "user " + userId : "guest", 
                request.getMessage().substring(0, Math.min(50, request.getMessage().length())));
        
        ChatResponse response = chatAIService.chat(request, userId);
        
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
