package iuh.chillteam.service.impl;

import iuh.chillteam.entity.Order;
import iuh.chillteam.entity.enums.*;
import iuh.chillteam.exception.BadRequestException;
import iuh.chillteam.exception.ForbiddenException;
import iuh.chillteam.exception.OrderNotFoundException;
import iuh.chillteam.exception.PaymentFailedException;
import iuh.chillteam.repository.OrderRepository;
import iuh.chillteam.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HexFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Payment Service Implementation
 * Mock implementation for payment processing
 * TODO: Integrate with real payment gateways (VNPay, MoMo, ZaloPay, etc.)
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final OrderRepository orderRepository;

    @Value("${payment.vnpay.enabled:false}")
    private boolean vnpayEnabled;

    @Value("${payment.vnpay.tmn-code:}")
    private String vnpayTmnCode;

    @Value("${payment.vnpay.hash-secret:}")
    private String vnpayHashSecret;

    @Value("${payment.vnpay.pay-url:https://sandbox.vnpayment.vn/paymentv2/vpcpay.html}")
    private String vnpayPayUrl;

    @Value("${payment.vnpay.return-url:http://localhost:3000/checkout/result}")
    private String vnpayReturnUrl;

    @Value("${payment.vnpay.ipn-url:http://localhost:8080/api/payments/ipn/vnpay}")
    private String vnpayIpnUrl;

    @Value("${payment.vnpay.version:2.1.0}")
    private String vnpayVersion;

    @Value("${payment.vnpay.command:pay}")
    private String vnpayCommand;

    @Value("${payment.vnpay.curr-code:VND}")
    private String vnpayCurrCode;

    @Value("${payment.vnpay.locale:vn}")
    private String vnpayLocale;

    @Value("${payment.vnpay.order-type:other}")
    private String vnpayOrderType;

    @Override
    public String processPayment(Long orderId, Long userId, PaymentMethod paymentMethod) {
        log.info("Processing payment for order: {} with method: {}", orderId, paymentMethod);

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
        validateOrderOwnership(order, userId);

        return switch (paymentMethod) {
            case COD -> processCODPayment(order);
            case BANK_TRANSFER -> processBankTransferPayment(order);
            case E_WALLET -> processEWalletPayment(order);
        };
    }

    @Override
    public boolean verifyPayment(String transactionId, String signature) {
        log.info("Verifying payment for transaction: {}", transactionId);

        // TODO: Implement real payment verification with gateway
        // Verify signature using gateway's secret key
        // Check transaction status with gateway API

        // Mock verification (always true for now)
        return true;
    }

    @Override
    public void updatePaymentStatus(Long orderId, PaymentStatus paymentStatus) {
        log.info("Updating payment status for order: {} to {}", orderId, paymentStatus);

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        order.setPaymentStatus(paymentStatus);
        orderRepository.save(order);

        log.info("Updated payment status for order: {}", orderId);
    }

    @Override
    public String generatePaymentUrl(Long orderId, Long userId, Double amount, PaymentMethod paymentMethod, String clientIp) {
        log.info("Generating payment URL for order: {} with method: {}", orderId, paymentMethod);

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
        validateOrderOwnership(order, userId);

        validateAmount(order, amount);

        // TODO: Integrate with real payment gateways
        // VNPay: https://sandbox.vnpayment.vn/paymentv2/vpcpay.html
        // MoMo: https://test-payment.momo.vn/v2/gateway/api/create
        // ZaloPay: https://sb-openapi.zalopay.vn/v2/create

        return switch (paymentMethod) {
            case BANK_TRANSFER -> generateBankTransferUrl(order, clientIp);
            case E_WALLET -> generateEWalletUrl(order);
            case COD -> null; // COD doesn't need payment URL
        };
    }

    @Override
    public void handlePaymentCallback(String transactionId, String status) {
        log.info("Handling payment callback for transaction: {} with status: {}", transactionId, status);

        // TODO: Implement payment callback handling
        // 1. Verify callback signature
        // 2. Find order by transaction ID
        // 3. Update payment status
        // 4. Send notification to user

        // Mock implementation
        if ("SUCCESS".equals(status)) {
            log.info("Payment successful for transaction: {}", transactionId);
            // Update order payment status to PAID
        } else {
            log.warn("Payment failed for transaction: {}", transactionId);
            // Update order payment status to FAILED
        }
    }

    @Override
    public boolean handleVnpayCallback(Map<String, String> callbackParams) {
        if (!verifyVnpaySignature(callbackParams)) {
            log.warn("VNPay callback signature invalid");
            return false;
        }

        String orderCode = callbackParams.get("vnp_TxnRef");
        String responseCode = callbackParams.get("vnp_ResponseCode");
        String transactionStatus = callbackParams.get("vnp_TransactionStatus");

        if (orderCode == null || orderCode.isBlank()) {
            log.warn("VNPay callback missing order code");
            return false;
        }

        Order order = orderRepository.findByOrderCode(orderCode)
                .orElseThrow(() -> new OrderNotFoundException("Order not found for transaction ref: " + orderCode));

        boolean success = "00".equals(responseCode) && "00".equals(transactionStatus);

        if (success) {
            order.setPaymentStatus(PaymentStatus.PAID);
            if (order.getStatus() == OrderStatus.PENDING) {
                order.setStatus(OrderStatus.CONFIRMED);
            }
        } else {
            order.setPaymentStatus(PaymentStatus.FAILED);
        }

        orderRepository.save(order);
        log.info("VNPay callback handled for order {} - success: {}", orderCode, success);
        return success;
    }

    @Override
    public Map<String, String> handleVnpayIpn(Map<String, String> callbackParams) {
        Map<String, String> response = new HashMap<>();

        if (!verifyVnpaySignature(callbackParams)) {
            response.put("RspCode", "97");
            response.put("Message", "Invalid signature");
            return response;
        }

        String orderCode = callbackParams.get("vnp_TxnRef");
        String responseCode = callbackParams.get("vnp_ResponseCode");
        String transactionStatus = callbackParams.get("vnp_TransactionStatus");

        if (orderCode == null || orderCode.isBlank()) {
            response.put("RspCode", "01");
            response.put("Message", "Order not found");
            return response;
        }

        Order order = orderRepository.findByOrderCode(orderCode).orElse(null);
        if (order == null) {
            response.put("RspCode", "01");
            response.put("Message", "Order not found");
            return response;
        }

        if (order.getPaymentStatus() == PaymentStatus.PAID) {
            response.put("RspCode", "02");
            response.put("Message", "Order already confirmed");
            return response;
        }

        boolean success = "00".equals(responseCode) && "00".equals(transactionStatus);
        if (success) {
            order.setPaymentStatus(PaymentStatus.PAID);
            if (order.getStatus() == OrderStatus.PENDING) {
                order.setStatus(OrderStatus.CONFIRMED);
            }
        } else {
            order.setPaymentStatus(PaymentStatus.FAILED);
        }

        orderRepository.save(order);
        response.put("RspCode", "00");
        response.put("Message", "Confirm Success");
        return response;
    }

    @Override
    public boolean processRefund(Long orderId) {
        log.info("Processing refund for order: {}", orderId);

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        // Can only refund paid orders
        if (order.getPaymentStatus() != PaymentStatus.PAID) {
            throw new PaymentFailedException("Cannot refund unpaid order");
        }

        // TODO: Implement real refund with payment gateway
        // Call gateway refund API

        // Mock refund (always successful for now)
        order.setPaymentStatus(PaymentStatus.UNPAID);
        orderRepository.save(order);

        log.info("Refund processed successfully for order: {}", orderId);
        return true;
    }

    /**
     * Process COD payment
     * No online payment needed, just mark as pending
     */
    private String processCODPayment(Order order) {
        log.info("Processing COD payment for order: {}", order.getOrderCode());

        // COD payment will be marked as PAID when order is delivered
        order.setPaymentStatus(PaymentStatus.UNPAID);
        orderRepository.save(order);

        return "COD_PENDING";
    }

    /**
     * Process bank transfer payment
     * Generate payment URL and return transaction ID
     */
    private String processBankTransferPayment(Order order) {
        log.info("Processing bank transfer payment for order: {}", order.getOrderCode());
        order.setPaymentStatus(PaymentStatus.UNPAID);
        orderRepository.save(order);

        // VNPay transaction ref uses orderCode for deterministic mapping in callbacks.
        return order.getOrderCode();
    }

    /**
     * Process e-wallet payment
     * Generate payment URL and return transaction ID
     */
    private String processEWalletPayment(Order order) {
        log.info("Processing e-wallet payment for order: {}", order.getOrderCode());

        // TODO: Integrate with MoMo, ZaloPay, or other e-wallet gateway
        // For now, return mock transaction ID

        String transactionId = generateTransactionId();
        order.setPaymentStatus(PaymentStatus.UNPAID);
        orderRepository.save(order);

        log.info("Generated transaction ID: {} for order: {}", transactionId, order.getOrderCode());
        return transactionId;
    }

    /**
     * Generate bank transfer payment URL
     */
    private String generateBankTransferUrl(Order order, String clientIp) {
        validateVnpayConfiguration();

        LocalDateTime now = LocalDateTime.now();
        String createDate = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String expireDate = now.plusMinutes(15).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

        long amount = Math.round(order.getTotalAmount() * 100);
        String orderInfo = "Thanh toan don hang " + order.getOrderCode();

        Map<String, String> params = new LinkedHashMap<>();
        params.put("vnp_Version", vnpayVersion);
        params.put("vnp_Command", vnpayCommand);
        params.put("vnp_TmnCode", vnpayTmnCode);
        params.put("vnp_Amount", String.valueOf(amount));
        params.put("vnp_CurrCode", vnpayCurrCode);
        params.put("vnp_TxnRef", order.getOrderCode());
        params.put("vnp_OrderInfo", orderInfo);
        params.put("vnp_OrderType", vnpayOrderType);
        params.put("vnp_ReturnUrl", vnpayReturnUrl);
        params.put("vnp_IpAddr", normalizeIp(clientIp));
        params.put("vnp_Locale", vnpayLocale);
        params.put("vnp_CreateDate", createDate);
        params.put("vnp_ExpireDate", expireDate);

        String hashData = buildQuery(params);
        String query = hashData;
        String secureHash = hmacSha512(vnpayHashSecret, hashData);

        String paymentUrl = vnpayPayUrl + "?" + query + "&vnp_SecureHash=" + secureHash;
        log.info("Generated VNPay payment URL for order: {}", order.getOrderCode());
        return paymentUrl;
    }

    /**
     * Generate e-wallet payment URL
     */
    private String generateEWalletUrl(Order order) {
        // TODO: Integrate with MoMo or ZaloPay
        // Build payment URL with order info, amount, return URL, etc.

        String mockUrl = String.format(
                "https://test-payment.momo.vn/v2/gateway/api/create?orderId=%s&amount=%s",
                order.getOrderCode(),
                order.getTotalAmount().longValue()
        );

        log.info("Generated e-wallet URL: {}", mockUrl);
        return mockUrl;
    }

    /**
     * Generate unique transaction ID
     */
    private String generateTransactionId() {
        return "TXN" + System.currentTimeMillis() + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    private boolean verifyVnpaySignature(Map<String, String> callbackParams) {
        String secureHash = callbackParams.get("vnp_SecureHash");
        if (secureHash == null || secureHash.isBlank()) {
            return false;
        }

        Map<String, String> paramsToVerify = new HashMap<>(callbackParams);
        paramsToVerify.remove("vnp_SecureHash");
        paramsToVerify.remove("vnp_SecureHashType");

        String hashData = buildQuery(paramsToVerify);
        String calculated = hmacSha512(vnpayHashSecret, hashData);
        return calculated.equalsIgnoreCase(secureHash);
    }

    private void validateAmount(Order order, Double requestedAmount) {
        if (requestedAmount == null) {
            return;
        }

        double delta = Math.abs(order.getTotalAmount() - requestedAmount);
        if (delta > 1.0) {
            throw new BadRequestException("Requested payment amount does not match order total");
        }
    }

    private void validateVnpayConfiguration() {
        if (!vnpayEnabled) {
            throw new PaymentFailedException("VNPay integration is disabled. Please contact support.");
        }

        if (isBlank(vnpayTmnCode) || isBlank(vnpayHashSecret) || isBlank(vnpayPayUrl) || isBlank(vnpayReturnUrl)) {
            throw new PaymentFailedException("VNPay configuration is incomplete.");
        }
    }

    private String buildQuery(Map<String, String> params) {
        return params.entrySet().stream()
                .filter(entry -> entry.getValue() != null && !entry.getValue().isBlank())
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .map(entry -> {
                    String key = urlEncode(entry.getKey());
                    String value = urlEncode(entry.getValue());
                    return key + "=" + value;
                })
                .collect(Collectors.joining("&"));
    }

    private String hmacSha512(String secret, String data) {
        try {
            Mac mac = Mac.getInstance("HmacSHA512");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
            mac.init(secretKeySpec);
            byte[] bytes = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(bytes);
        } catch (Exception e) {
            throw new PaymentFailedException("Cannot generate VNPay signature");
        }
    }

    private String urlEncode(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }

    private String normalizeIp(String clientIp) {
        if (clientIp == null || clientIp.isBlank() || "0:0:0:0:0:0:0:1".equals(clientIp)) {
            return "127.0.0.1";
        }
        return clientIp;
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }

    private void validateOrderOwnership(Order order, Long userId) {
        if (order.getUser() == null || !order.getUser().getId().equals(userId)) {
            throw new ForbiddenException("You don't have permission to process payment for this order");
        }
    }
}
