package iuh.chillteam.dto.order;

import iuh.chillteam.entity.enums.PaymentMethod;
import iuh.chillteam.entity.enums.ShippingMethod;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request DTO for creating an order
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOrderRequest {

    @NotNull(message = "Payment method is required")
    private PaymentMethod paymentMethod;

    @NotBlank(message = "Shipping address is required")
    @Size(max = 500, message = "Shipping address must not exceed 500 characters")
    private String shippingAddress;

    @NotNull(message = "Shipping method is required")
    private ShippingMethod shippingMethod;

    @Size(max = 500, message = "Notes must not exceed 500 characters")
    private String notes;

    // Optional: Promotion code
    private String promotionCode;
}
