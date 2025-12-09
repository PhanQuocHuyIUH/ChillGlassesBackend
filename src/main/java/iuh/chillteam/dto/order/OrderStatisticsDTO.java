package iuh.chillteam.dto.order;

import iuh.chillteam.entity.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Order Statistics DTO
 * Contains order statistics for admin dashboard
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatisticsDTO {

    /**
     * Total number of orders
     */
    private Long totalOrders;

    /**
     * Total revenue (sum of all DELIVERED orders)
     */
    private Double totalRevenue;

    /**
     * Pending orders count
     */
    private Long pendingOrders;

    /**
     * Confirmed orders count
     */
    private Long confirmedOrders;

    /**
     * Processing orders count
     */
    private Long processingOrders;

    /**
     * Shipping orders count
     */
    private Long shippingOrders;

    /**
     * Delivered orders count
     */
    private Long deliveredOrders;

    /**
     * Cancelled orders count
     */
    private Long cancelledOrders;

    /**
     * Order count by status (detailed)
     */
    private Map<OrderStatus, Long> orderCountByStatus;

    /**
     * Average order value
     */
    private Double averageOrderValue;

    /**
     * Total orders today
     */
    private Long ordersToday;

    /**
     * Revenue today
     */
    private Double revenueToday;
}
