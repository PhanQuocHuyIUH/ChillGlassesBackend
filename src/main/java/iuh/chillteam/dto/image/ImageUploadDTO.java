package iuh.chillteam.dto.image;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Image Upload Response DTO - Kết quả sau khi upload lên Cloudinary
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImageUploadDTO {

    /**
     * URL đầy đủ của ảnh trên Cloudinary
     */
    private String imageUrl;

    /**
     * Public ID để xóa ảnh sau này
     */
    private String publicId;

    /**
     * Format ảnh (jpg, png, webp, ...)
     */
    private String format;

    /**
     * Kích thước file (bytes)
     */
    private Long size;

    /**
     * Width của ảnh (pixels)
     */
    private Integer width;

    /**
     * Height của ảnh (pixels)
     */
    private Integer height;
}