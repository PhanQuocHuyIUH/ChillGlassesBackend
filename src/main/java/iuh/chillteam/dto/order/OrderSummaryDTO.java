package iuh.chillteam.dto.order;

import iuh.chillteam.entity.enums.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO for Order summary (list view)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderSummaryDTO {

    private Long id;
    private String orderCode;
    private LocalDateTime orderDate;
    private Double totalAmount;
    private String formattedTotalAmount;
    private OrderStatus status;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private Integer totalItems;
    private LocalDateTime createdAt;
    // thêm để fix promotion:
    private String promotionCode;
    private Double promotionDiscountAmount;
    private String formattedPromotionDiscountAmount;

}
