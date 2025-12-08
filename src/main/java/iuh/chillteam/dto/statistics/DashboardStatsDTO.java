package iuh.chillteam.dto.statistics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for Dashboard Statistics
 * Tổng quan thống kê cho admin dashboard
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardStatsDTO {

    // User Statistics
    private Long totalUsers;
    private Long totalCustomers;
    private Long totalAdmins;
    private Long activeUsers;

    // Order Statistics
    private Long totalOrders;
    private Long pendingOrders;
    private Long confirmedOrders;
    private Long processingOrders;
    private Long shippingOrders;
    private Long deliveredOrders;
    private Long cancelledOrders;

    // Revenue Statistics
    private Double totalRevenue;
    private Double todayRevenue;
    private Double thisMonthRevenue;
    private Double lastMonthRevenue;

    // Product Statistics
    private Long totalProducts;
    private Long activeProducts;
    private Long outOfStockProducts;
    private Long lowStockProducts; // Stock < 10

    // Growth Rates (optional)
    private Double revenueGrowthRate; // Compared to last month
    private Double orderGrowthRate;   // Compared to last month
    private Double userGrowthRate;    // Compared to last month
}
