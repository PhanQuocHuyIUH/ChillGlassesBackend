package iuh.chillteam.service;

import iuh.chillteam.dto.order.*;
import iuh.chillteam.entity.enums.OrderStatus;
import iuh.chillteam.entity.enums.ShippingMethod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Order Service Interface
 */
public interface OrderService {

    /**
     * Create order from cart
     */
    OrderDTO createOrder(Long userId, CreateOrderRequest request);

    /**
     * Get order by ID
     */
    OrderDTO getOrderById(Long orderId, Long userId);

    /**
     * Get order by order code
     */
    OrderDTO getOrderByCode(String orderCode, Long userId);

    /**
     * Get all orders for user
     */
    Page<OrderSummaryDTO> getUserOrders(Long userId, Pageable pageable);

    /**
     * Get orders by status for user
     */
    List<OrderSummaryDTO> getUserOrdersByStatus(Long userId, OrderStatus status);

    /**
     * Get all orders (Admin only)
     */
    Page<OrderSummaryDTO> getAllOrders(Pageable pageable);

    /**
     * Get orders by status (Admin only)
     */
    Page<OrderSummaryDTO> getOrdersByStatus(OrderStatus status, Pageable pageable);

    /**
     * Update order status (Admin only)
     */
    OrderDTO updateOrderStatus(Long orderId, UpdateOrderStatusRequest request);

    /**
     * Cancel order
     */
    /**
     * Cancel order_Sửa giúp chỉnh lưu lại được lý do hủy đơn hàng vào cột notes trong bảng order
     */
    OrderDTO cancelOrder(Long orderId, Long userId, CancelOrderRequest request);


    /**
     * Mark payment success for an order (online payment mock)
     */
    OrderDTO markPaymentSuccess(Long orderId, Long userId);

    /**
     * Mark payment fail for an order (online payment mock)
     */
    OrderDTO markPaymentFail(Long orderId, Long userId);



    /**
     * Calculate shipping fee
     */
    Double calculateShippingFee(ShippingMethod shippingMethod, Double orderAmount);
}
