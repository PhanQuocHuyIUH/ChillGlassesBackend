# Spring AI ChatBot - Cấu trúc và Best Practices

## ✅ Đã Implement

### 1. Core Components

- ✅ **ChatClient.Builder**: Fluent API để gọi OpenAI
- ✅ **ChatRequest/ChatResponse DTOs**: Structured I/O
- ✅ **System Prompt**: Vietnamese eyeglasses expert persona
- ✅ **Context Enhancement**: User info + product catalog injection
- ✅ **Product Suggestions**: Extract từ AI response
- ✅ **Guest Chat Support**: Public endpoint không cần auth

### 2. Configuration

- ✅ **SpringAIConfig**: Centralized AI configuration
  - OpenAI API client setup
  - Chat Model với custom options (temperature, max-tokens)
  - ChatMemory (InMemory) cho conversation history
  - ChatClient.Builder với Advisors support

### 3. Error Handling

- ✅ **AIServiceException**: Custom exception hierarchy
  - OpenAITimeoutException
  - OpenAIRateLimitException
  - OpenAIInvalidKeyException
  - OpenAIQuotaExceededException
- ✅ **GlobalExceptionHandler**: Centralized error handling với proper HTTP status codes
- ✅ **Detailed Logging**: Error type, cause, và context

### 4. Resilience

- ✅ **Spring Retry**: Auto retry cho timeout/network errors
  - Max 3 attempts
  - Exponential backoff (1s, 2s, 4s)
  - Retry chỉ cho recoverable errors

### 5. Observability

- ✅ **Spring Boot Actuator**: Health checks và metrics
- ✅ **Prometheus Integration**: Metrics export
- ✅ **Spring AI Observations**: Track input/output của AI calls
  - `/actuator/health` - Health check
  - `/actuator/metrics` - Application metrics
  - `/actuator/prometheus` - Prometheus format metrics

### 6. Dependencies

```xml
<dependency>
    <groupId>org.springframework.ai</groupId>
    <artifactId>spring-ai-openai-spring-boot-starter</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.retry</groupId>
    <artifactId>spring-retry</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-registry-prometheus</artifactId>
</dependency>
```

## 🔄 Có thể cải tiến thêm

### 1. Chat Memory (Production-ready)

**Hiện tại**: InMemoryChatMemory (mất khi restart)
**Nên**: Redis hoặc Database để persist conversation history

```java
@Bean
public ChatMemory chatMemory(RedisTemplate<String, String> redisTemplate) {
    return new RedisChatMemory(redisTemplate);
}
```

### 2. Function Calling / Tools

**Mục đích**: Cho AI access real-time data từ database

```java
@Bean
public FunctionCallback productSearchFunction() {
    return FunctionCallbackWrapper.builder(new ProductSearchFunction())
        .withName("search_products")
        .withDescription("Search products by filters")
        .build();
}
```

### 3. RAG (Retrieval Augmented Generation)

**Mục đích**: Tìm sản phẩm relevant từ Vector Database

```java
@Bean
public VectorStore vectorStore() {
    return new PgVectorStore(...); // hoặc Pinecone, Milvus
}

// Trong ChatClient
.advisors(new QuestionAnswerAdvisor(vectorStore))
```

### 4. Streaming Response

**Hiện tại**: Đã có method `streamChat()` nhưng chưa test kỹ
**Cải tiến**: Add SSE (Server-Sent Events) support ở frontend

### 5. Rate Limiting

**Bảo vệ**: Tránh abuse OpenAI API

```java
@RateLimiter(name = "chatAI", fallbackMethod = "chatRateLimitFallback")
public ChatResponse chat(...) { ... }
```

### 6. Caching

**Tối ưu**: Cache câu hỏi thường gặp (FAQ)

```java
@Cacheable(value = "aiResponses", key = "#request.message")
public ChatResponse chat(ChatRequest request, Long userId) { ... }
```

### 7. Content Moderation

**An toàn**: Filter inappropriate content

```java
.advisors(new SafeGuardAdvisor(...))
```

### 8. Multi-modal Support

**Mở rộng**: Image analysis (VTO - Virtual Try-On)

```java
// OpenAI Vision API
chatClient.prompt()
    .user(userMessage -> userMessage
        .text("Phân tích khuôn mặt")
        .media(new ClassPathResource("face-photo.jpg")))
    .call()
```

### 9. A/B Testing

**Thử nghiệm**: Test different prompts/models

```java
@ConditionalOnProperty("ai.experiment.enabled")
@Bean
public ChatClient experimentalChatClient() { ... }
```

### 10. Cost Tracking

**Monitoring**: Track OpenAI usage và cost

```java
@Aspect
public class AIUsageTracker {
    @Around("@annotation(Retryable)")
    public Object trackUsage(ProceedingJoinPoint pjp) {
        // Log tokens used, cost, latency
    }
}
```

## 📊 Architecture Diagram

```
Frontend (ChatBot.tsx)
    ↓
ChatAIController (REST API)
    ↓
ChatAIService (@Retryable)
    ↓
ChatClient.Builder
    ↓ [System Prompt + Context]
ChatClient → OpenAI API
    ↓ [Retry on failure]
ChatResponse ← AI Response
    ↓ [Extract products]
ProductSuggestions
```

## 🚀 Next Steps

1. **Test với OpenAI API key hợp lệ**
2. **Monitor metrics tại /actuator/prometheus**
3. **Implement Redis ChatMemory** cho production
4. **Thêm Function Calling** để search products real-time
5. **Setup Vector Database** cho RAG pattern
6. **Add rate limiting** với Resilience4j
7. **Implement caching** cho FAQ responses

## 📚 References

- [Spring AI Documentation](https://docs.spring.io/spring-ai/reference/index.html)
- [ChatClient API](https://docs.spring.io/spring-ai/reference/api/chatclient.html)
- [Observability](https://docs.spring.io/spring-ai/reference/observability/index.html)
- [Function Calling](https://docs.spring.io/spring-ai/reference/api/functions.html)
- [RAG Pattern](https://docs.spring.io/spring-ai/reference/api/chatclient.html#_retrieval_augmented_generation)
