package iuh.chillteam.service;

import iuh.chillteam.entity.enums.PaymentMethod;
import iuh.chillteam.entity.enums.PaymentStatus;

import java.util.Map;

/**
 * Payment Service Interface
 * Handles payment processing for different payment methods
 */
public interface PaymentService {

    /**
     * Process payment for order
     */
    String processPayment(Long orderId, Long userId, PaymentMethod paymentMethod);

    /**
     * Verify payment callback/webhook
     */
    boolean verifyPayment(String transactionId, String signature);

    /**
     * Update payment status
     */
    void updatePaymentStatus(Long orderId, PaymentStatus paymentStatus);

    /**
     * Generate payment URL for online payment
     */
    String generatePaymentUrl(Long orderId, Long userId, Double amount, PaymentMethod paymentMethod, String clientIp);

    /**
     * Handle payment callback
     */
    void handlePaymentCallback(String transactionId, String status);

    /**
     * Handle VNPay return callback from customer redirect URL
     */
    boolean handleVnpayCallback(Map<String, String> callbackParams);

    /**
     * Handle VNPay IPN callback and return VNPay-compatible response body
     */
    Map<String, String> handleVnpayIpn(Map<String, String> callbackParams);

    /**
     * Process refund (for cancelled orders)
     */
    boolean processRefund(Long orderId);
}
