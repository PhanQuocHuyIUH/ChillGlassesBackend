package iuh.chillteam.controller;

import iuh.chillteam.dto.response.ApiResponse;
import iuh.chillteam.dto.response.ProductImageDTO;
import iuh.chillteam.service.ProductImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ProductImage Controller
 */
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
public class ProductImageController {

    private final ProductImageService productImageService;

    @GetMapping("/{productId}/images")
    public ResponseEntity<ApiResponse<List<ProductImageDTO>>> getProductImages(@PathVariable Long productId) {
        log.info("GET /api/products/{}/images", productId);
        List<ProductImageDTO> images = productImageService.getProductImages(productId);
        return ResponseEntity.ok(ApiResponse.success(images));
    }

    @GetMapping("/{productId}/images/primary")
    public ResponseEntity<ApiResponse<ProductImageDTO>> getPrimaryImage(@PathVariable Long productId) {
        log.info("GET /api/products/{}/images/primary", productId);
        ProductImageDTO image = productImageService.getPrimaryImage(productId);
        return ResponseEntity.ok(ApiResponse.success(image));
    }

    @PostMapping("/{productId}/images")
    public ResponseEntity<ApiResponse<ProductImageDTO>> addImage(
            @PathVariable Long productId,
            @RequestParam String imageUrl,
            @RequestParam(required = false) String altText,
            @RequestParam(required = false) Boolean isPrimary,
            @RequestParam(required = false) Integer displayOrder) {

        log.info("POST /api/products/{}/images - URL: {}", productId, imageUrl);
        ProductImageDTO image = productImageService.addImageToProduct(productId, imageUrl, altText, isPrimary, displayOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Image added successfully", image));
    }

    @PutMapping("/images/{imageId}/set-primary")
    public ResponseEntity<ApiResponse<Void>> setPrimaryImage(@PathVariable Long imageId) {
        log.info("PUT /api/products/images/{}/set-primary", imageId);
        productImageService.setPrimaryImage(imageId);
        return ResponseEntity.ok(ApiResponse.success("Image set as primary successfully"));
    }

    @PutMapping("/images/{imageId}/display-order")
    public ResponseEntity<ApiResponse<Void>> updateDisplayOrder(
            @PathVariable Long imageId,
            @RequestParam Integer displayOrder) {

        log.info("PUT /api/products/images/{}/display-order - order: {}", imageId, displayOrder);
        productImageService.updateDisplayOrder(imageId, displayOrder);
        return ResponseEntity.ok(ApiResponse.success("Display order updated successfully"));
    }

    @DeleteMapping("/images/{imageId}")
    public ResponseEntity<ApiResponse<Void>> deleteImage(@PathVariable Long imageId) {
        log.info("DELETE /api/products/images/{}", imageId);
        productImageService.deleteImage(imageId);
        return ResponseEntity.ok(ApiResponse.success("Image deleted successfully"));
    }
}
