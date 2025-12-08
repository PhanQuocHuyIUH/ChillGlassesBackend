# Chat AI Feature - Setup Guide

## Tổng quan

Chức năng Chat AI sử dụng OpenAI GPT-4o mini để tư vấn kính mắt thời trang cho khách hàng.

## Cài đặt

### 1. Thêm OpenAI API Key vào `.env`

```env
OPENAI_API_KEY=sk-proj-xxxxxxxxxxxxxxxxxxxxx
```

Lấy API key tại: https://platform.openai.com/api-keys

### 2. Cấu hình đã có sẵn trong `application.properties`

```properties
spring.ai.openai.api-key=${OPENAI_API_KEY}
spring.ai.openai.chat.options.model=gpt-4o-mini
spring.ai.openai.chat.options.temperature=0.7
spring.ai.openai.chat.options.max-tokens=800
```

### 3. Dependencies đã có trong `pom.xml`

```xml
<dependency>
    <groupId>org.springframework.ai</groupId>
    <artifactId>spring-ai-starter-model-openai</artifactId>
</dependency>
```

## API Endpoints

### 1. Chat với AI (Authenticated)

```http
POST /api/chat-ai/chat
Content-Type: application/json
Authorization: Bearer {token}

{
  "message": "Tôi muốn tìm kính gọng tròn phù hợp với mặt vuông",
  "contextType": "product_recommendation",
  "contextData": "{\"faceShape\": \"square\", \"skinTone\": \"fair\"}"
}
```

### 2. Stream Chat Response

```http
POST /api/chat-ai/stream
Content-Type: application/json
Authorization: Bearer {token}

{
  "message": "Tư vấn kính cho tôi"
}
```

### 3. Guest Chat (No Authentication)

```http
POST /api/chat-ai/guest-chat
Content-Type: application/json

{
  "message": "Kính gọng tròn phù hợp với khuôn mặt nào?"
}
```

### 4. Product Recommendations

```http
POST /api/chat-ai/recommendations?preferences=modern style, round frames
Authorization: Bearer {token}
```

### 5. Style Advice

```http
POST /api/chat-ai/style-advice?faceShape=oval&skinTone=fair
Authorization: Bearer {token}
```

## Response Format

```json
{
  "success": true,
  "message": "Success",
  "data": {
    "message": "Dựa trên đặc điểm khuôn mặt vuông của bạn...",
    "timestamp": "2024-12-08T10:30:00",
    "suggestedProducts": [
      {
        "productId": 1,
        "productName": "Kính Gọng Tròn Classic",
        "imageUrl": "https://...",
        "price": 450000,
        "reason": "Phù hợp với mặt vuông vì..."
      }
    ],
    "responseType": "text"
  }
}
```

## Context Types

- `product_recommendation`: Tư vấn sản phẩm cụ thể
- `style_advice`: Tư vấn phong cách
- `general_chat`: Chat chung

## Tính năng

1. ✅ Tư vấn kính dựa trên dáng mặt
2. ✅ Tư vấn kính dựa trên tông da
3. ✅ Gợi ý sản phẩm có sẵn trong cửa hàng
4. ✅ Stream response real-time
5. ✅ Support cả authenticated và guest users
6. ✅ Context-aware (biết thông tin user nếu đã đăng nhập)

## Testing

### Test với cURL

```bash
# Guest chat
curl -X POST http://localhost:8080/api/chat-ai/guest-chat \
  -H "Content-Type: application/json" \
  -d '{"message": "Tư vấn kính cho mặt tròn"}'

# Authenticated chat
curl -X POST http://localhost:8080/api/chat-ai/chat \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_TOKEN" \
  -d '{"message": "Tôi muốn tìm kính phù hợp", "contextType": "product_recommendation"}'
```

### Test với Swagger

Truy cập: http://localhost:8080/swagger-ui.html
Tìm section "Chat AI" để test các endpoints

## Chi phí OpenAI

Model `gpt-4o-mini` rất tiết kiệm:

- Input: $0.15 / 1M tokens
- Output: $0.60 / 1M tokens

Ước tính: ~$0.0001 per chat message

## Lưu ý

1. Cần có OPENAI_API_KEY hợp lệ
2. Kiểm tra quota của OpenAI account
3. Có thể thay đổi model trong `application.properties`
4. Temperature cao hơn = creative hơn (0.0 - 1.0)
5. Max tokens giới hạn độ dài response

## Troubleshooting

**Lỗi: "401 Unauthorized"**

- Kiểm tra OPENAI_API_KEY có đúng không
- Kiểm tra API key còn active không

**Lỗi: "429 Too Many Requests"**

- Đã vượt quota/rate limit
- Chờ hoặc upgrade plan OpenAI

**Response chậm**

- Network latency đến OpenAI servers
- Có thể cache response cho câu hỏi phổ biến
- Sử dụng streaming endpoint cho UX tốt hơn
