package iuh.chillteam.exception;

/**
 * Invalid quantity exception
 */
public class InvalidQuantityException extends BadRequestException {
    public InvalidQuantityException(String message) {
        super(message);
    }

    public InvalidQuantityException(int quantity) {
        super(String.format("Invalid quantity: %d. Quantity must be greater than 0", quantity));
    }
}