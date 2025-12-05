package iuh.chillteam.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * OrderItem Entity
 * Represents items in an order (snapshot of product at purchase time)
 */
@Entity
@Table(name = "order_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_price", nullable = false)
    private Double productPrice;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "subtotal", nullable = false)
    private Double subtotal;

    /**
     * Calculate subtotal before persisting
     */
    @PrePersist
    @PreUpdate
    protected void calculateSubtotal() {
        if (productPrice != null && quantity != null) {
            this.subtotal = productPrice * quantity;
        }
    }
}
