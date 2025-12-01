package iuh.chillteam.dto.cart;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CartItem DTO - Response
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartItemDTO {

    private Long id;

    private Long productId;

    private String productName;

    private String productSlug;

    private Double productPrice;

    private String formattedProductPrice; // "500.000đ"

    private String productImageUrl;

    private String brand;

    private Integer stockQuantity;

    private Integer quantity;

    private Double subtotal;

    private String formattedSubtotal; // "1.000.000đ"

    private Boolean inStock;
}
