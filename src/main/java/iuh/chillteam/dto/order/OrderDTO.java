package iuh.chillteam.dto.order;

import iuh.chillteam.entity.enums.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for Order details
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {

    private Long id;
    private String orderCode;
    private Long userId;
    private String userFullName;
    private String userEmail;
    private LocalDateTime orderDate;
    private Double totalAmount;
    private String formattedTotalAmount;
    private OrderStatus status;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private String shippingAddress;
    private ShippingMethod shippingMethod;
    private Double shippingFee;
    private String formattedShippingFee;
    private String notes;
    private List<OrderItemDTO> items;
    private Integer totalItems;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    // thêm để fix promotion:
    private String promotionCode;
    private Double promotionDiscountAmount;
    private String formattedPromotionDiscountAmount;
    private String promotionDescription;

}
