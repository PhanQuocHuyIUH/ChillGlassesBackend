package iuh.chillteam.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for OrderItem details
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDTO {

    private Long id;
    private Long productId;
    private String productName;
    private String productSlug;
    private String productImage;
    private Double productPrice;
    private String formattedPrice;
    private Integer quantity;
    private Double subtotal;
    private String formattedSubtotal;
}
