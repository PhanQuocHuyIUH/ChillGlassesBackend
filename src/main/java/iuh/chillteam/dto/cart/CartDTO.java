package iuh.chillteam.dto.cart;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Cart DTO - Response
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartDTO {

    private Long id;

    private Long userId;

    private List<CartItemDTO> items;

    private Integer totalItems;

    private Double totalAmount;

    private String formattedTotalAmount; // "1.500.000đ"

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
