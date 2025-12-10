package iuh.chillteam.controller;

import iuh.chillteam.dto.common.ApiResponse;
import iuh.chillteam.dto.common.PageResponse;
import iuh.chillteam.dto.order.*;
import iuh.chillteam.entity.enums.OrderStatus;
import iuh.chillteam.security.UserDetailsServiceImpl;
import iuh.chillteam.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Order Controller
 * Handles order operations
 */
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Order", description = "Order management endpoints")
@SecurityRequirement(name = "Bearer Authentication")
public class OrderController {

    private final OrderService orderService;

    /**
     * Create order from cart
     */
    @PostMapping
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Create order", description = "Create order from current cart")
    public ResponseEntity<ApiResponse<OrderDTO>> createOrder(
            @Valid @RequestBody CreateOrderRequest request,
            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUserDetails userDetails
    ) {
        log.info("POST /api/orders - Create order for user: {}", userDetails.getUsername());
        OrderDTO order = orderService.createOrder(userDetails.getUserId(), request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Order created successfully", order));
    }

    /**
     * Get order by ID
     */
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Get order by ID", description = "Get order details by ID")
    public ResponseEntity<ApiResponse<OrderDTO>> getOrderById(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUserDetails userDetails
    ) {
        log.info("GET /api/orders/{} - Get order", id);
        OrderDTO order = orderService.getOrderById(id, userDetails.getUserId());
        return ResponseEntity.ok(ApiResponse.success("Order retrieved successfully", order));
    }

    /**
     * Get order by order code
     */
    @GetMapping("/code/{orderCode}")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Get order by code", description = "Get order details by order code")
    public ResponseEntity<ApiResponse<OrderDTO>> getOrderByCode(
            @PathVariable String orderCode,
            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUserDetails userDetails
    ) {
        log.info("GET /api/orders/code/{} - Get order by code", orderCode);
        OrderDTO order = orderService.getOrderByCode(orderCode, userDetails.getUserId());
        return ResponseEntity.ok(ApiResponse.success("Order retrieved successfully", order));
    }

    /**
     * Get my orders (paginated)
     */
    @GetMapping("/my-orders")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Get my orders", description = "Get all orders for current user")
    public ResponseEntity<ApiResponse<PageResponse<OrderSummaryDTO>>> getMyOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUserDetails userDetails
    ) {
        log.info("GET /api/orders/my-orders - Get orders for user: {}", userDetails.getUsername());
        Pageable pageable = PageRequest.of(page, size);
        Page<OrderSummaryDTO> orders = orderService.getUserOrders(userDetails.getUserId(), pageable);

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
     * Get my orders by status
     */
    @GetMapping("/my-orders/status/{status}")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Get my orders by status", description = "Get user orders filtered by status")
    public ResponseEntity<ApiResponse<List<OrderSummaryDTO>>> getMyOrdersByStatus(
            @PathVariable OrderStatus status,
            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUserDetails userDetails
    ) {
        log.info("GET /api/orders/my-orders/status/{} - Get orders by status", status);
        List<OrderSummaryDTO> orders = orderService.getUserOrdersByStatus(userDetails.getUserId(), status);
        return ResponseEntity.ok(ApiResponse.success("Orders retrieved successfully", orders));
    }

    /**
     * Cancel order
     */
    @PostMapping("/{id}/cancel")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Cancel order", description = "Cancel an order (only PENDING or PROCESSING)")
    public ResponseEntity<ApiResponse<OrderDTO>> cancelOrder(
            @PathVariable Long id,
            @Valid @RequestBody(required = false) CancelOrderRequest request,
            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUserDetails userDetails
    ) {
        log.info("POST /api/orders/{}/cancel - Cancel order", id);
        OrderDTO order = orderService.cancelOrder(id, userDetails.getUserId(), request);
        return ResponseEntity.ok(ApiResponse.success("Order cancelled successfully", order));
    }



    // ========== ADMIN ENDPOINTS ==========

    /**
     * Get all orders (Admin only)
     */
    @GetMapping("/admin/all")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all orders (Admin)", description = "Get all orders with pagination (Admin only)")
    public ResponseEntity<ApiResponse<PageResponse<OrderSummaryDTO>>> getAllOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        log.info("GET /api/orders/admin/all - Get all orders (Admin)");
        Pageable pageable = PageRequest.of(page, size);
        Page<OrderSummaryDTO> orders = orderService.getAllOrders(pageable);

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
     * Get orders by status (Admin only)
     */
    @GetMapping("/admin/status/{status}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get orders by status (Admin)", description = "Get orders filtered by status (Admin only)")
    public ResponseEntity<ApiResponse<PageResponse<OrderSummaryDTO>>> getOrdersByStatus(
            @PathVariable OrderStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        log.info("GET /api/orders/admin/status/{} - Get orders by status (Admin)", status);
        Pageable pageable = PageRequest.of(page, size);
        Page<OrderSummaryDTO> orders = orderService.getOrdersByStatus(status, pageable);

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
     * Update order status (Admin only)
     */
    @PutMapping("/admin/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update order status (Admin)", description = "Update order status (Admin only)")
    public ResponseEntity<ApiResponse<OrderDTO>> updateOrderStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateOrderStatusRequest request
    ) {
        log.info("PUT /api/orders/admin/{}/status - Update order status (Admin)", id);
        OrderDTO order = orderService.updateOrderStatus(id, request);
        return ResponseEntity.ok(ApiResponse.success("Order status updated successfully", order));
    }

    /**
     * Mark payment success for online orders (mock)
     */
    @PostMapping("/{id}/payment/success")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Mark payment success", description = "Mock online payment success for an order")
    public ResponseEntity<ApiResponse<OrderDTO>> markPaymentSuccess(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUserDetails userDetails
    ) {
        log.info("POST /api/orders/{}/payment/success - Mark payment success", id);
        OrderDTO order = orderService.markPaymentSuccess(id, userDetails.getUserId());
        return ResponseEntity.ok(ApiResponse.success("Payment marked as success", order));
    }

    /**
     * Mark payment failed for online orders (mock)
     */
    @PostMapping("/{id}/payment/fail")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Mark payment failed", description = "Mock online payment failure for an order")
    public ResponseEntity<ApiResponse<OrderDTO>> markPaymentFail(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUserDetails userDetails
    ) {
        log.info("POST /api/orders/{}/payment/fail - Mark payment failed", id);
        OrderDTO order = orderService.markPaymentFail(id, userDetails.getUserId());
        return ResponseEntity.ok(ApiResponse.success("Payment marked as failed", order));
    }


}
