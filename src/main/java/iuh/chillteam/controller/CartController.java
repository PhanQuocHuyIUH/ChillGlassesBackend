package iuh.chillteam.controller;

import iuh.chillteam.dto.cart.AddToCartRequest;
import iuh.chillteam.dto.cart.CartDTO;
import iuh.chillteam.dto.cart.UpdateCartItemRequest;
import iuh.chillteam.dto.common.ApiResponse;
import iuh.chillteam.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Cart Controller
 * Handles shopping cart operations
 */
@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Cart", description = "Shopping cart management endpoints")
@SecurityRequirement(name = "Bearer Authentication")
@PreAuthorize("isAuthenticated()")
public class CartController {

    private final CartService cartService;

    /**
     * Get current user's cart
     */
    @GetMapping
    @Operation(summary = "Get cart", description = "Get current user's shopping cart with all items")
    public ResponseEntity<ApiResponse<CartDTO>> getCart() {
        log.info("GET /api/cart - Get cart");
        CartDTO cart = cartService.getCart();
        return ResponseEntity.ok(ApiResponse.success(cart));
    }

    /**
     * Get cart item count
     */
    @GetMapping("/count")
    @Operation(summary = "Get cart item count", description = "Get total number of items in cart")
    public ResponseEntity<ApiResponse<Integer>> getCartItemCount() {
        log.info("GET /api/cart/count - Get cart item count");
        Integer count = cartService.getCartItemCount();
        return ResponseEntity.ok(ApiResponse.success(count));
    }

    /**
     * Add product to cart
     */
    @PostMapping("/items")
    @Operation(summary = "Add to cart", description = "Add product to shopping cart")
    public ResponseEntity<ApiResponse<CartDTO>> addToCart(
            @Valid @RequestBody AddToCartRequest request
    ) {
        log.info("POST /api/cart/items - Add product {} to cart", request.getProductId());
        CartDTO cart = cartService.addToCart(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Product added to cart successfully", cart));
    }

    /**
     * Update cart item quantity
     */
    @PutMapping("/items/{cartItemId}")
    @Operation(summary = "Update cart item", description = "Update quantity of item in cart")
    public ResponseEntity<ApiResponse<CartDTO>> updateCartItem(
            @PathVariable Long cartItemId,
            @Valid @RequestBody UpdateCartItemRequest request
    ) {
        log.info("PUT /api/cart/items/{} - Update cart item", cartItemId);
        CartDTO cart = cartService.updateCartItem(cartItemId, request);
        return ResponseEntity.ok(ApiResponse.success("Cart item updated successfully", cart));
    }

    /**
     * Remove item from cart
     */
    @DeleteMapping("/items/{cartItemId}")
    @Operation(summary = "Remove from cart", description = "Remove item from shopping cart")
    public ResponseEntity<ApiResponse<CartDTO>> removeFromCart(@PathVariable Long cartItemId) {
        log.info("DELETE /api/cart/items/{} - Remove cart item", cartItemId);
        CartDTO cart = cartService.removeFromCart(cartItemId);
        return ResponseEntity.ok(ApiResponse.success("Item removed from cart successfully", cart));
    }

    /**
     * Clear all items in cart
     */
    @DeleteMapping
    @Operation(summary = "Clear cart", description = "Remove all items from shopping cart")
    public ResponseEntity<ApiResponse<Void>> clearCart() {
        log.info("DELETE /api/cart - Clear cart");
        cartService.clearCart();
        return ResponseEntity.ok(ApiResponse.success("Cart cleared successfully"));
    }
}
