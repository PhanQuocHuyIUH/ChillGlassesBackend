package iuh.chillteam.dto.statistics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for Top Selling Product Statistics
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopSellingProductDTO {

    private Long productId;
    private String productName;
    private String productSlug;
    private String productImage;
    private String brand;
    private Long totalQuantitySold;
    private Double totalRevenue;
    private Long orderCount;
}
