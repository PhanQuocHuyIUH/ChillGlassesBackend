package iuh.chillteam.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request DTO for filtering products
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductFilterRequest {

    private String keyword; // Search in name, brand, description

    private Long categoryId;

    private String brand;

    private Double minPrice;

    private Double maxPrice;

    private Boolean inStock; // true = stockQuantity > 0

    private Boolean isActive;

    // Sorting
    private String sortBy; // price, rating, createdAt, name

    private String sortDir; // asc, desc
}
