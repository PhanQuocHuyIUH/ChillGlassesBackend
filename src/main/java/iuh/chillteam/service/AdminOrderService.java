package iuh.chillteam.service;

import iuh.chillteam.dto.order.*;
import iuh.chillteam.entity.enums.OrderStatus;
import iuh.chillteam.entity.enums.PaymentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Admin Order Service Interface
 * Handles admin-specific order management operations
 */
public interface AdminOrderService {

    /**
     * Get all orders with advanced filtering
     */
    Page<OrderSummaryDTO> getAllOrdersWithFilters(
        String search,
        OrderStatus status,
        PaymentStatus paymentStatus,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Pageable pageable
    );

    /**
     * Get order detail by ID (admin can view any order)
     */
    OrderDTO getOrderDetailById(Long orderId);

    /**
     * Update order status
     */
    OrderDTO updateOrderStatus(Long orderId, UpdateOrderStatusRequest request);

    /**
     * Update payment status
     */
    OrderDTO updatePaymentStatus(Long orderId, PaymentStatus paymentStatus);

    /**
     * Confirm order (PENDING → CONFIRMED)
     */
    OrderDTO confirmOrder(Long orderId);

    /**
     * Start processing order (CONFIRMED → PROCESSING)
     */
    OrderDTO processOrder(Long orderId);

    /**
     * Ship order (PROCESSING → SHIPPING)
     */
    OrderDTO shipOrder(Long orderId);

    /**
     * Deliver order (SHIPPING → DELIVERED)
     */
    OrderDTO deliverOrder(Long orderId);

    /**
     * Cancel order by admin
     */
    OrderDTO cancelOrderByAdmin(Long orderId, String reason);

    /**
     * Get order statistics
     */
    OrderStatisticsDTO getOrderStatistics(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Get order count by status
     */
    Map<OrderStatus, Long> getOrderCountByStatus();
}
