package iuh.chillteam.service.impl;

import iuh.chillteam.entity.Order;
import iuh.chillteam.entity.OrderItem;
import iuh.chillteam.entity.User;
import iuh.chillteam.repository.OrderItemRepository;
import iuh.chillteam.service.EmailService;
import iuh.chillteam.utils.FormatUtils;
import jakarta.mail.MessagingException;
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

    @Override
    @Async("emailTaskExecutor")
    public void sendWelcomeEmail(User user) {
        log.info("Sending welcome email to: {}", user.getEmail());

        String subject = "Chào mừng đến với ChillGlasses! 🎉";
        String content = buildWelcomeEmailContent(user);

        try {
            sendHtmlEmail(user.getEmail(), subject, content);
            log.info("Welcome email sent successfully to: {}", user.getEmail());
        } catch (Exception e) {
            log.error("Failed to send welcome email to: {}", user.getEmail(), e);
        }
    }

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

    @Override
    @Async("emailTaskExecutor")
    public void sendPasswordResetEmail(User user, String resetToken) {
        log.info("Sending password reset email to: {}", user.getEmail());

        String subject = "Đặt lại mật khẩu - ChillGlasses";
        String content = buildPasswordResetContent(user, resetToken);

        try {
            sendHtmlEmail(user.getEmail(), subject, content);
            log.info("Password reset email sent successfully to: {}", user.getEmail());
        } catch (Exception e) {
            log.error("Failed to send password reset email to: {}", user.getEmail(), e);
        }
    }

    @Override
    @Async("emailTaskExecutor")
    public void sendPromotionEmail(User user, String promotionCode, String description) {
        log.info("Sending promotion email to: {}", user.getEmail());

        String subject = "🎁 Mã giảm giá đặc biệt dành cho bạn!";
        String content = buildPromotionEmailContent(user, promotionCode, description);

        try {
            sendHtmlEmail(user.getEmail(), subject, content);
            log.info("Promotion email sent successfully to: {}", user.getEmail());
        } catch (Exception e) {
            log.error("Failed to send promotion email to: {}", user.getEmail(), e);
        }
    }

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
                        </div>
                        <div class="content">
                            <p>Xin chào <strong>%s</strong>,</p>
                            <p>Cảm ơn bạn đã đăng ký tài khoản tại ChillGlasses! Chúng tôi rất vui được chào đón bạn đến với cộng đồng yêu thích mắt kính thời trang.</p>
                            <p>Với ChillGlasses, bạn có thể:</p>
                            <ul>
                                <li>✨ Khám phá bộ sưu tập mắt kính đa dạng từ các thương hiệu nổi tiếng</li>
                                <li>🎯 Nhận thông báo về các chương trình khuyến mãi đặc biệt</li>
                                <li>📦 Theo dõi đơn hàng dễ dàng và nhanh chóng</li>
                                <li>⭐ Đánh giá và chia sẻ trải nghiệm của bạn</li>
                            </ul>
                            <p>Hãy bắt đầu khám phá ngay hôm nay!</p>
                            <a href="http://localhost:8080/api/products" class="button">Khám phá sản phẩm</a>
                        </div>
                        <div class="footer">
                            <p>&copy; 2025 ChillGlasses. All rights reserved.</p>
                            <p>Email: support@chillglasses.com | Hotline: 1900-xxxx</p>
                        </div>
                    </div>
                </body>
                </html>
                """.formatted(user.getFullName());
    }

    /**
     * Build order confirmation email content
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
                            <h1>🔐 Đặt lại mật khẩu</h1>
                        </div>
                        <div class="content">
                            <p>Xin chào <strong>%s</strong>,</p>
                            <p>Chúng tôi nhận được yêu cầu đặt lại mật khẩu cho tài khoản của bạn.</p>
                            <p>Nhấn vào nút bên dưới để đặt lại mật khẩu:</p>
                            <p style="text-align: center;">
                                <a href="%s" class="button">Đặt lại mật khẩu</a>
                            </p>
                            <div class="warning">
                                <strong>⚠️ Lưu ý:</strong> Link này chỉ có hiệu lực trong 15 phút. Nếu bạn không yêu cầu đặt lại mật khẩu, vui lòng bỏ qua email này.
                            </div>
                        </div>
                        <div class="footer">
                            <p>&copy; 2025 ChillGlasses. All rights reserved.</p>
                        </div>
                    </div>
                </body>
                </html>
                """.formatted(user.getFullName(), resetUrl);
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
                        </div>
                        <div class="content">
                            <p>Xin chào <strong>%s</strong>,</p>
                            <p>Chúng tôi có một ưu đãi đặc biệt dành riêng cho bạn!</p>
                            <p>%s</p>
                            <div class="promo-code">
                                <p style="margin: 0; color: #888;">Mã giảm giá của bạn:</p>
                                <p class="code">%s</p>
                            </div>
                            <p style="text-align: center;">
                                <a href="http://localhost:8080/api/products" class="button">Mua sắm ngay</a>
                            </p>
                            <p style="margin-top: 30px; color: #888; font-size: 14px;">* Vui lòng kiểm tra điều kiện áp dụng mã giảm giá trước khi sử dụng.</p>
                        </div>
                        <div class="footer">
                            <p>&copy; 2025 ChillGlasses. All rights reserved.</p>
                        </div>
                    </div>
                </body>
                </html>
                """.formatted(user.getFullName(), description, promotionCode);
    }

    // Helper methods
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
}
