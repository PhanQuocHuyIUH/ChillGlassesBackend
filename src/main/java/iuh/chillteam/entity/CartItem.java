package iuh.chillteam.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * CartItem Entity - Sản phẩm trong giỏ hàng
 */
@Entity
@Table(name = "cart_item", 
       uniqueConstraints = {
           @UniqueConstraint(name = "uk_cart_product", columnNames = {"cart_id", "product_id"})
       },
       indexes = {
           @Index(name = "idx_cart", columnList = "cart_id"),
           @Index(name = "idx_product", columnList = "product_id")
       })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity", nullable = false)
    @Builder.Default
    private Integer quantity = 1;

    /**
     * Calculate subtotal for this cart item
     */
    @Transient
    public Double getSubtotal() {
        if (product != null && product.getPrice() != null && quantity != null) {
            return product.getPrice() * quantity;
        }
        return 0.0;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + getId() +
                ", cartId=" + (cart != null ? cart.getId() : null) +
                ", productId=" + (product != null ? product.getId() : null) +
                ", quantity=" + quantity +
                '}';
    }
}
