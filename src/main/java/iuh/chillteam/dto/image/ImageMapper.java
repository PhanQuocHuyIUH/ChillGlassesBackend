package iuh.chillteam.dto.image;

import iuh.chillteam.entity.ProductImage;

import java.util.List;
import java.util.Map;

/**
 * Image Mapper - Manual mapping
 */
public class ImageMapper {

    /**
     * Cloudinary upload response → ImageUploadDTO
     */
    public static ImageUploadDTO toUploadDTO(Map<String, Object> uploadResult) {
        if (uploadResult == null) {
            return null;
        }

        return ImageUploadDTO.builder()
                .imageUrl((String) uploadResult.get("secure_url"))
                .publicId((String) uploadResult.get("public_id"))
                .format((String) uploadResult.get("format"))
                .size(getLongValue(uploadResult.get("bytes")))
                .width(getIntegerValue(uploadResult.get("width")))
                .height(getIntegerValue(uploadResult.get("height")))
                .build();
    }

    /**
     * ProductImage Entity → ProductImageDTO
     */
    public static ProductImageDTO toProductImageDTO(ProductImage entity) {
        if (entity == null) {
            return null;
        }

        return ProductImageDTO.builder()
                .id(entity.getId())
                .imageUrl(entity.getImageUrl())
                .altText(entity.getAltText())
                .isPrimary(entity.getIsPrimary())
                .displayOrder(entity.getDisplayOrder())
                .thumbnailUrl(generateThumbnailUrl(entity.getImageUrl()))
                .build();
    }

    /**
     * List ProductImage → List ProductImageDTO
     */
    public static List<ProductImageDTO> toProductImageDTOList(List<ProductImage> entities) {
        if (entities == null) {
            return java.util.Collections.emptyList();
        }

        return entities.stream()
                .map(ImageMapper::toProductImageDTO)
                .collect(java.util.stream.Collectors.toList());
    }

    /**
     * Tạo thumbnail URL từ Cloudinary URL
     * Example: https://res.cloudinary.com/.../image.jpg
     * → https://res.cloudinary.com/.../c_thumb,w_200,h_200/image.jpg
     */
    private static String generateThumbnailUrl(String imageUrl) {
        if (imageUrl == null || !imageUrl.contains("/upload/")) {
            return imageUrl;
        }

        return imageUrl.replace("/upload/", "/upload/c_thumb,w_200,h_200/");
    }

    /**
     * Helper: Convert Object → Long
     */
    private static Long getLongValue(Object value) {
        if (value == null) return null;
        if (value instanceof Number) {
            return ((Number) value).longValue();
        }
        return null;
    }

    /**
     * Helper: Convert Object → Integer
     */
    private static Integer getIntegerValue(Object value) {
        if (value == null) return null;
        if (value instanceof Number) {
            return ((Number) value).intValue();
        }
        return null;
    }
}