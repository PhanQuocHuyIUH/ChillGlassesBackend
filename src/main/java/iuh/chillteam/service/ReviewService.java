package iuh.chillteam.service;

import iuh.chillteam.dto.review.CreateReviewRequest;
import iuh.chillteam.dto.review.ReviewDTO;
import iuh.chillteam.dto.review.UpdateReviewRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Review Service Interface
 */
public interface ReviewService {

    /**
     * Create a new review
     * @param request Review data
     * @param userId Current user ID
     * @return Created review
     */
    ReviewDTO createReview(CreateReviewRequest request, Long userId);

    /**
     * Get all reviews for a product (approved only for public)
     * @param productId Product ID
     * @param pageable Pagination
     * @return Page of reviews
     */
    Page<ReviewDTO> getProductReviews(Long productId, Pageable pageable);

    /**
     * Get all reviews by current user
     * @param userId User ID
     * @param pageable Pagination
     * @return Page of reviews
     */
    Page<ReviewDTO> getMyReviews(Long userId, Pageable pageable);

    /**
     * Update a review
     * @param reviewId Review ID
     * @param request Update data
     * @param userId Current user ID
     * @return Updated review
     */
    ReviewDTO updateReview(Long reviewId, UpdateReviewRequest request, Long userId);

    /**
     * Delete a review
     * @param reviewId Review ID
     * @param userId Current user ID
     */
    void deleteReview(Long reviewId, Long userId);

    /**
     * Get all pending reviews (Admin)
     * @param pageable Pagination
     * @return Page of pending reviews
     */
    Page<ReviewDTO> getPendingReviews(Pageable pageable);

    /**
     * Approve a review (Admin)
     * @param reviewId Review ID
     * @return Approved review
     */
    ReviewDTO approveReview(Long reviewId);

    /**
     * Reject a review (Admin)
     * @param reviewId Review ID
     * @return Rejected review
     */
    ReviewDTO rejectReview(Long reviewId);

    /**
     * Update product rating and review count
     * @param productId Product ID
     */
    void updateProductRating(Long productId);
}
