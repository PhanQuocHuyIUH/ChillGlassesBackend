package iuh.chillteam.service;

import iuh.chillteam.dto.common.PageResponse;
import iuh.chillteam.dto.product.CreateProductRequest;
import iuh.chillteam.dto.product.ProductFilterRequest;
import iuh.chillteam.dto.product.UpdateProductRequest;
import iuh.chillteam.dto.product.ProductDetailDTO;
import iuh.chillteam.dto.product.ProductListDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Product Service Interface
 */
public interface ProductService {

    /**
     * Get all products with pagination and filter
     */
    PageResponse<ProductListDTO> getAllProducts(ProductFilterRequest filter, Pageable pageable);

    /**
     * Get product detail by ID
     */
    ProductDetailDTO getProductById(Long id);

    /**
     * Get product detail by slug
     */
    ProductDetailDTO getProductBySlug(String slug);

    /**
     * Get products by category
     */
    List<ProductListDTO> getProductsByCategory(Long categoryId);

    /**
     * Get products by brand
     */
    List<ProductListDTO> getProductsByBrand(String brand);

    /**
     * Search products by keyword
     */
    PageResponse<ProductListDTO> searchProducts(String keyword, Pageable pageable);

    /**
     * Create new product
     */
    ProductDetailDTO createProduct(CreateProductRequest request);

    /**
     * Update product
     */
    ProductDetailDTO updateProduct(Long id, UpdateProductRequest request);

    /**
     * Delete product (soft delete)
     */
    void deleteProduct(Long id);
}
