package iuh.chillteam.exception;

/**
 * Custom exception cho AI Service errors
 */
public class AIServiceException extends RuntimeException {
    
    private final String errorCode;
    
    public AIServiceException(String message) {
        super(message);
        this.errorCode = "AI_ERROR";
    }
    
    public AIServiceException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
    
    public AIServiceException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = "AI_ERROR";
    }
    
    public AIServiceException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
    
    public String getErrorCode() {
        return errorCode;
    }
    
    // Specific error types
    public static class OpenAITimeoutException extends AIServiceException {
        public OpenAITimeoutException(String message) {
            super(message, "OPENAI_TIMEOUT");
        }
    }
    
    public static class OpenAIRateLimitException extends AIServiceException {
        public OpenAIRateLimitException(String message) {
            super(message, "OPENAI_RATE_LIMIT");
        }
    }
    
    public static class OpenAIInvalidKeyException extends AIServiceException {
        public OpenAIInvalidKeyException(String message) {
            super(message, "OPENAI_INVALID_KEY");
        }
    }
    
    public static class OpenAIQuotaExceededException extends AIServiceException {
        public OpenAIQuotaExceededException(String message) {
            super(message, "OPENAI_QUOTA_EXCEEDED");
        }
    }
}
