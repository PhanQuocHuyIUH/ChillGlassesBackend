package iuh.chillteam.service;

import iuh.chillteam.dto.response.PageResponse;
import iuh.chillteam.entity.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * author: QUOC HUY
 * date: 07/11/2025
 */
public interface ProductService {

    /**
     * Get all products with pagination
     */
    PageResponse<Product> getAllProducts(Pageable pageable);

    /**
     * Get all products (no pagination)
     */
    List<Product> getAllProducts();

    /**
     * Get product by ID
     */
    Product getProductById(Long id);

    /**
     * Create new product
     */
    Product createProduct(Product product);

    /**
     * Update product
     */
    Product updateProduct(Long id, Product product);

    /**
     * Delete product (soft delete)
     */
    void deleteProduct(Long id);

    /**
     * Find products by price range
     */
    List<Product> getProductsByPriceRange(Double minPrice, Double maxPrice);
}
