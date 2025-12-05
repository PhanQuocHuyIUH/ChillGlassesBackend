package iuh.chillteam.service;

import iuh.chillteam.entity.enums.PaymentMethod;
import iuh.chillteam.entity.enums.PaymentStatus;

/**
 * Payment Service Interface
 * Handles payment processing for different payment methods
 */
public interface PaymentService {

    /**
     * Process payment for order
     */
    String processPayment(Long orderId, PaymentMethod paymentMethod);

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
    String generatePaymentUrl(Long orderId, Double amount, PaymentMethod paymentMethod);

    /**
     * Handle payment callback
     */
    void handlePaymentCallback(String transactionId, String status);

    /**
     * Process refund (for cancelled orders)
     */
    boolean processRefund(Long orderId);
}
