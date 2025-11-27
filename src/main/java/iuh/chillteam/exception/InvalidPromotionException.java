package iuh.chillteam.exception;

/**
 * Invalid promotion exception
 */
public class InvalidPromotionException extends BadRequestException {
    public InvalidPromotionException(String message) {
        super(message);
    }
}