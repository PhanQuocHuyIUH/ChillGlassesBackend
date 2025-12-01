package iuh.chillteam.repository;

import iuh.chillteam.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * CartItem Repository
 */
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    /**
     * Find all items in a cart
     */
    @Query("SELECT ci FROM CartItem ci JOIN FETCH ci.product WHERE ci.cart.id = :cartId")
    List<CartItem> findByCartId(@Param("cartId") Long cartId);

    /**
     * Find cart item by cart and product
     */
    @Query("SELECT ci FROM CartItem ci WHERE ci.cart.id = :cartId AND ci.product.id = :productId")
    Optional<CartItem> findByCartIdAndProductId(@Param("cartId") Long cartId, @Param("productId") Long productId);

    /**
     * Check if product exists in cart
     */
    @Query("SELECT CASE WHEN COUNT(ci) > 0 THEN true ELSE false END FROM CartItem ci WHERE ci.cart.id = :cartId AND ci.product.id = :productId")
    boolean existsByCartIdAndProductId(@Param("cartId") Long cartId, @Param("productId") Long productId);

    /**
     * Delete all items in cart
     */
    @Modifying
    @Query("DELETE FROM CartItem ci WHERE ci.cart.id = :cartId")
    void deleteByCartId(@Param("cartId") Long cartId);

    /**
     * Count items in cart
     */
    @Query("SELECT COUNT(ci) FROM CartItem ci WHERE ci.cart.id = :cartId")
    long countByCartId(@Param("cartId") Long cartId);

    /**
     * Calculate total amount in cart
     */
    @Query("SELECT COALESCE(SUM(ci.quantity * ci.product.price), 0) FROM CartItem ci WHERE ci.cart.id = :cartId")
    Double calculateTotalAmount(@Param("cartId") Long cartId);
}
