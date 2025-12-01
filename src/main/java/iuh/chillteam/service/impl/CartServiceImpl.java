package iuh.chillteam.service.impl;

import iuh.chillteam.dto.cart.AddToCartRequest;
import iuh.chillteam.dto.cart.CartDTO;
import iuh.chillteam.dto.cart.CartItemDTO;
import iuh.chillteam.dto.cart.UpdateCartItemRequest;
import iuh.chillteam.entity.*;
import iuh.chillteam.exception.BadRequestException;
import iuh.chillteam.exception.ResourceNotFoundException;
import iuh.chillteam.exception.UnauthorizedException;
import iuh.chillteam.repository.CartItemRepository;
import iuh.chillteam.repository.CartRepository;
import iuh.chillteam.repository.ProductImageRepository;
import iuh.chillteam.repository.ProductRepository;
import iuh.chillteam.security.UserDetailsServiceImpl;
import iuh.chillteam.service.CartService;
import iuh.chillteam.utils.FormatUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Cart Service Implementation
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    @Override
    @Transactional(readOnly = true)
    public CartDTO getCart() {
        User user = getCurrentAuthenticatedUser();
        log.info("Getting cart for user: {}", user.getEmail());

        Cart cart = getOrCreateCart(user);
        return convertToDTO(cart);
    }

    @Override
    public CartDTO addToCart(AddToCartRequest request) {
        User user = getCurrentAuthenticatedUser();
        log.info("Adding product {} to cart for user: {}", request.getProductId(), user.getEmail());

        // Validate product
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + request.getProductId()));

        if (product.getDeletedAt() != null) {
            throw new BadRequestException("Product is no longer available");
        }

        if (!product.getIsActive()) {
            throw new BadRequestException("Product is not active");
        }

        if (product.getStockQuantity() < request.getQuantity()) {
            throw new BadRequestException("Not enough stock. Available: " + product.getStockQuantity());
        }

        // Get or create cart
        Cart cart = getOrCreateCart(user);

        // Check if product already in cart
        CartItem existingItem = cartItemRepository
                .findByCartIdAndProductId(cart.getId(), product.getId())
                .orElse(null);

        if (existingItem != null) {
            // Update quantity
            int newQuantity = existingItem.getQuantity() + request.getQuantity();
            
            if (newQuantity > product.getStockQuantity()) {
                throw new BadRequestException("Not enough stock. Available: " + product.getStockQuantity());
            }

            existingItem.setQuantity(newQuantity);
            cartItemRepository.save(existingItem);
            log.info("Updated cart item quantity to: {}", newQuantity);
        } else {
            // Add new item
            CartItem cartItem = CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(request.getQuantity())
                    .build();
            cartItemRepository.save(cartItem);
            log.info("Added new item to cart");
        }

        return convertToDTO(cart);
    }

    @Override
    public CartDTO updateCartItem(Long cartItemId, UpdateCartItemRequest request) {
        User user = getCurrentAuthenticatedUser();
        log.info("Updating cart item {} for user: {}", cartItemId, user.getEmail());

        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found with id: " + cartItemId));

        // Verify ownership
        if (!cartItem.getCart().getUser().getId().equals(user.getId())) {
            throw new UnauthorizedException("You don't have permission to update this cart item");
        }

        // Validate stock
        Product product = cartItem.getProduct();
        if (request.getQuantity() > product.getStockQuantity()) {
            throw new BadRequestException("Not enough stock. Available: " + product.getStockQuantity());
        }

        cartItem.setQuantity(request.getQuantity());
        cartItemRepository.save(cartItem);

        log.info("Updated cart item quantity to: {}", request.getQuantity());
        return convertToDTO(cartItem.getCart());
    }

    @Override
    public CartDTO removeFromCart(Long cartItemId) {
        User user = getCurrentAuthenticatedUser();
        log.info("Removing cart item {} for user: {}", cartItemId, user.getEmail());

        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found with id: " + cartItemId));

        // Verify ownership
        if (!cartItem.getCart().getUser().getId().equals(user.getId())) {
            throw new UnauthorizedException("You don't have permission to remove this cart item");
        }

        Cart cart = cartItem.getCart();
        cartItemRepository.delete(cartItem);

        log.info("Removed cart item successfully");
        return convertToDTO(cart);
    }

    @Override
    public void clearCart() {
        User user = getCurrentAuthenticatedUser();
        log.info("Clearing cart for user: {}", user.getEmail());

        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        cartItemRepository.deleteByCartId(cart.getId());
        log.info("Cart cleared successfully");
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getCartItemCount() {
        User user = getCurrentAuthenticatedUser();
        
        Cart cart = cartRepository.findByUserId(user.getId()).orElse(null);
        if (cart == null) {
            return 0;
        }

        return (int) cartItemRepository.countByCartId(cart.getId());
    }

    /**
     * Get or create cart for user
     */
    private Cart getOrCreateCart(User user) {
        return cartRepository.findByUserId(user.getId())
                .orElseGet(() -> {
                    Cart newCart = Cart.builder()
                            .user(user)
                            .build();
                    return cartRepository.save(newCart);
                });
    }

    /**
     * Convert Cart entity to DTO
     */
    private CartDTO convertToDTO(Cart cart) {
        List<CartItem> cartItems = cartItemRepository.findByCartId(cart.getId());

        List<CartItemDTO> itemDTOs = cartItems.stream()
                .map(this::convertItemToDTO)
                .collect(Collectors.toList());

        Double totalAmount = cartItemRepository.calculateTotalAmount(cart.getId());

        return CartDTO.builder()
                .id(cart.getId())
                .userId(cart.getUser().getId())
                .items(itemDTOs)
                .totalItems(itemDTOs.size())
                .totalAmount(totalAmount)
                .formattedTotalAmount(FormatUtils.formatPrice(totalAmount))
                .createdAt(cart.getCreatedAt())
                .updatedAt(cart.getUpdatedAt())
                .build();
    }

    /**
     * Convert CartItem entity to DTO
     */
    private CartItemDTO convertItemToDTO(CartItem cartItem) {
        Product product = cartItem.getProduct();

        // Get primary image
        String primaryImageUrl = productImageRepository
                .findPrimaryImageByProductId(product.getId())
                .map(ProductImage::getImageUrl)
                .orElse(null);

        Double subtotal = cartItem.getSubtotal();
        boolean inStock = product.getStockQuantity() >= cartItem.getQuantity();

        return CartItemDTO.builder()
                .id(cartItem.getId())
                .productId(product.getId())
                .productName(product.getName())
                .productSlug(product.getSlug())
                .productPrice(product.getPrice())
                .formattedProductPrice(FormatUtils.formatPrice(product.getPrice()))
                .productImageUrl(primaryImageUrl)
                .brand(product.getBrand())
                .stockQuantity(product.getStockQuantity())
                .quantity(cartItem.getQuantity())
                .subtotal(subtotal)
                .formattedSubtotal(FormatUtils.formatPrice(subtotal))
                .inStock(inStock)
                .build();
    }

    /**
     * Get current authenticated user from security context
     */
    private User getCurrentAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthorizedException("User not authenticated");
        }

        if (authentication.getPrincipal() instanceof UserDetailsServiceImpl.CustomUserDetails userDetails) {
            return userDetails.getUser();
        }

        throw new UnauthorizedException("Invalid authentication");
    }
}
