package iuh.chillteam.service;

import iuh.chillteam.dto.request.CreateProductRequest;
import iuh.chillteam.dto.request.ProductFilterRequest;
import iuh.chillteam.dto.request.UpdateProductRequest;
import iuh.chillteam.dto.response.PageResponse;
import iuh.chillteam.dto.response.ProductDetailDTO;
import iuh.chillteam.dto.response.ProductListDTO;
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
