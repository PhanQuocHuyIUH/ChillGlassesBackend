package iuh.chillteam.controller;

import iuh.chillteam.dto.common.ApiResponse;
import iuh.chillteam.dto.common.PageResponse;
import iuh.chillteam.dto.review.CreateReviewRequest;
import iuh.chillteam.dto.review.ReviewDTO;
import iuh.chillteam.dto.review.UpdateReviewRequest;
import iuh.chillteam.security.UserDetailsServiceImpl;
import iuh.chillteam.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * Review Controller
 * Handles product reviews
 */
@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Reviews", description = "Product review management endpoints")
public class ReviewController {

    private final ReviewService reviewService;

    /**
     * Create a new review (Authenticated users only)
     * POST /api/reviews
     */
    @PostMapping
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Create review", description = "Create a new product review (requires authentication)")
    public ResponseEntity<ApiResponse<ReviewDTO>> createReview(
            @Valid @RequestBody CreateReviewRequest request,
            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUserDetails userDetails) {
        log.info("POST /api/reviews - Create review for product: {} by user: {}", 
                request.getProductId(), userDetails.getUser().getId());

        ReviewDTO review = reviewService.createReview(request, userDetails.getUser().getId());
        return ResponseEntity.ok(ApiResponse.success("Review created successfully. Waiting for admin approval.", review));
    }

    /**
     * Get all reviews for a product (Public)
     * GET /api/reviews/product/{productId}
     */
    @GetMapping("/product/{productId}")
    @Operation(summary = "Get product reviews", description = "Get all approved reviews for a specific product (public)")
    public ResponseEntity<ApiResponse<PageResponse<ReviewDTO>>> getProductReviews(
            @PathVariable Long productId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortDir) {
        log.info("GET /api/reviews/product/{} - Get product reviews", productId);

        Sort sort = sortDir.equalsIgnoreCase("ASC") ? 
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<ReviewDTO> reviews = reviewService.getProductReviews(productId, pageable);

        PageResponse<ReviewDTO> pageResponse = PageResponse.<ReviewDTO>builder()
                .content(reviews.getContent())
                .pageNumber(reviews.getNumber())
                .pageSize(reviews.getSize())
                .totalElements(reviews.getTotalElements())
                .totalPages(reviews.getTotalPages())
                .last(reviews.isLast())
                .build();

        return ResponseEntity.ok(ApiResponse.success("Product reviews retrieved successfully", pageResponse));
    }

    /**
     * Get current user's reviews (Authenticated)
     * GET /api/reviews/my-reviews
     */
    @GetMapping("/my-reviews")
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Get my reviews", description = "Get all reviews created by current user")
    public ResponseEntity<ApiResponse<PageResponse<ReviewDTO>>> getMyReviews(
            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUserDetails userDetails,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortDir) {
        log.info("GET /api/reviews/my-reviews - Get reviews for user: {}", userDetails.getUser().getId());

        Sort sort = sortDir.equalsIgnoreCase("ASC") ? 
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<ReviewDTO> reviews = reviewService.getMyReviews(userDetails.getUser().getId(), pageable);

        PageResponse<ReviewDTO> pageResponse = PageResponse.<ReviewDTO>builder()
                .content(reviews.getContent())
                .pageNumber(reviews.getNumber())
                .pageSize(reviews.getSize())
                .totalElements(reviews.getTotalElements())
                .totalPages(reviews.getTotalPages())
                .last(reviews.isLast())
                .build();

        return ResponseEntity.ok(ApiResponse.success("Your reviews retrieved successfully", pageResponse));
    }

    /**
     * Update a review (Owner only)
     * PUT /api/reviews/{id}
     */
    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Update review", description = "Update your own review (only pending reviews can be updated)")
    public ResponseEntity<ApiResponse<ReviewDTO>> updateReview(
            @PathVariable Long id,
            @Valid @RequestBody UpdateReviewRequest request,
            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUserDetails userDetails) {
        log.info("PUT /api/reviews/{} - Update review by user: {}", id, userDetails.getUser().getId());

        ReviewDTO review = reviewService.updateReview(id, request, userDetails.getUser().getId());
        return ResponseEntity.ok(ApiResponse.success("Review updated successfully", review));
    }

    /**
     * Delete a review (Owner only)
     * DELETE /api/reviews/{id}
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Delete review", description = "Delete your own review")
    public ResponseEntity<ApiResponse<Void>> deleteReview(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUserDetails userDetails) {
        log.info("DELETE /api/reviews/{} - Delete review by user: {}", id, userDetails.getUser().getId());

        reviewService.deleteReview(id, userDetails.getUser().getId());
        return ResponseEntity.ok(ApiResponse.success("Review deleted successfully"));
    }

    /**
     * Get pending reviews (Admin only)
     * GET /api/reviews/admin/pending
     */
    @GetMapping("/admin/pending")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Get pending reviews (Admin)", description = "Get all reviews waiting for approval")
    public ResponseEntity<ApiResponse<PageResponse<ReviewDTO>>> getPendingReviews(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortDir) {
        log.info("GET /api/reviews/admin/pending - Get pending reviews");

        Sort sort = sortDir.equalsIgnoreCase("ASC") ? 
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<ReviewDTO> reviews = reviewService.getPendingReviews(pageable);

        PageResponse<ReviewDTO> pageResponse = PageResponse.<ReviewDTO>builder()
                .content(reviews.getContent())
                .pageNumber(reviews.getNumber())
                .pageSize(reviews.getSize())
                .totalElements(reviews.getTotalElements())
                .totalPages(reviews.getTotalPages())
                .last(reviews.isLast())
                .build();

        return ResponseEntity.ok(ApiResponse.success("Pending reviews retrieved successfully", pageResponse));
    }

    /**
     * Approve a review (Admin only)
     * POST /api/reviews/{id}/approve
     */
    @PostMapping("/{id}/approve")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Approve review (Admin)", description = "Approve a pending review")
    public ResponseEntity<ApiResponse<ReviewDTO>> approveReview(@PathVariable Long id) {
        log.info("POST /api/reviews/{}/approve - Approve review", id);

        ReviewDTO review = reviewService.approveReview(id);
        return ResponseEntity.ok(ApiResponse.success("Review approved successfully", review));
    }

    /**
     * Reject a review (Admin only)
     * POST /api/reviews/{id}/reject
     */
    @PostMapping("/{id}/reject")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Reject review (Admin)", description = "Reject a pending review")
    public ResponseEntity<ApiResponse<ReviewDTO>> rejectReview(@PathVariable Long id) {
        log.info("POST /api/reviews/{}/reject - Reject review", id);

        ReviewDTO review = reviewService.rejectReview(id);
        return ResponseEntity.ok(ApiResponse.success("Review rejected successfully", review));
    }
}
