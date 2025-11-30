package iuh.chillteam.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request DTO for creating new product
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {

    @NotBlank(message = "Product name is required")
    @Size(max = 255, message = "Product name must not exceed 255 characters")
    private String name;

    @Size(max = 255, message = "Product slug must not exceed 255 characters")
    private String slug; // Optional, auto-generated if empty

    private String description;

    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price must be greater than or equal to 0")
    private Double price;

    @Min(value = 0, message = "Original price must be greater than or equal to 0")
    private Double originalPrice;

    @NotBlank(message = "Brand is required")
    @Size(max = 100, message = "Brand must not exceed 100 characters")
    private String brand;

    @NotNull(message = "Category ID is required")
    private Long categoryId;

    @Min(value = 0, message = "Stock quantity must be greater than or equal to 0")
    private Integer stockQuantity;

    private Boolean isActive;
}
