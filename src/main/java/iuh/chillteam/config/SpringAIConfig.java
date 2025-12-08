package iuh.chillteam.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

/**
 * Spring AI Configuration
 * Sử dụng Spring Boot auto-configuration cho OpenAI
 * Các options được cấu hình trong application.properties:
 * - spring.ai.openai.api-key
 * - spring.ai.openai.chat.options.model
 * - spring.ai.openai.chat.options.temperature
 * - spring.ai.openai.chat.options.max-tokens
 */
@Configuration
@EnableRetry
public class SpringAIConfig {

    /**
     * ChatClient Builder
     * ChatModel sẽ được auto-configured bởi Spring Boot từ application.properties
     * 
     * Note: Có thể thêm Advisors sau nếu cần:
     * - MessageChatMemoryAdvisor (conversation history)
     * - QuestionAnswerAdvisor (RAG pattern)
     * - SafeGuardAdvisor (content moderation)
     */
    @Bean
    public ChatClient.Builder chatClientBuilder(ChatModel chatModel) {
        return ChatClient.builder(chatModel);
    }
}
