package iuh.chillteam.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Response DTO for Product List (simplified)
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductListDTO {

    private Long id;

    private String name;

    private String slug;

    private Double price;

    private String formattedPrice; // "1.500.000đ"

    private Double originalPrice;

    private String formattedOriginalPrice; // "2.000.000đ"

    private String brand;

    private Long categoryId;

    private String categoryName;

    private Integer stockQuantity;

    private Double rating;

    private Integer reviewCount;

    private Boolean isActive;

    private String primaryImageUrl; // First primary image

    private LocalDateTime createdAt;
}
