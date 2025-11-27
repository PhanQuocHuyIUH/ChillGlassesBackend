package iuh.chillteam.exception;

/**
 * Rate limit exceeded exception (429)
 */
public class RateLimitExceededException extends BaseException {
    public RateLimitExceededException() {
        super("Too many requests. Please try again later.");
    }

    public RateLimitExceededException(String message) {
        super(message);
    }
}