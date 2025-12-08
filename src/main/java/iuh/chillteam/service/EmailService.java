package iuh.chillteam.service;

import iuh.chillteam.dto.order.OrderDTO;
import iuh.chillteam.entity.Order;
import iuh.chillteam.entity.User;

/**
 * Email Service Interface
 * Handles email sending operations
 */
public interface EmailService {

    /**
     * Send welcome email when user registers
     */
    void sendWelcomeEmail(User user);

    /**
     * Send order confirmation email (entity-based, legacy)
     */
    void sendOrderConfirmation(Order order);

    /**
     * Send order status update email
     */
    void sendOrderStatusUpdate(Order order, String oldStatus, String newStatus);

    /**
     * Send password reset email
     */
    void sendPasswordResetEmail(User user, String resetToken);

    /**
     * Send promotion email
     */
    void sendPromotionEmail(User user, String promotionCode, String description);

    /**
     * Send generic email (plain text)
     */
    void sendEmail(String to, String subject, String content);

    /**
     * Gửi email xác nhận đơn hàng mới (song ngữ, dùng OrderDTO).
     */
    void sendOrderConfirmationEmail(OrderDTO order);

    /**
     * Gửi email thông báo đơn hàng đã bị hủy (song ngữ, dùng OrderDTO).
     */
    void sendOrderCancellationEmail(OrderDTO order);
}
