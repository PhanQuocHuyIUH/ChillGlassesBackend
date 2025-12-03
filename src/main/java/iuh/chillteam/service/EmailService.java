package iuh.chillteam.service;

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
     * Send order confirmation email
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
     * Send generic email
     */
    void sendEmail(String to, String subject, String content);
}
