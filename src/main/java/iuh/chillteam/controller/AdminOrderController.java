package iuh.chillteam.controller;

import iuh.chillteam.dto.common.ApiResponse;
import iuh.chillteam.dto.common.PageResponse;
import iuh.chillteam.dto.order.*;
import iuh.chillteam.entity.enums.OrderStatus;
import iuh.chillteam.entity.enums.PaymentStatus;
import iuh.chillteam.service.AdminOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * Admin Order Controller
 * Handles all admin order management operations
 */
@RestController
@RequestMapping("/api/admin/orders")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Admin - Order Management", description = "Admin endpoints for order management")
@SecurityRequirement(name = "Bearer Authentication")
@PreAuthorize("hasRole('ADMIN')")
public class AdminOrderController {

    private final AdminOrderService adminOrderService;

    /**
     * Get all orders with advanced filtering and pagination
     * GET /api/admin/orders
     */
    @GetMapping
    @Operation(
        summary = "Get all orders (Admin)",
        description = "Get all orders with advanced filtering: search by order code/customer name, filter by status/date range, sort by any field"
    )
    public ResponseEntity<ApiResponse<PageResponse<OrderSummaryDTO>>> getAllOrders(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) OrderStatus status,
            @RequestParam(required = false) PaymentStatus paymentStatus,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "orderDate") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortDir
    ) {
        log.info("GET /api/admin/orders - Get all orders with filters");

        Sort sort = sortDir.equalsIgnoreCase("ASC") ? 
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<OrderSummaryDTO> orders = adminOrderService.getAllOrdersWithFilters(
            search, status, paymentStatus, startDate, endDate, pageable
        );

        PageResponse<OrderSummaryDTO> pageResponse = PageResponse.<OrderSummaryDTO>builder()
                .content(orders.getContent())
                .pageNumber(orders.getNumber())
                .pageSize(orders.getSize())
                .totalElements(orders.getTotalElements())
                .totalPages(orders.getTotalPages())
                .last(orders.isLast())
                .build();

        return ResponseEntity.ok(ApiResponse.success("Orders retrieved successfully", pageResponse));
    }

    /**
     * Get order detail by ID
     * GET /api/admin/orders/{id}
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get order detail (Admin)", description = "Get full order details including items")
    public ResponseEntity<ApiResponse<OrderDTO>> getOrderById(@PathVariable Long id) {
        log.info("GET /api/admin/orders/{} - Get order detail", id);
        OrderDTO order = adminOrderService.getOrderDetailById(id);
        return ResponseEntity.ok(ApiResponse.success("Order retrieved successfully", order));
    }

    /**
     * Update order status
     * PUT /api/admin/orders/{id}/status
     */
    @PutMapping("/{id}/status")
    @Operation(
        summary = "Update order status (Admin)",
        description = "Update order status. Valid transitions: PENDING→CONFIRMED→PROCESSING→SHIPPING→DELIVERED or PENDING/PROCESSING→CANCELLED"
    )
    public ResponseEntity<ApiResponse<OrderDTO>> updateOrderStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateOrderStatusRequest request
    ) {
        log.info("PUT /api/admin/orders/{}/status - Update status to {}", id, request.getStatus());
        OrderDTO order = adminOrderService.updateOrderStatus(id, request);
        return ResponseEntity.ok(ApiResponse.success("Order status updated successfully", order));
    }

    /**
     * Update payment status
     * PUT /api/admin/orders/{id}/payment-status
     */
    @PutMapping("/{id}/payment-status")
    @Operation(
        summary = "Update payment status (Admin)",
        description = "Manually update payment status. Use this for bank transfer confirmation or payment verification"
    )
    public ResponseEntity<ApiResponse<OrderDTO>> updatePaymentStatus(
            @PathVariable Long id,
            @RequestParam PaymentStatus paymentStatus
    ) {
        log.info("PUT /api/admin/orders/{}/payment-status - Update to {}", id, paymentStatus);
        OrderDTO order = adminOrderService.updatePaymentStatus(id, paymentStatus);
        return ResponseEntity.ok(ApiResponse.success("Payment status updated successfully", order));
    }

    /**
     * Confirm order (PENDING → CONFIRMED)
     * POST /api/admin/orders/{id}/confirm
     */
    @PostMapping("/{id}/confirm")
    @Operation(
        summary = "Confirm order (Admin)",
        description = "Confirm a pending order. This changes status from PENDING to CONFIRMED"
    )
    public ResponseEntity<ApiResponse<OrderDTO>> confirmOrder(@PathVariable Long id) {
        log.info("POST /api/admin/orders/{}/confirm - Confirm order", id);
        OrderDTO order = adminOrderService.confirmOrder(id);
        return ResponseEntity.ok(ApiResponse.success("Order confirmed successfully", order));
    }

    /**
     * Start processing order (CONFIRMED → PROCESSING)
     * POST /api/admin/orders/{id}/process
     */
    @PostMapping("/{id}/process")
    @Operation(
        summary = "Start processing order (Admin)",
        description = "Mark order as processing. This changes status from CONFIRMED to PROCESSING"
    )
    public ResponseEntity<ApiResponse<OrderDTO>> processOrder(@PathVariable Long id) {
        log.info("POST /api/admin/orders/{}/process - Start processing", id);
        OrderDTO order = adminOrderService.processOrder(id);
        return ResponseEntity.ok(ApiResponse.success("Order is now being processed", order));
    }

    /**
     * Ship order (PROCESSING → SHIPPING)
     * POST /api/admin/orders/{id}/ship
     */
    @PostMapping("/{id}/ship")
    @Operation(
        summary = "Ship order (Admin)",
        description = "Mark order as shipping. This changes status from PROCESSING to SHIPPING"
    )
    public ResponseEntity<ApiResponse<OrderDTO>> shipOrder(@PathVariable Long id) {
        log.info("POST /api/admin/orders/{}/ship - Mark as shipping", id);
        OrderDTO order = adminOrderService.shipOrder(id);
        return ResponseEntity.ok(ApiResponse.success("Order is now shipping", order));
    }

    /**
     * Complete order (SHIPPING → DELIVERED)
     * POST /api/admin/orders/{id}/deliver
     */
    @PostMapping("/{id}/deliver")
    @Operation(
        summary = "Complete order delivery (Admin)",
        description = "Mark order as delivered. This changes status from SHIPPING to DELIVERED and marks payment as PAID for COD orders"
    )
    public ResponseEntity<ApiResponse<OrderDTO>> deliverOrder(@PathVariable Long id) {
        log.info("POST /api/admin/orders/{}/deliver - Mark as delivered", id);
        OrderDTO order = adminOrderService.deliverOrder(id);
        return ResponseEntity.ok(ApiResponse.success("Order delivered successfully", order));
    }

    /**
     * Cancel order with reason
     * POST /api/admin/orders/{id}/cancel
     */
    @PostMapping("/{id}/cancel")
    @Operation(
        summary = "Cancel order (Admin)",
        description = "Cancel an order with reason. Can only cancel PENDING or CONFIRMED orders. Stock will be restored."
    )
    public ResponseEntity<ApiResponse<OrderDTO>> cancelOrder(
            @PathVariable Long id,
            @RequestParam String reason
    ) {
        log.info("POST /api/admin/orders/{}/cancel - Cancel order with reason: {}", id, reason);
        OrderDTO order = adminOrderService.cancelOrderByAdmin(id, reason);
        return ResponseEntity.ok(ApiResponse.success("Order cancelled successfully", order));
    }

    /**
     * Get order statistics
     * GET /api/admin/orders/statistics
     */
    @GetMapping("/statistics")
    @Operation(
        summary = "Get order statistics (Admin)",
        description = "Get order statistics including total orders, revenue, status distribution"
    )
    public ResponseEntity<ApiResponse<OrderStatisticsDTO>> getOrderStatistics(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
    ) {
        log.info("GET /api/admin/orders/statistics - Get statistics");
        OrderStatisticsDTO statistics = adminOrderService.getOrderStatistics(startDate, endDate);
        return ResponseEntity.ok(ApiResponse.success("Statistics retrieved successfully", statistics));
    }

    /**
     * Get orders count by status
     * GET /api/admin/orders/count-by-status
     */
    @GetMapping("/count-by-status")
    @Operation(summary = "Get order count by status (Admin)", description = "Get count of orders grouped by status")
    public ResponseEntity<ApiResponse<?>> getOrderCountByStatus() {
        log.info("GET /api/admin/orders/count-by-status");
        var counts = adminOrderService.getOrderCountByStatus();
        return ResponseEntity.ok(ApiResponse.success("Order counts retrieved successfully", counts));
    }
}
