package iuh.chillteam.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response DTO for ProductImage
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageDTO {

    private Long id;

    private String imageUrl;

    private String altText;

    private Boolean isPrimary;

    private Integer displayOrder;
}
