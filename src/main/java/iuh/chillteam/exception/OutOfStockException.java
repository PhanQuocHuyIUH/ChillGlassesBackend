package iuh.chillteam.exception;

/**
 * Out of stock exception
 */
public class OutOfStockException extends BadRequestException {
    public OutOfStockException(String productName) {
        super(String.format("Product '%s' is out of stock", productName));
    }

    public OutOfStockException(String productName, int available, int requested) {
        super(String.format("Product '%s' only has %d items available, but %d requested",
                productName, available, requested));
    }
}