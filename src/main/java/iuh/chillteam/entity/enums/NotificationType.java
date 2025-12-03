package iuh.chillteam.entity.enums;

/**
 * Notification Type Enum
 * Các loại thông báo trong hệ thống
 */
public enum NotificationType {
    /**
     * Thông báo liên quan đến đơn hàng (tạo mới, cập nhật trạng thái, hủy)
     */
    ORDER,

    /**
     * Thông báo về khuyến mãi, giảm giá
     */
    PROMOTION,

    /**
     * Thông báo hệ thống (bảo trì, cập nhật, thông báo chung)
     */
    SYSTEM
}
