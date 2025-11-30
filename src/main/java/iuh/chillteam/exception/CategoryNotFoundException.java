package iuh.chillteam.exception;

/**
 * Category not found exception
 */
public class CategoryNotFoundException extends ResourceNotFoundException {
    public CategoryNotFoundException(Long id) {
        super("Category", "id", id);
    }

    public CategoryNotFoundException(String slug) {
        super("Category", "slug", slug);
    }
}