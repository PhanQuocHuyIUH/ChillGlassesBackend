package iuh.chillteam.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Cart Entity - Giỏ hàng
 * 1 user chỉ có 1 giỏ hàng
 */
@Entity
@Table(name = "cart", uniqueConstraints = {
        @UniqueConstraint(name = "uk_user_cart", columnNames = "user_id")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + getId() +
                ", userId=" + (user != null ? user.getId() : null) +
                ", createdAt=" + getCreatedAt() +
                '}';
    }
}
