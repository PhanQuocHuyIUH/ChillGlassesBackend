package iuh.chillteam.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "order_promotion",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_order_promotion", columnNames = {"order_id"})
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderPromotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_id", nullable = false)
    private Promotion promotion;

    @Column(name = "discount_amount", nullable = false)
    private Double discountAmount;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
}
