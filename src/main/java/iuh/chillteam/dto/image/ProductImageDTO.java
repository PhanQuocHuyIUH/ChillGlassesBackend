package iuh.chillteam.dto.image;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Product Image DTO - Response
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductImageDTO {

    private Long id;

    /**
     * URL ảnh trên Cloudinary
     */
    private String imageUrl;

    /**
     * Alt text cho SEO
     */
    private String altText;

    /**
     * Ảnh chính hay không
     */
    private Boolean isPrimary;

    /**
     * Thứ tự hiển thị
     */
    private Integer displayOrder;

    /**
     * Thumbnail URL (có thể tạo từ imageUrl với transform)
     */
    private String thumbnailUrl;
}