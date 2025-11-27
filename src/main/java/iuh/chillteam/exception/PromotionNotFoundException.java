package iuh.chillteam.exception;

/**
 * Promotion not found exception
 */
public class PromotionNotFoundException extends ResourceNotFoundException {
    public PromotionNotFoundException(String code) {
        super("Promotion", "code", code);
    }

    public PromotionNotFoundException(Long id) {
        super("Promotion", "id", id);
    }
}