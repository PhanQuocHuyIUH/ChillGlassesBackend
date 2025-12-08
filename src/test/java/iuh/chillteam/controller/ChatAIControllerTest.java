package iuh.chillteam.controller;

import iuh.chillteam.dto.ai.ChatRequest;
import iuh.chillteam.dto.ai.ChatResponse;
import iuh.chillteam.service.ChatAIService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class ChatAIControllerTest {

    @Autowired
    private ChatAIController chatAIController;

    @MockBean
    private ChatAIService chatAIService;

    @Test
    void testGuestChat() {
        // Arrange
        ChatRequest request = ChatRequest.builder()
            .message("Tư vấn kính cho mặt tròn")
            .contextType("style_advice")
            .build();

        ChatResponse mockResponse = ChatResponse.builder()
            .message("Với khuôn mặt tròn, bạn nên chọn kính gọng vuông hoặc chữ nhật...")
            .timestamp(LocalDateTime.now())
            .responseType("text")
            .build();

        when(chatAIService.chat(any(ChatRequest.class), any()))
            .thenReturn(mockResponse);

        // Act
        var response = chatAIController.guestChat(request);

        // Assert
        assertNotNull(response);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
        assertEquals(200, response.getBody().getCode());
        assertNotNull(response.getBody().getData());
        assertEquals("text", response.getBody().getData().getResponseType());
    }

    @Test
    void testGetRecommendations() {
        // Arrange
        ChatResponse mockResponse = ChatResponse.builder()
            .message("Dựa trên phong cách hiện đại của bạn...")
            .timestamp(LocalDateTime.now())
            .responseType("text")
            .build();

        when(chatAIService.getProductRecommendations(any(String.class), anyLong()))
            .thenReturn(mockResponse);

        // Act
        var response = chatAIController.getRecommendations("modern style", null);

        // Assert
        assertNotNull(response);
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }
}
