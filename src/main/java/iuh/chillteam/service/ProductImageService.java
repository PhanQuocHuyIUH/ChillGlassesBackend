package iuh.chillteam.service;

import iuh.chillteam.dto.response.ProductImageDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * ProductImage Service Interface
 */
public interface ProductImageService {

    /**
     * Get all images of a product
     */
    List<ProductImageDTO> getProductImages(Long productId);

    /**
     * Get primary image of a product
     */
    ProductImageDTO getPrimaryImage(Long productId);

    /**
     * Add image to product (manual URL - no upload)
     */
    ProductImageDTO addImageToProduct(Long productId, String imageUrl, String altText, Boolean isPrimary, Integer displayOrder);

    /**
     * Set image as primary
     */
    void setPrimaryImage(Long imageId);

    /**
     * Update image display order
     */
    void updateDisplayOrder(Long imageId, Integer displayOrder);

    /**
     * Delete image
     */
    void deleteImage(Long imageId);
}
