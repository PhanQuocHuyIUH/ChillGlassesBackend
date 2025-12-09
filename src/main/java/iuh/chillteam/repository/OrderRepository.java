package iuh.chillteam.repository;

import iuh.chillteam.entity.Order;
import iuh.chillteam.entity.enums.OrderStatus;
import iuh.chillteam.entity.enums.PaymentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Order Repository
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * Find order by order code
     */
    Optional<Order> findByOrderCode(String orderCode);

    /**
     * Check if order code exists
     */
    boolean existsByOrderCode(String orderCode);

    /**
     * Find all orders by user
     */
    @Query("SELECT o FROM Order o WHERE o.user.id = :userId AND o.deletedAt IS NULL ORDER BY o.orderDate DESC")
    List<Order> findByUserId(@Param("userId") Long userId);

    /**
     * Find all orders by user with pagination
     */
    @Query("SELECT o FROM Order o WHERE o.user.id = :userId AND o.deletedAt IS NULL ORDER BY o.orderDate DESC")
    Page<Order> findByUserId(@Param("userId") Long userId, Pageable pageable);

    /**
     * Find orders by user and status
     */
    @Query("SELECT o FROM Order o WHERE o.user.id = :userId AND o.status = :status AND o.deletedAt IS NULL ORDER BY o.orderDate DESC")
    List<Order> findByUserIdAndStatus(@Param("userId") Long userId, @Param("status") OrderStatus status);

    /**
     * Find orders by status
     */
    @Query("SELECT o FROM Order o WHERE o.status = :status AND o.deletedAt IS NULL ORDER BY o.orderDate DESC")
    Page<Order> findByStatus(@Param("status") OrderStatus status, Pageable pageable);

    /**
     * Find all orders with pagination (for admin)
     */
    @Query("SELECT o FROM Order o WHERE o.deletedAt IS NULL ORDER BY o.orderDate DESC")
    Page<Order> findAllOrders(Pageable pageable);

    /**
     * Find orders by date range
     */
    @Query("SELECT o FROM Order o WHERE o.orderDate BETWEEN :startDate AND :endDate AND o.deletedAt IS NULL ORDER BY o.orderDate DESC")
    List<Order> findByOrderDateBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    /**
     * Count orders by user
     */
    @Query("SELECT COUNT(o) FROM Order o WHERE o.user.id = :userId AND o.deletedAt IS NULL")
    Long countByUserId(@Param("userId") Long userId);

    /**
     * Count orders by status
     */
    @Query("SELECT COUNT(o) FROM Order o WHERE o.status = :status AND o.deletedAt IS NULL")
    Long countByStatus(@Param("status") OrderStatus status);

    /**
     * Find recent orders for user (last N orders)
     */
    @Query("SELECT o FROM Order o WHERE o.user.id = :userId AND o.deletedAt IS NULL ORDER BY o.orderDate DESC")
    List<Order> findRecentOrdersByUserId(@Param("userId") Long userId, Pageable pageable);
    
    /**
     * Find top N recent orders for AI context
     */
    @Query(value = "SELECT o FROM Order o WHERE o.user.id = :userId AND o.deletedAt IS NULL ORDER BY o.createdAt DESC")
    List<Order> findTop3ByUserIdOrderByCreatedAtDesc(@Param("userId") Long userId, Pageable pageable);

    /**
     * Find orders after date
     */
    @Query("SELECT o FROM Order o WHERE o.orderDate >= :startDate AND o.deletedAt IS NULL ORDER BY o.orderDate DESC")
    List<Order> findByOrderDateAfter(@Param("startDate") LocalDateTime startDate);

    /**
     * Find orders before date
     */
    @Query("SELECT o FROM Order o WHERE o.orderDate <= :endDate AND o.deletedAt IS NULL ORDER BY o.orderDate DESC")
    List<Order> findByOrderDateBefore(@Param("endDate") LocalDateTime endDate);

    /**
     * Search orders by code or customer name with filters
     */
    @Query("SELECT o FROM Order o WHERE o.deletedAt IS NULL " +
           "AND (:search IS NULL OR LOWER(o.orderCode) LIKE LOWER(CONCAT('%', :search, '%')) " +
           "OR LOWER(o.user.fullName) LIKE LOWER(CONCAT('%', :search, '%'))) " +
           "AND (:status IS NULL OR o.status = :status) " +
           "AND (:paymentStatus IS NULL OR o.paymentStatus = :paymentStatus) " +
           "AND (:startDate IS NULL OR o.orderDate >= :startDate) " +
           "AND (:endDate IS NULL OR o.orderDate <= :endDate) " +
           "ORDER BY o.orderDate DESC")
    Page<Order> searchOrders(
        @Param("search") String search,
        @Param("status") OrderStatus status,
        @Param("paymentStatus") PaymentStatus paymentStatus,
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate,
        Pageable pageable
    );
}
