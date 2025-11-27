package iuh.chillteam.exception;

/**
 * Base exception class cho custom exceptions
 */
public abstract class BaseException extends RuntimeException {
    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
