package iuh.chillteam.exception;

/**
 * Exception thrown when cart item is not found
 */
public class CartItemNotFoundException extends BaseException {

    public CartItemNotFoundException(String message) {
        super(message);
    }

    public CartItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
