package iuh.chillteam.dto.review;

import iuh.chillteam.entity.ProductReview;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Review DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDTO {

    private Long id;
    private Long productId;
    private String productName;
    private Long userId;
    private String userName;
    private Long orderId;
    private Integer rating;
    private String comment;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * Convert ProductReview entity to ReviewDTO
     */
    public static ReviewDTO fromEntity(ProductReview review) {
        return ReviewDTO.builder()
                .id(review.getId())
                .productId(review.getProduct().getId())
                .productName(review.getProduct().getName())
                .userId(review.getUser().getId())
                .userName(review.getUser().getFullName())
                .orderId(review.getOrder() != null ? review.getOrder().getId() : null)
                .rating(review.getRating())
                .comment(review.getComment())
                .status(review.getStatus().name())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .build();
    }
}
