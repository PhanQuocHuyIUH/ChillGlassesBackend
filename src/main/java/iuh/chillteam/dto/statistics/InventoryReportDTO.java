package iuh.chillteam.dto.statistics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for Inventory Report (Low Stock Products)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryReportDTO {

    private Long productId;
    private String productName;
    private String productSlug;
    private String brand;
    private String category;
    private Integer stockQuantity;
    private String stockStatus; // "OUT_OF_STOCK", "LOW_STOCK", "IN_STOCK"
    private Boolean isActive;
}
