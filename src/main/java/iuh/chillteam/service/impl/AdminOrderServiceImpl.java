package iuh.chillteam.service.impl;

import iuh.chillteam.dto.order.*;
import iuh.chillteam.entity.*;
import iuh.chillteam.entity.enums.*;
import iuh.chillteam.exception.*;
import iuh.chillteam.repository.*;
import iuh.chillteam.service.AdminOrderService;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Admin Order Service Implementation
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AdminOrderServiceImpl implements AdminOrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<OrderSummaryDTO> getAllOrdersWithFilters(
            String search,
            OrderStatus status,
            PaymentStatus paymentStatus,
            LocalDateTime startDate,
            LocalDateTime endDate,
            Pageable pageable
    ) {
        log.info("Getting all orders with filters - search: {}, status: {}, paymentStatus: {}", 
                search, status, paymentStatus);

        // Build query manually since we don't have JpaSpecificationExecutor
        // Use simple repository methods or build custom query
        Page<Order> orders;
        
        if (search != null && !search.trim().isEmpty()) {
            // Search by order code or customer name
            orders = orderRepository.searchOrders(search, status, paymentStatus, startDate, endDate, pageable);
        } else if (status != null) {
            orders = orderRepository.findByStatus(status, pageable);
        } else {
            orders = orderRepository.findAllOrders(pageable);
        }
        
        return orders.map(this::convertToSummaryDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderDTO getOrderDetailById(Long orderId) {
        log.info("Getting order detail by ID: {}", orderId);

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + orderId));

        return convertToDTO(order);
    }

    @Override
    public OrderDTO updateOrderStatus(Long orderId, UpdateOrderStatusRequest request) {
        log.info("Updating order {} status to {}", orderId, request.getStatus());

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + orderId));

        // Validate status transition
        validateStatusTransition(order.getStatus(), request.getStatus());

        // Update status
        order.setStatus(request.getStatus());

        // If order is delivered and payment method is COD, mark as paid
        if (request.getStatus() == OrderStatus.DELIVERED && 
            order.getPaymentMethod() == PaymentMethod.COD &&
            order.getPaymentStatus() == PaymentStatus.UNPAID) {
            order.setPaymentStatus(PaymentStatus.PAID);
            log.info("COD order {} marked as PAID upon delivery", orderId);
        }

        // If order is cancelled, restore stock
        if (request.getStatus() == OrderStatus.CANCELLED) {
            restoreStock(orderId);
            log.info("Stock restored for cancelled order {}", orderId);
        }

        order = orderRepository.save(order);
        log.info("Order {} status updated to {}", orderId, request.getStatus());

        return convertToDTO(order);
    }

    @Override
    public OrderDTO updatePaymentStatus(Long orderId, PaymentStatus paymentStatus) {
        log.info("Updating order {} payment status to {}", orderId, paymentStatus);

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + orderId));

        order.setPaymentStatus(paymentStatus);
        order = orderRepository.save(order);

        log.info("Order {} payment status updated to {}", orderId, paymentStatus);
        return convertToDTO(order);
    }

    @Override
    public OrderDTO confirmOrder(Long orderId) {
        log.info("Confirming order {}", orderId);

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + orderId));

        if (order.getStatus() != OrderStatus.PENDING) {
            throw new BadRequestException("Only PENDING orders can be confirmed. Current status: " + order.getStatus());
        }

        order.setStatus(OrderStatus.CONFIRMED);
        order = orderRepository.save(order);

        log.info("Order {} confirmed", orderId);
        return convertToDTO(order);
    }

    @Override
    public OrderDTO processOrder(Long orderId) {
        log.info("Starting to process order {}", orderId);

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + orderId));

        if (order.getStatus() != OrderStatus.CONFIRMED) {
            throw new BadRequestException("Only CONFIRMED orders can be processed. Current status: " + order.getStatus());
        }

        order.setStatus(OrderStatus.PROCESSING);
        order = orderRepository.save(order);

        log.info("Order {} is now being processed", orderId);
        return convertToDTO(order);
    }

    @Override
    public OrderDTO shipOrder(Long orderId) {
        log.info("Shipping order {}", orderId);

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + orderId));

        if (order.getStatus() != OrderStatus.PROCESSING) {
            throw new BadRequestException("Only PROCESSING orders can be shipped. Current status: " + order.getStatus());
        }

        order.setStatus(OrderStatus.SHIPPING);
        order = orderRepository.save(order);

        log.info("Order {} is now shipping", orderId);
        return convertToDTO(order);
    }

    @Override
    public OrderDTO deliverOrder(Long orderId) {
        log.info("Delivering order {}", orderId);

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + orderId));

        if (order.getStatus() != OrderStatus.SHIPPING) {
            throw new BadRequestException("Only SHIPPING orders can be delivered. Current status: " + order.getStatus());
        }

        order.setStatus(OrderStatus.DELIVERED);

        // Auto-mark COD orders as PAID when delivered
        if (order.getPaymentMethod() == PaymentMethod.COD && order.getPaymentStatus() == PaymentStatus.UNPAID) {
            order.setPaymentStatus(PaymentStatus.PAID);
            log.info("COD order {} automatically marked as PAID upon delivery", orderId);
        }

        order = orderRepository.save(order);

        log.info("Order {} delivered successfully", orderId);
        return convertToDTO(order);
    }

    @Override
    public OrderDTO cancelOrderByAdmin(Long orderId, String reason) {
        log.info("Admin cancelling order {} with reason: {}", orderId, reason);

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + orderId));

        // Can only cancel PENDING, CONFIRMED, or PROCESSING orders
        if (order.getStatus() != OrderStatus.PENDING && 
            order.getStatus() != OrderStatus.CONFIRMED &&
            order.getStatus() != OrderStatus.PROCESSING) {
            throw new BadRequestException("Cannot cancel order with status: " + order.getStatus());
        }

        order.setStatus(OrderStatus.CANCELLED);
        if (reason != null && !reason.trim().isEmpty()) {
            order.setNotes((order.getNotes() != null ? order.getNotes() + "\n" : "") + 
                          "Cancellation reason: " + reason);
        }

        // Restore stock
        restoreStock(orderId);

        order = orderRepository.save(order);

        log.info("Order {} cancelled by admin", orderId);
        return convertToDTO(order);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderStatisticsDTO getOrderStatistics(LocalDateTime startDate, LocalDateTime endDate) {
        log.info("Getting order statistics from {} to {}", startDate, endDate);

        // Get orders by date range
        List<Order> orders;
        if (startDate != null && endDate != null) {
            orders = orderRepository.findByOrderDateBetween(startDate, endDate);
        } else if (startDate != null) {
            orders = orderRepository.findByOrderDateAfter(startDate);
        } else if (endDate != null) {
            orders = orderRepository.findByOrderDateBefore(endDate);
        } else {
            orders = orderRepository.findAll();
        }

        // Calculate statistics
        Long totalOrders = (long) orders.size();
        
        Double totalRevenue = orders.stream()
                .filter(o -> o.getStatus() == OrderStatus.DELIVERED)
                .mapToDouble(Order::getTotalAmount)
                .sum();

        Map<OrderStatus, Long> countByStatus = orders.stream()
                .collect(Collectors.groupingBy(Order::getStatus, Collectors.counting()));

        Double averageOrderValue = totalOrders > 0 ? totalRevenue / totalOrders : 0.0;

        // Today's statistics
        LocalDateTime startOfToday = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        Long ordersToday = orders.stream()
                .filter(o -> o.getOrderDate().isAfter(startOfToday))
                .count();

        Double revenueToday = orders.stream()
                .filter(o -> o.getOrderDate().isAfter(startOfToday))
                .filter(o -> o.getStatus() == OrderStatus.DELIVERED)
                .mapToDouble(Order::getTotalAmount)
                .sum();

        return OrderStatisticsDTO.builder()
                .totalOrders(totalOrders)
                .totalRevenue(totalRevenue)
                .pendingOrders(countByStatus.getOrDefault(OrderStatus.PENDING, 0L))
                .confirmedOrders(countByStatus.getOrDefault(OrderStatus.CONFIRMED, 0L))
                .processingOrders(countByStatus.getOrDefault(OrderStatus.PROCESSING, 0L))
                .shippingOrders(countByStatus.getOrDefault(OrderStatus.SHIPPING, 0L))
                .deliveredOrders(countByStatus.getOrDefault(OrderStatus.DELIVERED, 0L))
                .cancelledOrders(countByStatus.getOrDefault(OrderStatus.CANCELLED, 0L))
                .orderCountByStatus(countByStatus)
                .averageOrderValue(averageOrderValue)
                .ordersToday(ordersToday)
                .revenueToday(revenueToday)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public Map<OrderStatus, Long> getOrderCountByStatus() {
        log.info("Getting order count by status");

        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .collect(Collectors.groupingBy(Order::getStatus, Collectors.counting()));
    }

    /**
     * Validate status transition
     */
    private void validateStatusTransition(OrderStatus currentStatus, OrderStatus newStatus) {
        log.debug("Validating status transition from {} to {}", currentStatus, newStatus);

        // Allow same status (no change)
        if (currentStatus == newStatus) {
            return;
        }

        // Define valid transitions
        boolean isValidTransition = switch (currentStatus) {
            case PENDING -> newStatus == OrderStatus.CONFIRMED || newStatus == OrderStatus.CANCELLED;
            case CONFIRMED -> newStatus == OrderStatus.PROCESSING || newStatus == OrderStatus.CANCELLED;
            case PROCESSING -> newStatus == OrderStatus.SHIPPING || newStatus == OrderStatus.CANCELLED;
            case SHIPPING -> newStatus == OrderStatus.DELIVERED;
            case DELIVERED, CANCELLED, REFUNDED -> false; // Cannot change from these states
            default -> false;
        };

        if (!isValidTransition) {
            throw new BadRequestException(
                String.format("Invalid status transition from %s to %s", currentStatus, newStatus)
            );
        }
    }

    /**
     * Restore stock when order is cancelled
     */
    private void restoreStock(Long orderId) {
        log.info("Restoring stock for cancelled order: {}", orderId);

        List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderId);
        for (OrderItem item : orderItems) {
            Product product = item.getProduct();
            Integer currentStock = product.getStockQuantity() != null ? product.getStockQuantity() : 0;
            product.setStockQuantity(currentStock + item.getQuantity());
            productRepository.save(product);
            log.debug("Restored {} units of product {}", item.getQuantity(), product.getId());
        }
    }

    /**
     * Convert Order to OrderDTO
     */
    private OrderDTO convertToDTO(Order order) {
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(order.getId());
        int totalItems = orderItems.stream().mapToInt(OrderItem::getQuantity).sum();

        return OrderDTO.builder()
                .id(order.getId())
                .orderCode(order.getOrderCode())
                .userId(order.getUser().getId())
                .userFullName(order.getUser().getFullName())
                .userEmail(order.getUser().getEmail())
                .orderDate(order.getOrderDate())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .paymentMethod(order.getPaymentMethod())
                .paymentStatus(order.getPaymentStatus())
                .shippingAddress(order.getShippingAddress())
                .shippingMethod(order.getShippingMethod())
                .shippingFee(order.getShippingFee())
                .notes(order.getNotes())
                .items(orderItems.stream()
                        .map(this::convertItemToDTO)
                        .collect(Collectors.toList()))
                .totalItems(totalItems)
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }

    /**
     * Convert Order to OrderSummaryDTO
     */
    private OrderSummaryDTO convertToSummaryDTO(Order order) {
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(order.getId());
        int totalItems = orderItems.stream()
                .mapToInt(OrderItem::getQuantity)
                .sum();

        return OrderSummaryDTO.builder()
                .id(order.getId())
                .orderCode(order.getOrderCode())
                .userFullName(order.getUser().getFullName())
                .userEmail(order.getUser().getEmail())
                .orderDate(order.getOrderDate())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .paymentMethod(order.getPaymentMethod())
                .paymentStatus(order.getPaymentStatus())
                .totalItems(totalItems)
                .createdAt(order.getCreatedAt())
                .build();
    }

    /**
     * Convert OrderItem to OrderItemDTO
     */
    private OrderItemDTO convertItemToDTO(OrderItem orderItem) {
        // Get first product image
        List<ProductImage> images = productImageRepository.findByProductId(orderItem.getProduct().getId());
        String imageUrl = !images.isEmpty() ? images.get(0).getImageUrl() : null;

        return OrderItemDTO.builder()
                .id(orderItem.getId())
                .productId(orderItem.getProduct().getId())
                .productName(orderItem.getProductName())
                .productImage(imageUrl)
                .productPrice(orderItem.getProductPrice())
                .quantity(orderItem.getQuantity())
                .subtotal(orderItem.getSubtotal())
                .build();
    }
}
