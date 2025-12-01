package iuh.chillteam.exception;

/**
 * Exception thrown when cart is empty
 */
public class EmptyCartException extends BaseException {

    public EmptyCartException(String message) {
        super(message);
    }

    public EmptyCartException(String message, Throwable cause) {
        super(message, cause);
    }
}
