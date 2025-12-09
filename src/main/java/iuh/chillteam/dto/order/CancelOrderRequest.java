package iuh.chillteam.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Request DTO cho hủy đơn hàng.
 * Khớp với CancelOrderPayload bên FE.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CancelOrderRequest {

    // Chuỗi lý do hủy đã gộp (FE thường gửi vào đây)
    private String notes;

    // Các lý do chọn sẵn (nếu FE muốn gửi raw)
    private List<String> reasons;

    // Lý do khác người dùng gõ tay
    private String otherReason;
}
