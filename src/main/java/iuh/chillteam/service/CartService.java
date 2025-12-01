package iuh.chillteam.service;

import iuh.chillteam.dto.cart.AddToCartRequest;
import iuh.chillteam.dto.cart.CartDTO;
import iuh.chillteam.dto.cart.UpdateCartItemRequest;

/**
 * Cart Service Interface
 */
public interface CartService {

    /**
     * Get current user's cart
     */
    CartDTO getCart();

    /**
     * Add product to cart
     */
    CartDTO addToCart(AddToCartRequest request);

    /**
     * Update cart item quantity
     */
    CartDTO updateCartItem(Long cartItemId, UpdateCartItemRequest request);

    /**
     * Remove item from cart
     */
    CartDTO removeFromCart(Long cartItemId);

    /**
     * Clear all items in cart
     */
    void clearCart();

    /**
     * Get cart item count
     */
    Integer getCartItemCount();
}
