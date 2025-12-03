package iuh.chillteam.entity.enums;

/**
 * Order Status Enum
 */
public enum OrderStatus {
    PENDING,      // Đang chờ xử lý
    PROCESSING,   // Đang xử lý
    SHIPPED,      // Đã giao cho đơn vị vận chuyển
    DELIVERED,    // Đã giao hàng
    CANCELLED     // Đã hủy
}
