package iuh.chillteam.service.impl;

import iuh.chillteam.dto.order.*;
import iuh.chillteam.entity.*;
import iuh.chillteam.entity.enums.*;
import iuh.chillteam.exception.*;
import iuh.chillteam.repository.*;
import iuh.chillteam.service.OrderService;
import iuh.chillteam.utils.FormatUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import iuh.chillteam.service.EmailService;


/**
 * Order Service Implementation
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final UserRepository userRepository;

    // fix promotion
    private final PromotionRepository promotionRepository;
    private final OrderPromotionRepository orderPromotionRepository;

    // Shipping fee constants (VND)
    private static final Double STANDARD_SHIPPING_FEE = 30000.0;
    private static final Double EXPRESS_SHIPPING_FEE = 60000.0;
    // Nếu không dùng free-threshold nữa thì có thể bỏ hẳn dòng này
    // private static final Double FREE_SHIPPING_THRESHOLD = 500000.0;

    // Dich vu gui email _ xác nhận đơn
    private final EmailService emailService; // ⭐ thêm dòng này



    @Override
    public OrderDTO createOrder(Long userId, CreateOrderRequest request) {
        log.info("Creating order for user: {}", userId);

        // Get user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // Get cart
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new EmptyCartException("Cart is empty"));

        // Get cart items
        List<CartItem> cartItems = cartItemRepository.findByCartId(cart.getId());
        if (cartItems.isEmpty()) {
            throw new EmptyCartException("Cart is empty");
        }

        // Validate stock and calculate subtotal
        Double subtotal = 0.0;
        for (CartItem cartItem : cartItems) {
            Product product = cartItem.getProduct();

            // Check stock
            if (product.getStockQuantity() < cartItem.getQuantity()) {
                throw new OutOfStockException(
                        String.format("Product '%s' is out of stock. Available: %d",
                                product.getName(), product.getStockQuantity())
                );
            }

            subtotal += cartItem.getSubtotal();
        }

        // Calculate shipping fee
        Double shippingFee = calculateShippingFee(request.getShippingMethod(), subtotal);

        // 🔹 Tính promotion (nếu có)
        Double discountAmount = 0.0;
        Promotion appliedPromotion = null;
        String promotionDescription = null;

        String rawPromotionCode = request.getPromotionCode();
        if (rawPromotionCode != null && !rawPromotionCode.trim().isEmpty()) {
            String promotionCode = rawPromotionCode.trim().toUpperCase();
            log.info("User {} applies promotion code: {}", userId, promotionCode);

            Promotion promotion = promotionRepository.findByCodeAndIsActiveTrue(promotionCode)
                    .orElseThrow(() -> new InvalidPromotionException("Promotion code is invalid or inactive"));

            // Validate thời gian
            LocalDateTime now = LocalDateTime.now();
            if (now.isBefore(promotion.getStartDate()) || now.isAfter(promotion.getEndDate())) {
                throw new InvalidPromotionException("Promotion is expired or not yet started");
            }

            // Validate usage_limit
            Integer usageLimit = promotion.getUsageLimit();
            Integer usedCount = promotion.getUsedCount() == null ? 0 : promotion.getUsedCount();
            if (usageLimit != null && usedCount >= usageLimit) {
                throw new InvalidPromotionException("Promotion usage limit has been reached");
            }

            // Validate min_order_value (tính trên subtotal)
            Double minOrderValue = promotion.getMinOrderValue();
            if (minOrderValue != null && subtotal < minOrderValue) {
                throw new InvalidPromotionException("Order amount does not meet minimum requirement for this promotion");
            }

            // 🔥 ĐIỂM KHÁC BIỆT Ở ĐÂY
            // Nếu là mã FREESHIP2025 → giảm trên shippingFee, không giảm trên subtotal
            if ("FREESHIP2025".equalsIgnoreCase(promotion.getCode())) {
                Double maxDiscountAmount = promotion.getMaxDiscountAmount();
                double maxByPromotion = (maxDiscountAmount != null) ? maxDiscountAmount : shippingFee;
                discountAmount = Math.min(shippingFee, maxByPromotion);

            } else {
                // Các mã còn lại → giảm trên subtotal theo discount_type
                switch (promotion.getDiscountType()) {
                    case PERCENTAGE -> {
                        double raw = subtotal * (promotion.getDiscountValue() / 100.0);
                        discountAmount = raw;
                    }
                    case FIXED_AMOUNT -> discountAmount = promotion.getDiscountValue();
                }

                // Áp max_discount_amount nếu có
                Double maxDiscountAmount = promotion.getMaxDiscountAmount();
                if (maxDiscountAmount != null && discountAmount > maxDiscountAmount) {
                    discountAmount = maxDiscountAmount;
                }
            }

            // Không cho giảm vượt quá tổng (subtotal + shipping)
            double maxPossibleDiscount = subtotal + shippingFee;
            if (discountAmount > maxPossibleDiscount) {
                discountAmount = maxPossibleDiscount;
            }

            appliedPromotion = promotion;

            StringBuilder descBuilder = new StringBuilder();
            descBuilder.append("Code: ").append(promotion.getCode());
            if (promotion.getDescription() != null) {
                descBuilder.append(" | ").append(promotion.getDescription());
            }
            descBuilder.append(" | Discount applied: ").append(FormatUtils.formatCurrency(discountAmount));
            promotionDescription = descBuilder.toString();
        }


        // Calculate total amount (subtotal + shipping - discount)
        Double totalAmount = subtotal + shippingFee - discountAmount;

        // Generate order code
        String orderCode = generateOrderCode();

        // Create order
        Order order = Order.builder()
                .orderCode(orderCode)
                .user(user)
                .orderDate(LocalDateTime.now())
                .totalAmount(totalAmount)
                .status(OrderStatus.PENDING)
                .paymentMethod(request.getPaymentMethod())
                .paymentStatus(PaymentStatus.UNPAID)
                .shippingAddress(request.getShippingAddress())
                .shippingMethod(request.getShippingMethod())
                .shippingFee(shippingFee)
                .notes(request.getNotes())
                .build();

        order = orderRepository.save(order);
        log.info("Created order: {} with totalAmount: {}", orderCode, totalAmount);

        // Nếu có promotion → tạo OrderPromotion + tăng used_count
        if (appliedPromotion != null) {
            OrderPromotion orderPromotion = OrderPromotion.builder()
                    .order(order)
                    .promotion(appliedPromotion)
                    .discountAmount(discountAmount)
                    .description(promotionDescription)
                    .build();

            orderPromotionRepository.save(orderPromotion);

            Integer currentUsed = appliedPromotion.getUsedCount() == null
                    ? 0
                    : appliedPromotion.getUsedCount();
            appliedPromotion.setUsedCount(currentUsed + 1);
            promotionRepository.save(appliedPromotion);

            log.info("Applied promotion {} to order {} with discount {}", appliedPromotion.getCode(), orderCode, discountAmount);
        }

        // Create order items and reduce stock
        for (CartItem cartItem : cartItems) {
            Product product = cartItem.getProduct();

            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .product(product)
                    .productName(product.getName())
                    .productPrice(product.getPrice())
                    .quantity(cartItem.getQuantity())
                    .subtotal(cartItem.getSubtotal())
                    .build();

            orderItemRepository.save(orderItem);

            product.setStockQuantity(product.getStockQuantity() - cartItem.getQuantity());
            productRepository.save(product);
        }

        // Clear cart
        cartItemRepository.deleteAll(cartItems);
        log.info("Cleared cart for user: {}", userId);

        // Chuyển sang DTO để trả về FE và gửi email xác nhận
        OrderDTO orderDTO = convertToDTO(order);

        // Gửi email xác nhận đơn hàng (song ngữ, dùng DTO)
        try {
            emailService.sendOrderConfirmationEmail(orderDTO);
        } catch (Exception e) {
            log.error("Error while sending order confirmation email for order {}", order.getOrderCode(), e);
        }

        return orderDTO;
    }



    @Override
    @Transactional(readOnly = true)
    public OrderDTO getOrderById(Long orderId, Long userId) {
        log.info("Getting order by ID: {} for user: {}", orderId, userId);

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        // Check ownership
        if (!order.getUser().getId().equals(userId)) {
            throw new ForbiddenException("You don't have permission to access this order");
        }

        return convertToDTO(order);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderDTO getOrderByCode(String orderCode, Long userId) {
        log.info("Getting order by code: {} for user: {}", orderCode, userId);

        Order order = orderRepository.findByOrderCode(orderCode)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        // Check ownership
        if (!order.getUser().getId().equals(userId)) {
            throw new ForbiddenException("You don't have permission to access this order");
        }

        return convertToDTO(order);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OrderSummaryDTO> getUserOrders(Long userId, Pageable pageable) {
        log.info("Getting orders for user: {}", userId);

        Page<Order> orders = orderRepository.findByUserId(userId, pageable);
        return orders.map(this::convertToSummaryDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderSummaryDTO> getUserOrdersByStatus(Long userId, OrderStatus status) {
        log.info("Getting orders for user: {} with status: {}", userId, status);

        List<Order> orders = orderRepository.findByUserIdAndStatus(userId, status);
        return orders.stream()
                .map(this::convertToSummaryDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OrderSummaryDTO> getAllOrders(Pageable pageable) {
        log.info("Getting all orders (Admin)");

        Page<Order> orders = orderRepository.findAllOrders(pageable);
        return orders.map(this::convertToSummaryDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OrderSummaryDTO> getOrdersByStatus(OrderStatus status, Pageable pageable) {
        log.info("Getting orders by status: {} (Admin)", status);

        Page<Order> orders = orderRepository.findByStatus(status, pageable);
        return orders.map(this::convertToSummaryDTO);
    }

    @Override
    public OrderDTO updateOrderStatus(Long orderId, UpdateOrderStatusRequest request) {
        log.info("Updating order status: {} to {}", orderId, request.getStatus());

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        // Validate status transition
        validateStatusTransition(order.getStatus(), request.getStatus());

        // Update status
        order.setStatus(request.getStatus());

        // Update payment status if delivered
        if (request.getStatus() == OrderStatus.DELIVERED &&
                order.getPaymentMethod() == PaymentMethod.COD) {
            order.setPaymentStatus(PaymentStatus.PAID);
        }

        // If cancelled, restore stock
        if (request.getStatus() == OrderStatus.CANCELLED) {
            restoreStock(orderId);
        }

        order = orderRepository.save(order);
        log.info("Updated order status: {}", orderId);

        return convertToDTO(order);
    }

    @Override
    public OrderDTO cancelOrder(Long orderId, Long userId) {
        log.info("Cancelling order: {} by user: {}", orderId, userId);

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        // Check ownership
        if (!order.getUser().getId().equals(userId)) {
            throw new ForbiddenException("You don't have permission to cancel this order");
        }

        // Validate can cancel
        if (order.getStatus() == OrderStatus.CANCELLED) {
            throw new BadRequestException("Order is already cancelled");
        }

        if (order.getStatus() == OrderStatus.DELIVERED) {
            throw new BadRequestException("Cannot cancel delivered order");
        }

        if (order.getStatus() == OrderStatus.SHIPPED) {
            throw new BadRequestException("Cannot cancel shipped order. Please contact support.");
        }

        // Cancel and restore stock
        order.setStatus(OrderStatus.CANCELLED);
        restoreStock(orderId);

        order = orderRepository.save(order);
        log.info("Cancelled order: {}", orderId);

        OrderDTO dto = convertToDTO(order);

        // Gửi email thông báo hủy đơn (song ngữ)
        try {
            emailService.sendOrderCancellationEmail(dto);
        } catch (Exception e) {
            log.error("Error while sending order cancellation email for order {}", order.getOrderCode(), e);
        }

        return dto;
    }


    @Override
    public Double calculateShippingFee(ShippingMethod shippingMethod, Double orderAmount) {
        // Không auto free ship nữa, cứ theo phương thức ship
        return switch (shippingMethod) {
            case STANDARD -> STANDARD_SHIPPING_FEE;
            case EXPRESS -> EXPRESS_SHIPPING_FEE;
        };
    }


    /**
     * Validate status transition
     */
    private void validateStatusTransition(OrderStatus currentStatus, OrderStatus newStatus) {
        // Cannot change from CANCELLED or DELIVERED
        if (currentStatus == OrderStatus.CANCELLED) {
            throw new BadRequestException("Cannot change status of cancelled order");
        }

        if (currentStatus == OrderStatus.DELIVERED && newStatus != OrderStatus.DELIVERED) {
            throw new BadRequestException("Cannot change status of delivered order");
        }

        // Validate logical flow: PENDING → PROCESSING → SHIPPED → DELIVERED
        // Can cancel at PENDING or PROCESSING stage
    }

    /**
     * Restore stock when order is cancelled
     */
    private void restoreStock(Long orderId) {
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderId);

        for (OrderItem orderItem : orderItems) {
            Product product = orderItem.getProduct();
            product.setStockQuantity(product.getStockQuantity() + orderItem.getQuantity());
            productRepository.save(product);
        }

        log.info("Restored stock for cancelled order: {}", orderId);
    }

    /**
     * Generate unique order code
     * Format: OD20250120001
     */
    private String generateOrderCode() {
        String datePrefix = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String baseCode = "OD" + datePrefix;

        // Find last order of the day
        int sequence = 1;
        String orderCode;

        do {
            orderCode = String.format("%s%03d", baseCode, sequence);
            sequence++;
        } while (orderRepository.existsByOrderCode(orderCode));

        return orderCode;
    }

    /**
     * Convert Order to OrderDTO
     */
    private OrderDTO convertToDTO(Order order) {
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(order.getId());
        List<OrderItemDTO> itemDTOs = orderItems.stream()
                .map(this::convertItemToDTO)
                .collect(Collectors.toList());

        // 🔹 Lấy thông tin promotion nếu có
        List<OrderPromotion> orderPromotions = orderPromotionRepository.findByOrderId(order.getId());
        OrderPromotion op = orderPromotions.isEmpty() ? null : orderPromotions.get(0);

        String promotionCode = null;
        Double promotionDiscountAmount = 0.0;
        String formattedPromotionDiscount = null;
        String promotionDescription = null;

        if (op != null) {
            promotionCode = op.getPromotion().getCode();
            promotionDiscountAmount = op.getDiscountAmount();
            formattedPromotionDiscount = FormatUtils.formatCurrency(promotionDiscountAmount);
            promotionDescription = op.getDescription();
        }

        return OrderDTO.builder()
                .id(order.getId())
                .orderCode(order.getOrderCode())
                .userId(order.getUser().getId())
                .userFullName(order.getUser().getFullName())
                .userEmail(order.getUser().getEmail())
                .orderDate(order.getOrderDate())
                .totalAmount(order.getTotalAmount())
                .formattedTotalAmount(FormatUtils.formatCurrency(order.getTotalAmount()))
                .status(order.getStatus())
                .paymentMethod(order.getPaymentMethod())
                .paymentStatus(order.getPaymentStatus())
                .shippingAddress(order.getShippingAddress())
                .shippingMethod(order.getShippingMethod())
                .shippingFee(order.getShippingFee())
                .formattedShippingFee(FormatUtils.formatCurrency(order.getShippingFee()))
                .promotionCode(promotionCode)
                .promotionDiscountAmount(promotionDiscountAmount)
                .formattedPromotionDiscountAmount(formattedPromotionDiscount)
                .promotionDescription(promotionDescription)
                .notes(order.getNotes())
                .items(itemDTOs)
                .totalItems(itemDTOs.size())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }


    /**
     * Convert Order to OrderSummaryDTO
     */
    private OrderSummaryDTO convertToSummaryDTO(Order order) {
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(order.getId());

        List<OrderPromotion> orderPromotions = orderPromotionRepository.findByOrderId(order.getId());
        OrderPromotion op = orderPromotions.isEmpty() ? null : orderPromotions.get(0);

        String promotionCode = null;
        Double promotionDiscountAmount = 0.0;
        String formattedPromotionDiscount = null;

        if (op != null) {
            promotionCode = op.getPromotion().getCode();
            promotionDiscountAmount = op.getDiscountAmount();
            formattedPromotionDiscount = FormatUtils.formatCurrency(promotionDiscountAmount);
        }

        return OrderSummaryDTO.builder()
                .id(order.getId())
                .orderCode(order.getOrderCode())
                .orderDate(order.getOrderDate())
                .totalAmount(order.getTotalAmount())
                .formattedTotalAmount(FormatUtils.formatCurrency(order.getTotalAmount()))
                .status(order.getStatus())
                .paymentMethod(order.getPaymentMethod())
                .paymentStatus(order.getPaymentStatus())
                .totalItems(orderItems.size())
                .createdAt(order.getCreatedAt())
                .promotionCode(promotionCode)
                .promotionDiscountAmount(promotionDiscountAmount)
                .formattedPromotionDiscountAmount(formattedPromotionDiscount)
                .build();
    }


    /**
     * Convert OrderItem to OrderItemDTO
     */
    private OrderItemDTO convertItemToDTO(OrderItem orderItem) {
        Product product = orderItem.getProduct();

        // Get primary image
        String primaryImage = productImageRepository
                .findPrimaryImageByProductId(product.getId())
                .map(ProductImage::getImageUrl)
                .orElse(null);

        return OrderItemDTO.builder()
                .id(orderItem.getId())
                .productId(product.getId())
                .productName(orderItem.getProductName())
                .productSlug(product.getSlug())
                .productImage(primaryImage)
                .productPrice(orderItem.getProductPrice())
                .formattedPrice(FormatUtils.formatCurrency(orderItem.getProductPrice()))
                .quantity(orderItem.getQuantity())
                .subtotal(orderItem.getSubtotal())
                .formattedSubtotal(FormatUtils.formatCurrency(orderItem.getSubtotal()))
                .build();
    }
}
