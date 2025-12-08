package iuh.chillteam.service;

import iuh.chillteam.dto.ai.ChatRequest;
import iuh.chillteam.dto.ai.ChatResponse;

public interface ChatAIService {
    
    /**
     * Main chat endpoint - handles all chat interactions
     * - Checks for FAQ/basic greetings first
     * - Loads user context (orders) if authenticated
     * - Calls OpenAI API for complex queries
     * - Always returns 3 product suggestions when relevant
     */
    ChatResponse chat(ChatRequest request, Long userId);
}
