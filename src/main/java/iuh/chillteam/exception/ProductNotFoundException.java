package iuh.chillteam.exception;

/**
 * Product not found exception
 */
public class ProductNotFoundException extends ResourceNotFoundException {
    public ProductNotFoundException(Long id) {
        super("Product", "id", id);
    }

    public ProductNotFoundException(String slug) {
        super("Product", "slug", slug);
    }
}