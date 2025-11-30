package iuh.chillteam.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Chuẩn format response cho toàn bộ API
 *
 * Success response:
 * {
 *   "code": 200,
 *   "message": "Success",
 *   "data": {...},
 *   "timestamp": "2025-01-20T10:30:00"
 * }
 *
 * Error response:
 * {
 *   "code": 400,
 *   "message": "Validation failed",
 *   "errors": ["Email is required", "Password must be at least 8 characters"],
 *   "timestamp": "2025-01-20T10:30:00"
 * }
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    /**
     * HTTP status code
     */
    private int code;

    /**
     * Message mô tả kết quả
     */
    private String message;

    /**
     * Data trả về (nếu success)
     */
    private T data;

    /**
     * Errors chi tiết (nếu failed)
     */
    private List<String> errors;

    /**
     * Timestamp
     */
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

    /**
     * Success response với data
     */
    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .code(200)
                .message("Success")
                .data(data)
                .build();
    }

    /**
     * Success response với custom message
     */
    public static <T> ApiResponse<T> success(String message, T data) {
        return ApiResponse.<T>builder()
                .code(200)
                .message(message)
                .data(data)
                .build();
    }

    /**
     * Success response không có data
     */
    public static <T> ApiResponse<T> success(String message) {
        return ApiResponse.<T>builder()
                .code(200)
                .message(message)
                .build();
    }

    /**
     * Error response
     */
    public static <T> ApiResponse<T> error(int code, String message) {
        return ApiResponse.<T>builder()
                .code(code)
                .message(message)
                .build();
    }

    /**
     * Error response với errors chi tiết
     */
    public static <T> ApiResponse<T> error(int code, String message, List<String> errors) {
        return ApiResponse.<T>builder()
                .code(code)
                .message(message)
                .errors(errors)
                .build();
    }
}