package iuh.chillteam.exception;

/**
 * Order not found exception
 */
public class OrderNotFoundException extends ResourceNotFoundException {
    public OrderNotFoundException(Long id) {
        super("Order", "id", id);
    }

    public OrderNotFoundException(String orderCode) {
        super("Order", "orderCode", orderCode);
    }
}