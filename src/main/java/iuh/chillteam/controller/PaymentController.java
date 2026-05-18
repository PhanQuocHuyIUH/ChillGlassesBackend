package iuh.chillteam.controller;

import iuh.chillteam.dto.common.ApiResponse;
import iuh.chillteam.entity.enums.PaymentMethod;
import iuh.chillteam.entity.enums.PaymentStatus;
import iuh.chillteam.security.UserDetailsServiceImpl;
import iuh.chillteam.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Payment Controller
 * Handles payment processing and callbacks
 */
@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Payment", description = "Payment processing endpoints")
public class PaymentController {

    private final PaymentService paymentService;

    /**
     * Process payment for order
     */
    @PostMapping("/process/{orderId}")
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Process payment", description = "Process payment for an order")
    public ResponseEntity<ApiResponse<String>> processPayment(
            @PathVariable Long orderId,
            @RequestParam PaymentMethod paymentMethod,
            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUserDetails userDetails
    ) {
        log.info("POST /api/payments/process/{} - Process payment with method: {}", orderId, paymentMethod);
        String transactionId = paymentService.processPayment(orderId, userDetails.getUserId(), paymentMethod);
        return ResponseEntity.ok(ApiResponse.success("Payment processed successfully", transactionId));
    }

    /**
     * Generate payment URL
     */
    @GetMapping("/url/{orderId}")
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Generate payment URL", description = "Generate payment URL for online payment")
    public ResponseEntity<ApiResponse<String>> generatePaymentUrl(
            @PathVariable Long orderId,
            @RequestParam Double amount,
            @RequestParam PaymentMethod paymentMethod,
            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUserDetails userDetails,
            HttpServletRequest request
    ) {
        log.info("GET /api/payments/url/{} - Generate payment URL", orderId);
        String paymentUrl = paymentService.generatePaymentUrl(
                orderId,
                userDetails.getUserId(),
                amount,
                paymentMethod,
                request.getRemoteAddr()
        );
        return ResponseEntity.ok(ApiResponse.success("Payment URL generated successfully", paymentUrl));
    }

    /**
     * Payment callback/webhook (public endpoint)
     */
    @PostMapping("/callback")
    @Operation(summary = "Payment callback", description = "Handle payment gateway callback")
    public ResponseEntity<ApiResponse<Void>> handlePaymentCallback(
            @RequestBody Map<String, String> callbackData
    ) {
        log.info("POST /api/payments/callback - Handle payment callback");

        String transactionId = callbackData.get("transactionId");
        String status = callbackData.get("status");
        String signature = callbackData.get("signature");

        // Verify signature
        boolean isValid = paymentService.verifyPayment(transactionId, signature);
        if (!isValid) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error(400, "Invalid payment signature"));
        }

        // Handle callback
        paymentService.handlePaymentCallback(transactionId, status);

        return ResponseEntity.ok(ApiResponse.success("Payment callback processed successfully"));
    }

    /**
     * VNPay return callback (frontend redirect)
     */
    @GetMapping("/callback/vnpay")
    @Operation(summary = "VNPay return callback", description = "Handle VNPay return callback from user redirect")
    public ResponseEntity<ApiResponse<Boolean>> handleVnpayCallback(
            @RequestParam Map<String, String> callbackParams
    ) {
        boolean success = paymentService.handleVnpayCallback(callbackParams);
        if (!success) {
            return ResponseEntity.badRequest().body(ApiResponse.error(400, "Invalid VNPay callback signature"));
        }

        return ResponseEntity.ok(ApiResponse.success("VNPay callback processed", true));
    }

    /**
     * VNPay IPN callback (server-to-server)
     */
    @GetMapping("/ipn/vnpay")
    @Operation(summary = "VNPay IPN callback", description = "Handle VNPay instant payment notification")
    public ResponseEntity<Map<String, String>> handleVnpayIpn(
            @RequestParam Map<String, String> callbackParams
    ) {
        Map<String, String> response = paymentService.handleVnpayIpn(callbackParams);
        return ResponseEntity.ok(response);
    }

    /**
     * Update payment status (Admin only)
     */
    @PutMapping("/admin/{orderId}/status")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Update payment status (Admin)", description = "Manually update payment status (Admin only)")
    public ResponseEntity<ApiResponse<Void>> updatePaymentStatus(
            @PathVariable Long orderId,
            @RequestParam PaymentStatus paymentStatus
    ) {
        log.info("PUT /api/payments/admin/{}/status - Update payment status to {}", orderId, paymentStatus);
        paymentService.updatePaymentStatus(orderId, paymentStatus);
        return ResponseEntity.ok(ApiResponse.success("Payment status updated successfully"));
    }

    /**
     * Process refund (Admin only)
     */
    @PostMapping("/admin/{orderId}/refund")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Process refund (Admin)", description = "Process refund for cancelled order (Admin only)")
    public ResponseEntity<ApiResponse<Boolean>> processRefund(
            @PathVariable Long orderId
    ) {
        log.info("POST /api/payments/admin/{}/refund - Process refund", orderId);
        boolean success = paymentService.processRefund(orderId);
        return ResponseEntity.ok(ApiResponse.success("Refund processed successfully", success));
    }
}
