package iuh.chillteam.service.impl;

import iuh.chillteam.dto.review.CreateReviewRequest;
import iuh.chillteam.dto.review.ReviewDTO;
import iuh.chillteam.dto.review.UpdateReviewRequest;
import iuh.chillteam.entity.Order;
import iuh.chillteam.entity.Product;
import iuh.chillteam.entity.ProductReview;
import iuh.chillteam.entity.User;
import iuh.chillteam.entity.enums.OrderStatus;
import iuh.chillteam.entity.enums.ReviewStatus;
import iuh.chillteam.exception.*;
import iuh.chillteam.repository.OrderRepository;
import iuh.chillteam.repository.ProductRepository;
import iuh.chillteam.repository.ProductReviewRepository;
import iuh.chillteam.repository.UserRepository;
import iuh.chillteam.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Review Service Implementation
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final ProductReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Override
    public ReviewDTO createReview(CreateReviewRequest request, Long userId) {
        log.info("Creating review for product: {} by user: {}", request.getProductId(), userId);

        // Validate user exists
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        // Validate product exists
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + request.getProductId()));

        // Validate order exists and belongs to user
        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + request.getOrderId()));

        if (!order.getUser().getId().equals(userId)) {
            throw new ForbiddenException("You can only review products from your own orders");
        }

        // Check if order is delivered
        if (order.getStatus() != OrderStatus.DELIVERED) {
            throw new BadRequestException("You can only review products from delivered orders");
        }

        // Check if user already reviewed this product in this order
        if (reviewRepository.existsByUserIdAndProductIdAndOrderId(userId, request.getProductId(), request.getOrderId())) {
            throw new ConflictException("You have already reviewed this product for this order");
        }

        // Create review
        ProductReview review = ProductReview.builder()
                .product(product)
                .user(user)
                .order(order)
                .rating(request.getRating())
                .comment(request.getComment())
                .status(ReviewStatus.PENDING)
                .build();

        review = reviewRepository.save(review);
        log.info("Review created successfully with ID: {}", review.getId());

        return ReviewDTO.fromEntity(review);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ReviewDTO> getProductReviews(Long productId, Pageable pageable) {
        log.info("Getting reviews for product: {}", productId);

        // Validate product exists
        if (!productRepository.existsById(productId)) {
            throw new ProductNotFoundException("Product not found with ID: " + productId);
        }

        // Get only approved reviews for public viewing
        Page<ProductReview> reviews = reviewRepository.findByProductIdAndStatus(
                productId, ReviewStatus.APPROVED, pageable);

        return reviews.map(ReviewDTO::fromEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ReviewDTO> getMyReviews(Long userId, Pageable pageable) {
        log.info("Getting reviews for user: {}", userId);

        // Validate user exists
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found with ID: " + userId);
        }

        Page<ProductReview> reviews = reviewRepository.findByUserId(userId, pageable);
        return reviews.map(ReviewDTO::fromEntity);
    }

    @Override
    public ReviewDTO updateReview(Long reviewId, UpdateReviewRequest request, Long userId) {
        log.info("Updating review: {} by user: {}", reviewId, userId);

        ProductReview review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with ID: " + reviewId));

        // Check if user owns this review
        if (!review.getUser().getId().equals(userId)) {
            throw new ForbiddenException("You can only update your own reviews");
        }

        // Check if review is still pending (cannot update approved/rejected reviews)
        if (review.getStatus() != ReviewStatus.PENDING) {
            throw new BadRequestException("Cannot update a review that has been " + review.getStatus().name().toLowerCase());
        }

        // Update review
        review.setRating(request.getRating());
        review.setComment(request.getComment());

        review = reviewRepository.save(review);
        log.info("Review updated successfully: {}", reviewId);

        return ReviewDTO.fromEntity(review);
    }

    @Override
    public void deleteReview(Long reviewId, Long userId) {
        log.info("Deleting review: {} by user: {}", reviewId, userId);

        ProductReview review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with ID: " + reviewId));

        // Check if user owns this review
        if (!review.getUser().getId().equals(userId)) {
            throw new ForbiddenException("You can only delete your own reviews");
        }

        Long productId = review.getProduct().getId();
        reviewRepository.delete(review);
        log.info("Review deleted successfully: {}", reviewId);

        // Update product rating after deletion
        updateProductRating(productId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ReviewDTO> getPendingReviews(Pageable pageable) {
        log.info("Getting pending reviews for admin approval");

        Page<ProductReview> reviews = reviewRepository.findByStatus(ReviewStatus.PENDING, pageable);
        return reviews.map(ReviewDTO::fromEntity);
    }

    @Override
    public ReviewDTO approveReview(Long reviewId) {
        log.info("Approving review: {}", reviewId);

        ProductReview review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with ID: " + reviewId));

        if (review.getStatus() == ReviewStatus.APPROVED) {
            throw new BadRequestException("Review is already approved");
        }

        review.setStatus(ReviewStatus.APPROVED);
        review = reviewRepository.save(review);
        log.info("Review approved successfully: {}", reviewId);

        // Update product rating
        updateProductRating(review.getProduct().getId());

        return ReviewDTO.fromEntity(review);
    }

    @Override
    public ReviewDTO rejectReview(Long reviewId) {
        log.info("Rejecting review: {}", reviewId);

        ProductReview review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with ID: " + reviewId));

        if (review.getStatus() == ReviewStatus.REJECTED) {
            throw new BadRequestException("Review is already rejected");
        }

        review.setStatus(ReviewStatus.REJECTED);
        review = reviewRepository.save(review);
        log.info("Review rejected successfully: {}", reviewId);

        // Update product rating (in case it was previously approved)
        updateProductRating(review.getProduct().getId());

        return ReviewDTO.fromEntity(review);
    }

    @Override
    public void updateProductRating(Long productId) {
        log.info("Updating product rating for product: {}", productId);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + productId));

        // Calculate average rating from approved reviews
        Double avgRating = reviewRepository.getAverageRatingByProductId(productId);
        Long reviewCount = reviewRepository.countApprovedReviewsByProductId(productId);

        product.setRating(avgRating != null ? avgRating : 0.0);
        product.setReviewCount(reviewCount.intValue());

        productRepository.save(product);
        log.info("Product rating updated: avgRating={}, reviewCount={}", avgRating, reviewCount);
    }
}
