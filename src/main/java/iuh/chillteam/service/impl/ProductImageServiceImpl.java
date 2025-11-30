package iuh.chillteam.service.impl;

import iuh.chillteam.dto.response.ProductImageDTO;
import iuh.chillteam.entity.Product;
import iuh.chillteam.entity.ProductImage;
import iuh.chillteam.exception.BadRequestException;
import iuh.chillteam.exception.ResourceNotFoundException;
import iuh.chillteam.repository.ProductImageRepository;
import iuh.chillteam.repository.ProductRepository;
import iuh.chillteam.service.ProductImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ProductImage Service Implementation
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductImageServiceImpl implements ProductImageService {

    private final ProductImageRepository productImageRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProductImageDTO> getProductImages(Long productId) {
        log.info("Getting images for product: {}", productId);
        List<ProductImage> images = productImageRepository.findByProductId(productId);
        return images.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductImageDTO getPrimaryImage(Long productId) {
        log.info("Getting primary image for product: {}", productId);
        ProductImage image = productImageRepository.findPrimaryImageByProductId(productId)
                .orElseThrow(() -> new ResourceNotFoundException("No primary image found for product: " + productId));
        return convertToDTO(image);
    }

    @Override
    public ProductImageDTO addImageToProduct(Long productId, String imageUrl, String altText, Boolean isPrimary, Integer displayOrder) {
        log.info("Adding image to product: {}", productId);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));

        if (product.getDeletedAt() != null) {
            throw new BadRequestException("Cannot add image to deleted product");
        }

        // If this image should be primary, unset other primary images
        if (isPrimary != null && isPrimary) {
            productImageRepository.findPrimaryImageByProductId(productId)
                    .ifPresent(img -> {
                        img.setIsPrimary(false);
                        productImageRepository.save(img);
                    });
        }

        ProductImage image = ProductImage.builder()
                .product(product)
                .imageUrl(imageUrl)
                .altText(altText)
                .isPrimary(isPrimary != null ? isPrimary : false)
                .displayOrder(displayOrder != null ? displayOrder : 0)
                .build();

        image = productImageRepository.save(image);
        log.info("Image added successfully with id: {}", image.getId());

        return convertToDTO(image);
    }

    @Override
    public void setPrimaryImage(Long imageId) {
        log.info("Setting image as primary: {}", imageId);

        ProductImage image = productImageRepository.findById(imageId)
                .orElseThrow(() -> new ResourceNotFoundException("Image not found with id: " + imageId));

        if (image.getDeletedAt() != null) {
            throw new BadRequestException("Cannot set deleted image as primary");
        }

        // Unset other primary images for this product
        Long productId = image.getProduct().getId();
        productImageRepository.findPrimaryImageByProductId(productId)
                .ifPresent(img -> {
                    if (!img.getId().equals(imageId)) {
                        img.setIsPrimary(false);
                        productImageRepository.save(img);
                    }
                });

        image.setIsPrimary(true);
        productImageRepository.save(image);
        log.info("Image set as primary successfully");
    }

    @Override
    public void updateDisplayOrder(Long imageId, Integer displayOrder) {
        log.info("Updating display order for image: {}", imageId);

        ProductImage image = productImageRepository.findById(imageId)
                .orElseThrow(() -> new ResourceNotFoundException("Image not found with id: " + imageId));

        if (image.getDeletedAt() != null) {
            throw new BadRequestException("Cannot update deleted image");
        }

        image.setDisplayOrder(displayOrder);
        productImageRepository.save(image);
        log.info("Display order updated successfully");
    }

    @Override
    public void deleteImage(Long imageId) {
        log.info("Deleting image: {}", imageId);

        ProductImage image = productImageRepository.findById(imageId)
                .orElseThrow(() -> new ResourceNotFoundException("Image not found with id: " + imageId));

        if (image.getDeletedAt() != null) {
            throw new BadRequestException("Image already deleted");
        }

        image.softDelete();
        productImageRepository.save(image);
        log.info("Image deleted successfully");
    }

    private ProductImageDTO convertToDTO(ProductImage image) {
        return ProductImageDTO.builder()
                .id(image.getId())
                .imageUrl(image.getImageUrl())
                .altText(image.getAltText())
                .isPrimary(image.getIsPrimary())
                .displayOrder(image.getDisplayOrder())
                .build();
    }
}
