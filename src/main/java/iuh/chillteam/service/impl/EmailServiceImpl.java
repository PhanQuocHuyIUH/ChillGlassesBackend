package iuh.chillteam.service.impl;

import iuh.chillteam.dto.order.OrderDTO;
import iuh.chillteam.entity.Order;
import iuh.chillteam.entity.OrderItem;
import iuh.chillteam.entity.User;
import iuh.chillteam.repository.OrderItemRepository;
import iuh.chillteam.service.EmailService;
import iuh.chillteam.utils.FormatUtils;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Email Service Implementation
 * Handles sending emails for various events
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;
    private final OrderItemRepository orderItemRepository;

    @Value("${app.mail.from:noreply@chillglasses.com}")
    private String fromEmail;

    @Value("${app.mail.from-name:ChillGlasses}")
    private String fromName;

    // ====================== WELCOME EMAIL ======================

    @Override
    @Async("emailTaskExecutor")
    public void sendWelcomeEmail(User user) {
        log.info("Sending welcome email to: {}", user.getEmail());

        String subject = "Chào mừng đến với ChillGlasses! / Welcome to ChillGlasses! 🎉";
        String content = buildWelcomeEmailContent(user);

        try {
            sendHtmlEmail(user.getEmail(), subject, content);
            log.info("Welcome email sent successfully to: {}", user.getEmail());
        } catch (Exception e) {
            log.error("Failed to send welcome email to: {}", user.getEmail(), e);
        }
    }

    // ====================== ORDER CONFIRMATION (ENTITY – LEGACY) ======================

    @Override
    @Async("emailTaskExecutor")
    public void sendOrderConfirmation(Order order) {
        log.info("Sending order confirmation email for order: {}", order.getOrderCode());

        String subject = "Xác nhận đơn hàng #" + order.getOrderCode();
        String content = buildOrderConfirmationContent(order);

        try {
            sendHtmlEmail(order.getUser().getEmail(), subject, content);
            log.info("Order confirmation email sent successfully for order: {}", order.getOrderCode());
        } catch (Exception e) {
            log.error("Failed to send order confirmation email for order: {}", order.getOrderCode(), e);
        }
    }

    // ====================== ORDER STATUS UPDATE (ENTITY) ======================

    @Override
    @Async("emailTaskExecutor")
    public void sendOrderStatusUpdate(Order order, String oldStatus, String newStatus) {
        log.info("Sending order status update email for order: {}", order.getOrderCode());

        String subject = "Cập nhật đơn hàng #" + order.getOrderCode();
        String content = buildOrderStatusUpdateContent(order, oldStatus, newStatus);

        try {
            sendHtmlEmail(order.getUser().getEmail(), subject, content);
            log.info("Order status update email sent successfully for order: {}", order.getOrderCode());
        } catch (Exception e) {
            log.error("Failed to send order status update email for order: {}", order.getOrderCode(), e);
        }
    }

    // ====================== PASSWORD RESET ======================

    @Override
    @Async("emailTaskExecutor")
    public void sendPasswordResetEmail(User user, String resetToken) {
        log.info("Sending password reset email to: {}", user.getEmail());

        String subject = "Đặt lại mật khẩu / Password reset - ChillGlasses";
        String content = buildPasswordResetContent(user, resetToken);

        try {
            sendHtmlEmail(user.getEmail(), subject, content);
            log.info("Password reset email sent successfully to: {}", user.getEmail());
        } catch (Exception e) {
            log.error("Failed to send password reset email to: {}", user.getEmail(), e);
        }
    }

    // ====================== PROMOTION ======================

    @Override
    @Async("emailTaskExecutor")
    public void sendPromotionEmail(User user, String promotionCode, String description) {
        log.info("Sending promotion email to: {}", user.getEmail());

        String subject = "🎁 Ưu đãi đặc biệt / Special offer just for you!";
        String content = buildPromotionEmailContent(user, promotionCode, description);

        try {
            sendHtmlEmail(user.getEmail(), subject, content);
            log.info("Promotion email sent successfully to: {}", user.getEmail());
        } catch (Exception e) {
            log.error("Failed to send promotion email to: {}", user.getEmail(), e);
        }
    }

    // ====================== GENERIC TEXT EMAIL ======================

    @Override
    @Async("emailTaskExecutor")
    public void sendEmail(String to, String subject, String content) {
        log.info("Sending email to: {}", to);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);

        try {
            mailSender.send(message);
            log.info("Email sent successfully to: {}", to);
        } catch (Exception e) {
            log.error("Failed to send email to: {}", to, e);
        }
    }

    // ====================== HTML SENDER ======================

    /**
     * Send HTML email
     */
    private void sendHtmlEmail(String to, String subject, String htmlContent) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(fromEmail, fromName);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);

            mailSender.send(message);
        } catch (Exception e) {
            log.error("Failed to send HTML email to: {}", to, e);
            throw new RuntimeException("Failed to send email", e);
        }
    }

    // ====================== CONTENT BUILDERS (WELCOME / PASSWORD / PROMO / STATUS – EXISTING) ======================

    /**
     * Build welcome email content
     */
    private String buildWelcomeEmailContent(User user) {
        return """
                <!DOCTYPE html>
                <html>
                <head>
                    <meta charset="UTF-8">
                    <style>
                        body { font-family: Arial, sans-serif; line-height: 1.6; color: #333; }
                        .container { max-width: 600px; margin: 0 auto; padding: 20px; }
                        .header { background: linear-gradient(135deg, #667eea 0%%, #764ba2 100%%); color: white; padding: 30px; text-align: center; border-radius: 10px 10px 0 0; }
                        .content { background: #f9f9f9; padding: 30px; border-radius: 0 0 10px 10px; }
                        .button { display: inline-block; background: #667eea; color: white; padding: 12px 30px; text-decoration: none; border-radius: 5px; margin-top: 20px; }
                        .footer { text-align: center; margin-top: 30px; color: #888; font-size: 12px; }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <div class="header">
                            <h1>🕶️ Chào mừng đến với ChillGlasses!</h1>
                            <h2>Welcome to ChillGlasses!</h2>
                        </div>
                        <div class="content">
                            <p>Xin chào <strong>%s</strong>,</p>
                            <p>Cảm ơn bạn đã đăng ký tài khoản tại ChillGlasses! Chúng tôi rất vui được chào đón bạn đến với cộng đồng yêu thích mắt kính thời trang.</p>
                            <p><em>Hello <strong>%s</strong>,</em></p>
                            <p><em>Thank you for creating an account at ChillGlasses. We are thrilled to welcome you to our eyewear-loving community.</em></p>
                            <p>Với ChillGlasses, bạn có thể:</p>
                            <ul>
                                <li>✨ Khám phá bộ sưu tập mắt kính đa dạng từ các thương hiệu nổi tiếng</li>
                                <li>🎯 Nhận thông báo về các chương trình khuyến mãi đặc biệt</li>
                                <li>📦 Theo dõi đơn hàng dễ dàng và nhanh chóng</li>
                                <li>⭐ Đánh giá và chia sẻ trải nghiệm của bạn</li>
                            </ul>
                            <p><em>With ChillGlasses, you can:</em></p>
                            <ul>
                                <li><em>✨ Explore a wide collection of eyewear from well-known brands</em></li>
                                <li><em>🎯 Receive notifications about exclusive promotions</em></li>
                                <li><em>📦 Track your orders easily and quickly</em></li>
                                <li><em>⭐ Review and share your shopping experience</em></li>
                            </ul>
                            <p>Hãy bắt đầu khám phá ngay hôm nay!</p>
                            <p><em>Start exploring today!</em></p>
                        </div>
                        <div class="footer">
                            <p>&copy; 2025 ChillGlasses. All rights reserved.</p>
                            <p>Email: support@chillglasses.com | Hotline: 1900-xxxx</p>
                        </div>
                    </div>
                </body>
                </html>
                """.formatted(user.getFullName(), user.getFullName());
    }

    /**
     * Build order confirmation email content (ENTITY – cũ, chỉ tiếng Việt, giữ lại để tương thích)
     */
    private String buildOrderConfirmationContent(Order order) {
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(order.getId());

        StringBuilder itemsHtml = new StringBuilder();
        for (OrderItem item : orderItems) {
            itemsHtml.append(String.format("""
                    <tr>
                        <td style="padding: 10px; border-bottom: 1px solid #eee;">%s</td>
                        <td style="padding: 10px; border-bottom: 1px solid #eee; text-align: center;">%d</td>
                        <td style="padding: 10px; border-bottom: 1px solid #eee; text-align: right;">%s</td>
                        <td style="padding: 10px; border-bottom: 1px solid #eee; text-align: right;">%s</td>
                    </tr>
                    """,
                    item.getProductName(),
                    item.getQuantity(),
                    FormatUtils.formatPrice(item.getProductPrice()),
                    FormatUtils.formatPrice(item.getSubtotal())
            ));
        }

        return """
                <!DOCTYPE html>
                <html>
                <head>
                    <meta charset="UTF-8">
                    <style>
                        body { font-family: Arial, sans-serif; line-height: 1.6; color: #333; }
                        .container { max-width: 600px; margin: 0 auto; padding: 20px; }
                        .header { background: #28a745; color: white; padding: 30px; text-align: center; border-radius: 10px 10px 0 0; }
                        .content { background: #f9f9f9; padding: 30px; border-radius: 0 0 10px 10px; }
                        .order-info { background: white; padding: 20px; border-radius: 5px; margin: 20px 0; }
                        table { width: 100%%; border-collapse: collapse; }
                        .total { font-size: 18px; font-weight: bold; color: #28a745; }
                        .footer { text-align: center; margin-top: 30px; color: #888; font-size: 12px; }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <div class="header">
                            <h1>✅ Đơn hàng đã được xác nhận!</h1>
                        </div>
                        <div class="content">
                            <p>Xin chào <strong>%s</strong>,</p>
                            <p>Cảm ơn bạn đã đặt hàng tại ChillGlasses! Đơn hàng của bạn đã được tiếp nhận và đang được xử lý.</p>

                            <div class="order-info">
                                <h3>Thông tin đơn hàng</h3>
                                <p><strong>Mã đơn hàng:</strong> %s</p>
                                <p><strong>Ngày đặt:</strong> %s</p>
                                <p><strong>Địa chỉ giao hàng:</strong> %s</p>
                                <p><strong>Phương thức thanh toán:</strong> %s</p>
                                <p><strong>Phương thức vận chuyển:</strong> %s</p>
                            </div>

                            <h3>Chi tiết sản phẩm</h3>
                            <table>
                                <thead>
                                    <tr style="background: #f0f0f0;">
                                        <th style="padding: 10px; text-align: left;">Sản phẩm</th>
                                        <th style="padding: 10px; text-align: center;">Số lượng</th>
                                        <th style="padding: 10px; text-align: right;">Đơn giá</th>
                                        <th style="padding: 10px; text-align: right;">Thành tiền</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    %s
                                    <tr>
                                        <td colspan="3" style="padding: 10px; text-align: right;"><strong>Phí vận chuyển:</strong></td>
                                        <td style="padding: 10px; text-align: right;">%s</td>
                                    </tr>
                                    <tr>
                                        <td colspan="3" style="padding: 10px; text-align: right;"><strong>Tổng cộng:</strong></td>
                                        <td class="total" style="padding: 10px; text-align: right;">%s</td>
                                    </tr>
                                </tbody>
                            </table>

                            <p style="margin-top: 30px;">Chúng tôi sẽ thông báo cho bạn khi đơn hàng được giao cho đơn vị vận chuyển.</p>
                        </div>
                        <div class="footer">
                            <p>&copy; 2025 ChillGlasses. All rights reserved.</p>
                        </div>
                    </div>
                </body>
                </html>
                """.formatted(
                order.getUser().getFullName(),
                order.getOrderCode(),
                order.getOrderDate(),
                order.getShippingAddress(),
                getPaymentMethodLabel(order.getPaymentMethod().name()),
                getShippingMethodLabel(order.getShippingMethod().name()),
                itemsHtml.toString(),
                FormatUtils.formatPrice(order.getShippingFee()),
                FormatUtils.formatPrice(order.getTotalAmount())
        );
    }

    /**
     * Build order status update email content
     */
    private String buildOrderStatusUpdateContent(Order order, String oldStatus, String newStatus) {
        String statusMessage = getStatusMessage(newStatus);
        String statusColor = getStatusColor(newStatus);

        return """
                <!DOCTYPE html>
                <html>
                <head>
                    <meta charset="UTF-8">
                    <style>
                        body { font-family: Arial, sans-serif; line-height: 1.6; color: #333; }
                        .container { max-width: 600px; margin: 0 auto; padding: 20px; }
                        .header { background: %s; color: white; padding: 30px; text-align: center; border-radius: 10px 10px 0 0; }
                        .content { background: #f9f9f9; padding: 30px; border-radius: 0 0 10px 10px; }
                        .status-badge { display: inline-block; background: %s; color: white; padding: 8px 20px; border-radius: 20px; font-weight: bold; }
                        .footer { text-align: center; margin-top: 30px; color: #888; font-size: 12px; }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <div class="header">
                            <h1>📦 Cập nhật đơn hàng</h1>
                        </div>
                        <div class="content">
                            <p>Xin chào <strong>%s</strong>,</p>
                            <p>Đơn hàng <strong>%s</strong> của bạn đã được cập nhật trạng thái:</p>
                            <p style="text-align: center; margin: 30px 0;">
                                <span class="status-badge">%s</span>
                            </p>
                            <p>%s</p>
                            <p><strong>Địa chỉ giao hàng:</strong> %s</p>
                        </div>
                        <div class="footer">
                            <p>&copy; 2025 ChillGlasses. All rights reserved.</p>
                        </div>
                    </div>
                </body>
                </html>
                """.formatted(
                statusColor,
                statusColor,
                order.getUser().getFullName(),
                order.getOrderCode(),
                getStatusLabel(newStatus),
                statusMessage,
                order.getShippingAddress()
        );
    }

    /**
     * Build password reset email content
     */
    private String buildPasswordResetContent(User user, String resetToken) {
        String resetUrl = "http://localhost:8080/reset-password?token=" + resetToken;

        return """
                <!DOCTYPE html>
                <html>
                <head>
                    <meta charset="UTF-8">
                    <style>
                        body { font-family: Arial, sans-serif; line-height: 1.6; color: #333; }
                        .container { max-width: 600px; margin: 0 auto; padding: 20px; }
                        .header { background: #dc3545; color: white; padding: 30px; text-align: center; border-radius: 10px 10px 0 0; }
                        .content { background: #f9f9f9; padding: 30px; border-radius: 0 0 10px 10px; }
                        .button { display: inline-block; background: #dc3545; color: white; padding: 12px 30px; text-decoration: none; border-radius: 5px; margin-top: 20px; }
                        .warning { background: #fff3cd; border-left: 4px solid #ffc107; padding: 15px; margin: 20px 0; }
                        .footer { text-align: center; margin-top: 30px; color: #888; font-size: 12px; }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <div class="header">
                            <h1>🔐 Đặt lại mật khẩu / Password reset</h1>
                        </div>
                        <div class="content">
                            <p>Xin chào <strong>%s</strong>,</p>
                            <p>Chúng tôi nhận được yêu cầu đặt lại mật khẩu cho tài khoản của bạn.</p>
                            <p><em>Hello <strong>%s</strong>, we have received a request to reset the password for your account.</em></p>
                            <p>Nhấn vào nút bên dưới để đặt lại mật khẩu:</p>
                            <p><em>Click the button below to reset your password:</em></p>
                            <p style="text-align: center;">
                                <a href="%s" class="button">Đặt lại mật khẩu / Reset password</a>
                            </p>
                            <div class="warning">
                                <strong>⚠️ Lưu ý:</strong> Link này chỉ có hiệu lực trong 15 phút. Nếu bạn không yêu cầu đặt lại mật khẩu, vui lòng bỏ qua email này.
                                <br/>
                                <em><strong>⚠️ Note:</strong> This link is only valid for 15 minutes. If you did not request a password reset, please ignore this email.</em>
                            </div>
                        </div>
                        <div class="footer">
                            <p>&copy; 2025 ChillGlasses. All rights reserved.</p>
                        </div>
                    </div>
                </body>
                </html>
                """.formatted(user.getFullName(), user.getFullName(), resetUrl);
    }

    /**
     * Build promotion email content
     */
    private String buildPromotionEmailContent(User user, String promotionCode, String description) {
        return """
                <!DOCTYPE html>
                <html>
                <head>
                    <meta charset="UTF-8">
                    <style>
                        body { font-family: Arial, sans-serif; line-height: 1.6; color: #333; }
                        .container { max-width: 600px; margin: 0 auto; padding: 20px; }
                        .header { background: linear-gradient(135deg, #f093fb 0%%, #f5576c 100%%); color: white; padding: 30px; text-align: center; border-radius: 10px 10px 0 0; }
                        .content { background: #f9f9f9; padding: 30px; border-radius: 0 0 10px 10px; }
                        .promo-code { background: #fff; border: 2px dashed #f5576c; padding: 20px; text-align: center; margin: 20px 0; border-radius: 5px; }
                        .code { font-size: 24px; font-weight: bold; color: #f5576c; letter-spacing: 2px; }
                        .button { display: inline-block; background: #f5576c; color: white; padding: 12px 30px; text-decoration: none; border-radius: 5px; margin-top: 20px; }
                        .footer { text-align: center; margin-top: 30px; color: #888; font-size: 12px; }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <div class="header">
                            <h1>🎁 Ưu đãi đặc biệt dành cho bạn!</h1>
                            <h2>🎁 A special offer just for you!</h2>
                        </div>
                        <div class="content">
                            <p>Xin chào <strong>%s</strong>,</p>
                            <p>%s</p>
                            <p><em>Hello <strong>%s</strong>, here is a special offer prepared just for you.</em></p>
                            <div class="promo-code">
                                <p style="margin: 0; color: #888;">Mã giảm giá của bạn / Your promo code:</p>
                                <p class="code">%s</p>
                            </div>
                            <p style="margin-top: 30px; color: #888; font-size: 14px;">
                                * Vui lòng kiểm tra điều kiện áp dụng mã giảm giá trước khi sử dụng.<br/>
                                <em>* Please check the terms and conditions of the promotion before using the code.</em>
                            </p>
                        </div>
                        <div class="footer">
                            <p>&copy; 2025 ChillGlasses. All rights reserved.</p>
                        </div>
                    </div>
                </body>
                </html>
                """.formatted(user.getFullName(), description, user.getFullName(), promotionCode);
    }

    // ====================== HELPER LABELS ======================

    private String getPaymentMethodLabel(String method) {
        return switch (method) {
            case "COD" -> "Thanh toán khi nhận hàng (COD)";
            case "BANK_TRANSFER" -> "Chuyển khoản ngân hàng";
            case "E_WALLET" -> "Ví điện tử (MoMo/ZaloPay)";
            default -> method;
        };
    }

    private String getShippingMethodLabel(String method) {
        return switch (method) {
            case "STANDARD" -> "Giao hàng tiêu chuẩn (3-5 ngày)";
            case "EXPRESS" -> "Giao hàng nhanh (1-2 ngày)";
            default -> method;
        };
    }

    private String getStatusLabel(String status) {
        return switch (status) {
            case "PENDING" -> "Chờ xác nhận";
            case "PROCESSING" -> "Đang xử lý";
            case "SHIPPED" -> "Đang giao hàng";
            case "DELIVERED" -> "Đã giao hàng";
            case "CANCELLED" -> "Đã hủy";
            default -> status;
        };
    }

    private String getStatusMessage(String status) {
        return switch (status) {
            case "PENDING" -> "Đơn hàng của bạn đang chờ xác nhận từ hệ thống.";
            case "PROCESSING" -> "Đơn hàng của bạn đang được chuẩn bị và đóng gói.";
            case "SHIPPED" -> "Đơn hàng của bạn đã được giao cho đơn vị vận chuyển và đang trên đường giao đến bạn.";
            case "DELIVERED" -> "Đơn hàng của bạn đã được giao thành công. Cảm ơn bạn đã mua hàng!";
            case "CANCELLED" -> "Đơn hàng của bạn đã bị hủy. Nếu có thắc mắc, vui lòng liên hệ với chúng tôi.";
            default -> "";
        };
    }

    private String getStatusColor(String status) {
        return switch (status) {
            case "PENDING" -> "#ffc107";
            case "PROCESSING" -> "#17a2b8";
            case "SHIPPED" -> "#007bff";
            case "DELIVERED" -> "#28a745";
            case "CANCELLED" -> "#dc3545";
            default -> "#6c757d";
        };
    }

    // ====================== NEW: DTO-BASED, SONG NGỮ ======================

    @Override
    @Async("emailTaskExecutor")
    public void sendOrderConfirmationEmail(OrderDTO order) {
        if (order == null || order.getUserEmail() == null || order.getUserEmail().isBlank()) {
            log.warn("Skip sending order confirmation email because email is empty. orderCode={}",
                    order != null ? order.getOrderCode() : null);
            return;
        }

        String to = order.getUserEmail();
        String subject = "[ChillGlasses] Xác nhận đơn hàng / Order confirmation " + order.getOrderCode();
        String content = buildOrderConfirmationContentFromDTO(order);

        try {
            sendHtmlEmail(to, subject, content);
            log.info("Sent order confirmation (DTO) email to {} for order {}", to, order.getOrderCode());
        } catch (Exception e) {
            log.error("Failed to send order confirmation (DTO) email for order {}", order.getOrderCode(), e);
        }
    }

    @Override
    @Async("emailTaskExecutor")
    public void sendOrderCancellationEmail(OrderDTO order) {
        if (order == null || order.getUserEmail() == null || order.getUserEmail().isBlank()) {
            log.warn("Skip sending order cancellation email because email is empty. orderCode={}",
                    order != null ? order.getOrderCode() : null);
            return;
        }

        String to = order.getUserEmail();
        String subject = "[ChillGlasses] Đơn hàng đã hủy / Order cancelled " + order.getOrderCode();
        String content = buildOrderCancellationContentFromDTO(order);

        try {
            sendHtmlEmail(to, subject, content);
            log.info("Sent order cancellation (DTO) email to {} for order {}", to, order.getOrderCode());
        } catch (Exception e) {
            log.error("Failed to send order cancellation (DTO) email for order {}", order.getOrderCode(), e);
        }
    }

    private String buildOrderConfirmationContentFromDTO(OrderDTO order) {
        StringBuilder itemsHtml = new StringBuilder();
        order.getItems().forEach(item -> {
            itemsHtml.append("""
                    <tr>
                        <td style="padding: 8px; border-bottom: 1px solid #eee;">%s</td>
                        <td style="padding: 8px; border-bottom: 1px solid #eee; text-align: center;">%d</td>
                        <td style="padding: 8px; border-bottom: 1px solid #eee; text-align: right;">%s</td>
                        <td style="padding: 8px; border-bottom: 1px solid #eee; text-align: right;">%s</td>
                    </tr>
                    """.formatted(
                    item.getProductName(),
                    item.getQuantity(),
                    item.getFormattedPrice(),
                    item.getFormattedSubtotal()
            ));
        });

        String promotionBlock = "";
        if (order.getPromotionCode() != null) {
            promotionBlock = """
                    <p><strong>Mã khuyến mãi / Promotion code:</strong> %s</p>
                    %s
                    """.formatted(
                    order.getPromotionCode(),
                    order.getFormattedPromotionDiscountAmount() != null
                            ? "<p><strong>Giảm giá / Discount:</strong> -" + order.getFormattedPromotionDiscountAmount() + "</p>"
                            : ""
            );
        }

        return """
                <!DOCTYPE html>
                <html>
                <head>
                    <meta charset="UTF-8">
                    <style>
                        body { font-family: Arial, sans-serif; line-height: 1.6; color: #333; }
                        .container { max-width: 700px; margin: 0 auto; padding: 20px; }
                        .header { background: #28a745; color: white; padding: 24px; text-align: center; border-radius: 10px 10px 0 0; }
                        .content { background: #f9f9f9; padding: 24px; border-radius: 0 0 10px 10px; }
                        .order-info { background: white; padding: 16px; border-radius: 6px; margin: 16px 0; }
                        table { width: 100%%; border-collapse: collapse; font-size: 14px; }
                        .footer { text-align: center; margin-top: 24px; color: #888; font-size: 12px; }
                        .total-row { font-weight: bold; }
                        .section-title { margin-top: 24px; margin-bottom: 8px; font-size: 16px; font-weight: 600; }
                        hr { margin: 24px 0; border: none; border-top: 1px solid #ddd; }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <div class="header">
                            <h2>✅ Xác nhận đơn hàng / Order confirmation</h2>
                        </div>
                        <div class="content">
                            <!-- Vietnamese -->
                            <p>Xin chào <strong>%s</strong>,</p>
                            <p>Cảm ơn bạn đã đặt hàng tại <strong>ChillGlasses</strong>. Đơn hàng của bạn đã được ghi nhận và đang được xử lý.</p>

                            <div class="order-info">
                                <h3>Thông tin đơn hàng</h3>
                                <p><strong>Mã đơn hàng / Order code:</strong> %s</p>
                                <p><strong>Ngày đặt / Order date:</strong> %s</p>
                                <p><strong>Trạng thái / Status:</strong> %s</p>
                                <p><strong>Phương thức giao hàng / Shipping method:</strong> %s</p>
                                <p><strong>Địa chỉ giao hàng / Shipping address:</strong> %s</p>
                                <p><strong>Phương thức thanh toán / Payment method:</strong> %s</p>
                                %s
                            </div>

                            <h3 class="section-title">Chi tiết sản phẩm / Order items</h3>
                            <table>
                                <thead>
                                    <tr style="background: #efefef;">
                                        <th style="text-align: left; padding: 8px;">Sản phẩm / Product</th>
                                        <th style="text-align: center; padding: 8px;">SL / Qty</th>
                                        <th style="text-align: right; padding: 8px;">Đơn giá / Unit price</th>
                                        <th style="text-align: right; padding: 8px;">Tạm tính / Subtotal</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    %s
                                    <tr>
                                        <td colspan="3" style="text-align: right; padding: 8px;">Phí vận chuyển / Shipping fee:</td>
                                        <td style="text-align: right; padding: 8px;">%s</td>
                                    </tr>
                                    %s
                                    <tr class="total-row">
                                        <td colspan="3" style="text-align: right; padding: 8px;">Tổng cộng / Grand total:</td>
                                        <td style="text-align: right; padding: 8px;">%s</td>
                                    </tr>
                                </tbody>
                            </table>

                            <p style="margin-top: 16px;">
                                Chúng tôi sẽ gửi thông báo tiếp theo khi đơn hàng được giao cho đơn vị vận chuyển.<br/>
                                Nếu bạn có bất kỳ thắc mắc nào, vui lòng phản hồi email này để được hỗ trợ.
                            </p>

                            <hr/>

                            <!-- English section -->
                            <p><em>Dear <strong>%s</strong>,</em></p>
                            <p><em>Thank you for shopping at <strong>ChillGlasses</strong>. Your order has been received and is now being processed.</em></p>
                            <p><em>You will receive another notification once your order is handed over to the shipping carrier.</em></p>
                            <p><em>If you have any questions, please reply to this email and our support team will gladly assist you.</em></p>
                        </div>
                        <div class="footer">
                            <p>&copy; 2025 ChillGlasses. All rights reserved.</p>
                        </div>
                    </div>
                </body>
                </html>
                """.formatted(
                order.getUserFullName(),
                order.getOrderCode(),
                order.getOrderDate(),
                order.getStatus(),
                order.getShippingMethod(),
                order.getShippingAddress(),
                order.getPaymentMethod(),
                promotionBlock,
                itemsHtml.toString(),
                order.getFormattedShippingFee(),
                order.getPromotionCode() != null && order.getFormattedPromotionDiscountAmount() != null
                        ? """
                          <tr>
                              <td colspan="3" style="text-align: right; padding: 8px;">Giảm giá / Discount:</td>
                              <td style="text-align: right; padding: 8px;">-%s</td>
                          </tr>
                          """.formatted(order.getFormattedPromotionDiscountAmount())
                        : "",
                order.getFormattedTotalAmount(),
                order.getUserFullName()
        );
    }

    private String buildOrderCancellationContentFromDTO(OrderDTO order) {
        return """
                <!DOCTYPE html>
                <html>
                <head>
                    <meta charset="UTF-8">
                    <style>
                        body { font-family: Arial, sans-serif; line-height: 1.6; color: #333; }
                        .container { max-width: 700px; margin: 0 auto; padding: 20px; }
                        .header { background: #dc3545; color: white; padding: 24px; text-align: center; border-radius: 10px 10px 0 0; }
                        .content { background: #f9f9f9; padding: 24px; border-radius: 0 0 10px 10px; }
                        .footer { text-align: center; margin-top: 24px; color: #888; font-size: 12px; }
                        hr { margin: 24px 0; border: none; border-top: 1px solid #ddd; }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <div class="header">
                            <h2>❌ Đơn hàng đã được hủy / Order cancelled</h2>
                        </div>
                        <div class="content">
                            <!-- Vietnamese -->
                            <p>Xin chào <strong>%s</strong>,</p>
                            <p>Đơn hàng với mã <strong>%s</strong> đã được hủy.</p>
                            <p><strong>Tổng giá trị đơn hàng / Order total:</strong> %s</p>
                            <p>Nếu bạn không yêu cầu hủy đơn này hoặc có bất kỳ thắc mắc nào, vui lòng phản hồi email này để được hỗ trợ.</p>

                            <hr/>

                            <!-- English -->
                            <p><em>Dear <strong>%s</strong>,</em></p>
                            <p><em>Your order with code <strong>%s</strong> has been cancelled.</em></p>
                            <p><em><strong>Order total:</strong> %s</em></p>
                            <p><em>If you did not request this cancellation or have any questions, please reply to this email so we can assist you.</em></p>
                        </div>
                        <div class="footer">
                            <p>&copy; 2025 ChillGlasses. All rights reserved.</p>
                        </div>
                    </div>
                </body>
                </html>
                """.formatted(
                order.getUserFullName(),
                order.getOrderCode(),
                order.getFormattedTotalAmount(),
                order.getUserFullName(),
                order.getOrderCode(),
                order.getFormattedTotalAmount()
        );
    }
}
