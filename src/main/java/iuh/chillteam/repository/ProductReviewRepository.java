package iuh.chillteam.repository;

import iuh.chillteam.entity.ProductReview;
import iuh.chillteam.entity.enums.ReviewStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * ProductReview Repository
 */
@Repository
public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {

    /**
     * Find all reviews by product ID with pagination
     */
    Page<ProductReview> findByProductId(Long productId, Pageable pageable);

    /**
     * Find approved reviews by product ID
     */
    @Query("SELECT r FROM ProductReview r WHERE r.product.id = :productId AND r.status = :status ORDER BY r.createdAt DESC")
    Page<ProductReview> findByProductIdAndStatus(@Param("productId") Long productId, 
                                                  @Param("status") ReviewStatus status, 
                                                  Pageable pageable);

    /**
     * Find all reviews by user ID
     */
    @Query("SELECT r FROM ProductReview r WHERE r.user.id = :userId ORDER BY r.createdAt DESC")
    Page<ProductReview> findByUserId(@Param("userId") Long userId, Pageable pageable);

    /**
     * Find reviews by status (for admin)
     */
    Page<ProductReview> findByStatus(ReviewStatus status, Pageable pageable);

    /**
     * Check if user already reviewed product in specific order
     */
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM ProductReview r " +
           "WHERE r.user.id = :userId AND r.product.id = :productId AND r.order.id = :orderId")
    boolean existsByUserIdAndProductIdAndOrderId(@Param("userId") Long userId,
                                                  @Param("productId") Long productId,
                                                  @Param("orderId") Long orderId);

    /**
     * Find review by user, product and order
     */
    @Query("SELECT r FROM ProductReview r WHERE r.user.id = :userId AND r.product.id = :productId AND r.order.id = :orderId")
    Optional<ProductReview> findByUserIdAndProductIdAndOrderId(@Param("userId") Long userId,
                                                                @Param("productId") Long productId,
                                                                @Param("orderId") Long orderId);

    /**
     * Get average rating for product (approved reviews only)
     */
    @Query("SELECT AVG(r.rating) FROM ProductReview r WHERE r.product.id = :productId AND r.status = 'APPROVED'")
    Double getAverageRatingByProductId(@Param("productId") Long productId);

    /**
     * Count approved reviews for product
     */
    @Query("SELECT COUNT(r) FROM ProductReview r WHERE r.product.id = :productId AND r.status = 'APPROVED'")
    Long countApprovedReviewsByProductId(@Param("productId") Long productId);

    /**
     * Get all approved reviews for a product
     */
    @Query("SELECT r FROM ProductReview r WHERE r.product.id = :productId AND r.status = 'APPROVED' ORDER BY r.createdAt DESC")
    List<ProductReview> findApprovedReviewsByProductId(@Param("productId") Long productId);
}
