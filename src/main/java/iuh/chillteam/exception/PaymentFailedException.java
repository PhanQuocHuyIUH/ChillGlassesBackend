package iuh.chillteam.exception;

/**
 * Payment failed exception
 */
public class PaymentFailedException extends BadRequestException {
    public PaymentFailedException(String message) {
        super(message);
    }

    public PaymentFailedException(String message, Throwable cause) {
        super(message);
    }
}