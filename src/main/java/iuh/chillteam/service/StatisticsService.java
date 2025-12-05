package iuh.chillteam.service;

import iuh.chillteam.dto.statistics.*;
import iuh.chillteam.entity.enums.OrderStatus;

import java.time.LocalDate;
import java.util.List;

/**
 * Statistics Service Interface
 * Handles statistics and analytics for admin dashboard
 */
public interface StatisticsService {

    /**
     * Get dashboard overview statistics
     */
    DashboardStatsDTO getDashboardStats();

    /**
     * Get revenue statistics by period
     * @param startDate Start date
     * @param endDate End date
     * @param period "daily", "weekly", "monthly"
     */
    List<RevenueStatsDTO> getRevenueByPeriod(LocalDate startDate, LocalDate endDate, String period);

    /**
     * Get order statistics by status
     */
    List<OrderStatusStatsDTO> getOrdersByStatus();

    /**
     * Get top selling products
     * @param limit Number of products to return
     */
    List<TopSellingProductDTO> getTopSellingProducts(int limit);

    /**
     * Get inventory report (low stock products)
     * @param threshold Stock threshold (default 10)
     */
    List<InventoryReportDTO> getInventoryReport(Integer threshold);

    /**
     * Get revenue for a specific date range
     */
    Double getRevenueBetweenDates(LocalDate startDate, LocalDate endDate);

    /**
     * Get order count by status
     */
    Long getOrderCountByStatus(OrderStatus status);
}
