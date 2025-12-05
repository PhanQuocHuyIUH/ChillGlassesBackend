package iuh.chillteam.controller;

import iuh.chillteam.dto.statistics.*;
import iuh.chillteam.entity.enums.OrderStatus;
import iuh.chillteam.service.StatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Statistics Controller - Admin analytics and reporting endpoints
 */
@RestController
@RequestMapping("/api/admin/statistics")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Admin - Statistics", description = "Admin endpoints for analytics and statistics (ADMIN ONLY)")
@SecurityRequirement(name = "Bearer Authentication")
@PreAuthorize("hasRole('ADMIN')")
public class StatisticsController {

    private final StatisticsService statisticsService;

    /**
     * Get comprehensive dashboard statistics
     * GET /api/admin/statistics/dashboard
     */
    @GetMapping("/dashboard")
    @Operation(summary = "Get dashboard statistics (Admin)", description = "Get comprehensive dashboard overview including users, orders, revenue, and products stats")
    public ResponseEntity<DashboardStatsDTO> getDashboardStats() {
        log.info("Request to get dashboard statistics");
        DashboardStatsDTO stats = statisticsService.getDashboardStats();
        return ResponseEntity.ok(stats);
    }

    /**
     * Get revenue statistics by period
     * GET /api/admin/statistics/revenue
     * @param startDate Start date (required)
     * @param endDate End date (required)
     * @param period Period type: daily, weekly, monthly (default: daily)
     */
    @GetMapping("/revenue")
    @Operation(summary = "Get revenue by period (Admin)", description = "Get revenue statistics grouped by daily, weekly, or monthly period")
    public ResponseEntity<List<RevenueStatsDTO>> getRevenueByPeriod(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(defaultValue = "daily") String period) {
        log.info("Request to get revenue by period: {} from {} to {}", period, startDate, endDate);
        List<RevenueStatsDTO> stats = statisticsService.getRevenueByPeriod(startDate, endDate, period);
        return ResponseEntity.ok(stats);
    }

    /**
     * Get order distribution by status
     * GET /api/admin/statistics/orders/status
     */
    @GetMapping("/orders/status")
    @Operation(summary = "Get orders by status (Admin)", description = "Get order count and percentage distribution by status")
    public ResponseEntity<List<OrderStatusStatsDTO>> getOrdersByStatus() {
        log.info("Request to get orders by status");
        List<OrderStatusStatsDTO> stats = statisticsService.getOrdersByStatus();
        return ResponseEntity.ok(stats);
    }

    /**
     * Get top selling products
     * GET /api/admin/statistics/products/top-selling
     * @param limit Number of products to return (default: 10)
     */
    @GetMapping("/products/top-selling")
    @Operation(summary = "Get top selling products (Admin)", description = "Get list of best-selling products with quantity and revenue stats")
    public ResponseEntity<List<TopSellingProductDTO>> getTopSellingProducts(
            @RequestParam(defaultValue = "10") int limit) {
        log.info("Request to get top {} selling products", limit);
        List<TopSellingProductDTO> stats = statisticsService.getTopSellingProducts(limit);
        return ResponseEntity.ok(stats);
    }

    /**
     * Get low stock inventory report
     * GET /api/admin/statistics/inventory
     * @param threshold Stock quantity threshold (default: 10)
     */
    @GetMapping("/inventory")
    @Operation(summary = "Get inventory report (Admin)", description = "Get low stock products report with stock status")
    public ResponseEntity<List<InventoryReportDTO>> getInventoryReport(
            @RequestParam(required = false) Integer threshold) {
        log.info("Request to get inventory report with threshold: {}", threshold);
        List<InventoryReportDTO> report = statisticsService.getInventoryReport(threshold);
        return ResponseEntity.ok(report);
    }

    /**
     * Get total revenue between dates
     * GET /api/admin/statistics/revenue/total
     * @param startDate Start date (required)
     * @param endDate End date (required)
     */
    @GetMapping("/revenue/total")
    @Operation(summary = "Get total revenue (Admin)", description = "Get total revenue amount between two dates")
    public ResponseEntity<Double> getRevenueBetweenDates(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        log.info("Request to get total revenue between {} and {}", startDate, endDate);
        Double revenue = statisticsService.getRevenueBetweenDates(startDate, endDate);
        return ResponseEntity.ok(revenue);
    }

    /**
     * Get order count by status
     * GET /api/admin/statistics/orders/count
     * @param status Order status (required)
     */
    @GetMapping("/orders/count")
    @Operation(summary = "Get order count by status (Admin)", description = "Get total number of orders by specific status")
    public ResponseEntity<Long> getOrderCountByStatus(@RequestParam OrderStatus status) {
        log.info("Request to get order count by status: {}", status);
        Long count = statisticsService.getOrderCountByStatus(status);
        return ResponseEntity.ok(count);
    }
}
