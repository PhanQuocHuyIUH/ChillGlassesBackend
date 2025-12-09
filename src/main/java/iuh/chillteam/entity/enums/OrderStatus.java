package iuh.chillteam.entity.enums;

/**
 * Order Status Enum
 */
public enum OrderStatus {
    PENDING,      // Đang chờ xử lý
    CONFIRMED,    // Đã xác nhận
    PROCESSING,   // Đang xử lý
    SHIPPING,     // Đang giao hàng
    DELIVERED,    // Đã giao hàng
    CANCELLED,    // Đã hủy
    REFUNDED      // Đã hoàn tiền
}
