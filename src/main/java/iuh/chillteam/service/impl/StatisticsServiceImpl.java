package iuh.chillteam.service.impl;

import iuh.chillteam.dto.statistics.*;
import iuh.chillteam.entity.Order;
import iuh.chillteam.entity.OrderItem;
import iuh.chillteam.entity.Product;
import iuh.chillteam.entity.ProductImage;
import iuh.chillteam.entity.enums.OrderStatus;
import iuh.chillteam.entity.enums.UserRole;
import iuh.chillteam.repository.*;
import iuh.chillteam.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Statistics Service Implementation
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class StatisticsServiceImpl implements StatisticsService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    @Override
    public DashboardStatsDTO getDashboardStats() {
        log.info("Getting dashboard statistics");

        // User Statistics
        Long totalUsers = userRepository.count();
        Long totalCustomers = userRepository.countByRole(UserRole.CUSTOMER);
        Long totalAdmins = userRepository.countByRole(UserRole.ADMIN);
        Long activeUsers = userRepository.findAllActive().stream().count();

        // Order Statistics
        Long totalOrders = orderRepository.count();
        Long pendingOrders = getOrderCountByStatus(OrderStatus.PENDING);
        Long confirmedOrders = getOrderCountByStatus(OrderStatus.CONFIRMED);
        Long processingOrders = getOrderCountByStatus(OrderStatus.PROCESSING);
        Long shippingOrders = getOrderCountByStatus(OrderStatus.SHIPPING);
        Long deliveredOrders = getOrderCountByStatus(OrderStatus.DELIVERED);
        Long cancelledOrders = getOrderCountByStatus(OrderStatus.CANCELLED);

        // Today's order statistics
        LocalDateTime startOfToday = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        Long ordersToday = orderRepository.findAll().stream()
                .filter(o -> o.getOrderDate().isAfter(startOfToday))
                .filter(o -> o.getStatus() != OrderStatus.CANCELLED)
                .count();

        // New users today
        Long newUsersToday = userRepository.findAll().stream()
                .filter(u -> u.getCreatedAt() != null && u.getCreatedAt().isAfter(startOfToday))
                .count();

        // Revenue Statistics - only DELIVERED+PAID or SHIPPING orders
        Double totalRevenue = calculateTotalRevenue();
        Double todayRevenue = getRevenueBetweenDates(LocalDate.now(), LocalDate.now());
        
        LocalDate firstDayOfMonth = LocalDate.now().withDayOfMonth(1);
        LocalDate lastDayOfMonth = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
        Double thisMonthRevenue = getRevenueBetweenDates(firstDayOfMonth, lastDayOfMonth);
        
        LocalDate firstDayOfLastMonth = LocalDate.now().minusMonths(1).withDayOfMonth(1);
        LocalDate lastDayOfLastMonth = LocalDate.now().minusMonths(1).withDayOfMonth(
                LocalDate.now().minusMonths(1).lengthOfMonth());
        Double lastMonthRevenue = getRevenueBetweenDates(firstDayOfLastMonth, lastDayOfLastMonth);

        // Product Statistics
        Long totalProducts = productRepository.count();
        Long activeProducts = productRepository.findAll().stream()
                .filter(Product::getIsActive)
                .count();
        Long outOfStockProducts = productRepository.findAll().stream()
                .filter(p -> p.getStockQuantity() == 0)
                .count();
        Long lowStockProducts = productRepository.findAll().stream()
                .filter(p -> p.getStockQuantity() > 0 && p.getStockQuantity() < 5)
                .count();

        // Calculate Growth Rates
        Double revenueGrowthRate = calculateGrowthRate(lastMonthRevenue, thisMonthRevenue);
        Double orderGrowthRate = 0.0; // TODO: Calculate based on orders count
        Double userGrowthRate = 0.0; // TODO: Calculate based on user registrations

        return DashboardStatsDTO.builder()
                .totalUsers(totalUsers)
                .totalCustomers(totalCustomers)
                .totalAdmins(totalAdmins)
                .activeUsers(activeUsers)
                .totalOrders(totalOrders)
                .pendingOrders(pendingOrders)
                .confirmedOrders(confirmedOrders)
                .processingOrders(processingOrders)
                .shippingOrders(shippingOrders)
                .deliveredOrders(deliveredOrders)
                .cancelledOrders(cancelledOrders)
                .totalRevenue(totalRevenue)
                .todayRevenue(todayRevenue)
                .thisMonthRevenue(thisMonthRevenue)
                .lastMonthRevenue(lastMonthRevenue)
                .totalProducts(totalProducts)
                .activeProducts(activeProducts)
                .outOfStockProducts(outOfStockProducts)
                .lowStockProducts(lowStockProducts)
                .revenueGrowthRate(revenueGrowthRate)
                .orderGrowthRate(orderGrowthRate)
                .userGrowthRate(userGrowthRate)
                .ordersToday(ordersToday)
                .newUsersToday(newUsersToday)
                .build();
    }

    @Override
    public List<RevenueStatsDTO> getRevenueByPeriod(LocalDate startDate, LocalDate endDate, String period) {
        log.info("Getting revenue by period: {} from {} to {}", period, startDate, endDate);

        List<Order> orders = orderRepository.findAll().stream()
                .filter(order -> {
                    LocalDate orderDate = order.getOrderDate().toLocalDate();
                    return !orderDate.isBefore(startDate) && !orderDate.isAfter(endDate);
                })
                .filter(order -> (order.getStatus() == OrderStatus.DELIVERED && order.getPaymentStatus() == iuh.chillteam.entity.enums.PaymentStatus.PAID) 
                        || order.getStatus() == OrderStatus.SHIPPING)
                .filter(order -> order.getStatus() != OrderStatus.CANCELLED)
                .collect(Collectors.toList());

        Map<String, List<Order>> groupedOrders = new HashMap<>();

        if ("daily".equalsIgnoreCase(period)) {
            groupedOrders = orders.stream()
                    .collect(Collectors.groupingBy(order -> 
                            order.getOrderDate().toLocalDate().toString()));
        } else if ("weekly".equalsIgnoreCase(period)) {
            groupedOrders = orders.stream()
                    .collect(Collectors.groupingBy(order -> {
                        LocalDate date = order.getOrderDate().toLocalDate();
                        int year = date.getYear();
                        int week = date.get(java.time.temporal.IsoFields.WEEK_OF_WEEK_BASED_YEAR);
                        return String.format("%d-W%02d", year, week);
                    }));
        } else if ("monthly".equalsIgnoreCase(period)) {
            groupedOrders = orders.stream()
                    .collect(Collectors.groupingBy(order -> {
                        LocalDate date = order.getOrderDate().toLocalDate();
                        return String.format("%d-%02d", date.getYear(), date.getMonthValue());
                    }));
        }

        return groupedOrders.entrySet().stream()
                .map(entry -> {
                    String periodKey = entry.getKey();
                    List<Order> periodOrders = entry.getValue();
                    Long orderCount = (long) periodOrders.size();
                    Double totalRevenue = periodOrders.stream()
                            .mapToDouble(Order::getTotalAmount)
                            .sum();
                    Double averageOrderValue = orderCount > 0 ? totalRevenue / orderCount : 0.0;

                    return RevenueStatsDTO.builder()
                            .period(periodKey)
                            .date(LocalDate.parse(periodKey.split("-")[0] + "-" + 
                                    (periodKey.split("-").length > 1 ? periodKey.split("-")[1] : "01") + 
                                    "-01"))
                            .orderCount(orderCount)
                            .totalRevenue(totalRevenue)
                            .averageOrderValue(averageOrderValue)
                            .build();
                })
                .sorted(Comparator.comparing(RevenueStatsDTO::getPeriod))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderStatusStatsDTO> getOrdersByStatus() {
        log.info("Getting orders by status");

        Long totalOrders = orderRepository.count();

        return Arrays.stream(OrderStatus.values())
                .map(status -> {
                    Long count = getOrderCountByStatus(status);
                    Double percentage = totalOrders > 0 ? (count * 100.0) / totalOrders : 0.0;
                    
                    return OrderStatusStatsDTO.builder()
                            .status(status.name())
                            .count(count)
                            .percentage(Math.round(percentage * 100.0) / 100.0)
                            .build();
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<TopSellingProductDTO> getTopSellingProducts(int limit) {
        log.info("Getting top {} selling products", limit);

        // Get all order items from confirmed orders only (exclude PENDING and CANCELLED)
        List<OrderItem> orderItems = orderItemRepository.findAll().stream()
                .filter(item -> {
                    Order order = item.getOrder();
                    return order.getStatus() == OrderStatus.CONFIRMED
                            || order.getStatus() == OrderStatus.PROCESSING
                            || order.getStatus() == OrderStatus.SHIPPING
                            || order.getStatus() == OrderStatus.DELIVERED;
                })
                .collect(Collectors.toList());

        // Group by product and calculate statistics
        Map<Long, List<OrderItem>> productOrderItems = orderItems.stream()
                .collect(Collectors.groupingBy(item -> item.getProduct().getId()));

        return productOrderItems.entrySet().stream()
                .map(entry -> {
                    Long productId = entry.getKey();
                    List<OrderItem> items = entry.getValue();
                    
                    Product product = items.get(0).getProduct();
                    Long totalQuantitySold = items.stream()
                            .mapToLong(OrderItem::getQuantity)
                            .sum();
                    Double totalRevenue = items.stream()
                            .mapToDouble(OrderItem::getSubtotal)
                            .sum();
                    Long orderCount = (long) items.size();

                    // Get primary image
                    String primaryImage = productImageRepository
                            .findPrimaryImageByProductId(productId)
                            .map(ProductImage::getImageUrl)
                            .orElse(null);

                    return TopSellingProductDTO.builder()
                            .productId(productId)
                            .productName(product.getName())
                            .productSlug(product.getSlug())
                            .productImage(primaryImage)
                            .brand(product.getBrand())
                            .totalQuantitySold(totalQuantitySold)
                            .totalRevenue(totalRevenue)
                            .orderCount(orderCount)
                            .build();
                })
                .sorted(Comparator.comparing(TopSellingProductDTO::getTotalQuantitySold).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    @Override
    public List<InventoryReportDTO> getInventoryReport(Integer threshold) {
        log.info("Getting inventory report with threshold: {}", threshold);

        if (threshold == null) {
            threshold = 5;
        }

        final Integer finalThreshold = threshold;

        return productRepository.findAll().stream()
                .filter(product -> product.getStockQuantity() <= finalThreshold)
                .map(product -> {
                    String stockStatus;
                    if (product.getStockQuantity() == 0) {
                        stockStatus = "OUT_OF_STOCK";
                    } else if (product.getStockQuantity() < 10) {
                        stockStatus = "LOW_STOCK";
                    } else {
                        stockStatus = "IN_STOCK";
                    }

                    return InventoryReportDTO.builder()
                            .productId(product.getId())
                            .productName(product.getName())
                            .productSlug(product.getSlug())
                            .brand(product.getBrand())
                            .category(product.getCategory().getName())
                            .stockQuantity(product.getStockQuantity())
                            .stockStatus(stockStatus)
                            .isActive(product.getIsActive())
                            .build();
                })
                .sorted(Comparator.comparing(InventoryReportDTO::getStockQuantity))
                .collect(Collectors.toList());
    }

    @Override
    public Double getRevenueBetweenDates(LocalDate startDate, LocalDate endDate) {
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(23, 59, 59);

        return orderRepository.findAll().stream()
                .filter(order -> {
                    LocalDateTime orderDate = order.getOrderDate();
                    return !orderDate.isBefore(startDateTime) && !orderDate.isAfter(endDateTime);
                })
                .filter(order -> (order.getStatus() == OrderStatus.DELIVERED && order.getPaymentStatus() == iuh.chillteam.entity.enums.PaymentStatus.PAID) 
                        || order.getStatus() == OrderStatus.SHIPPING)
                .filter(order -> order.getStatus() != OrderStatus.CANCELLED)
                .mapToDouble(Order::getTotalAmount)
                .sum();
    }

    @Override
    public Long getOrderCountByStatus(OrderStatus status) {
        return orderRepository.findByStatus(status, null).getTotalElements();
    }

    /**
     * Calculate total revenue (all delivered orders)
     */
    private Double calculateTotalRevenue() {
        return orderRepository.findAll().stream()
                .filter(order -> (order.getStatus() == OrderStatus.DELIVERED && order.getPaymentStatus() == iuh.chillteam.entity.enums.PaymentStatus.PAID) 
                        || order.getStatus() == OrderStatus.SHIPPING)
                .filter(order -> order.getStatus() != OrderStatus.CANCELLED)
                .mapToDouble(Order::getTotalAmount)
                .sum();
    }

    /**
     * Calculate growth rate between two periods
     */
    private Double calculateGrowthRate(Double oldValue, Double newValue) {
        if (oldValue == null || oldValue == 0) {
            return 0.0;
        }
        return ((newValue - oldValue) / oldValue) * 100.0;
    }
}
