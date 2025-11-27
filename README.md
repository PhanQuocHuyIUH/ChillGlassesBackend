# ChillGlasses Backend - Task Management

## 📊 PHÂN CÔNG NHIỆM VỤ (4 THÀNH VIÊN)

| Member       | Tasks                                          | Total     | Module                                           | Classes cần implement                                                                                                                                                                                                                                                                                                                                                                                |
| ------------ | ---------------------------------------------- | --------- | ------------------------------------------------ | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Member 1** | 1-10 (Nhóm A), 38-44 (Nhóm I)                  | ~17 tasks | User + Auth + Profile + Notification + Email     | User, UserRepository, SecurityConfig, JwtUtil, JwtAuthenticationFilter, UserDetailsServiceImpl, AuthService, AuthController, UserController, Notification, NotificationRepository, NotificationService, NotificationController, EmailService, AsyncConfig, OtpService (optional)                                                                                                                     |
| **Member 2** | 11-25 (Nhóm B), 45-48 (Nhóm J), 49-51 (Nhóm K) | ~23 tasks | Category + Product + Admin + Recommendation      | Category, CategoryRepository, CategoryService, CategoryController, Product, ProductImage, ProductRepository, ProductImageRepository, ProductSpecification, ProductService, ProductController, ProductImageService, ImageService, AdminUserService, AdminUserController, StatisticsService, StatisticsController, ReportService, RecommendationService, RecommendationController, Wishlist (optional) |
| **Member 3** | 26-30 (Nhóm C), 31-37 (Nhóm D), 52-57 (Nhóm E) | ~18 tasks | Cart + Order + Payment + Promotion               | Cart, CartItem, CartRepository, CartItemRepository, CartService, CartController, Order, OrderItem, OrderRepository, OrderItemRepository, OrderService, OrderController, OrderStatusHistory, OrderTrackingService, PaymentService, PaymentController, Promotion, PromotionRepository, PromotionService, PromotionController                                                                           |
| **Member 4** | 58-60 (Nhóm F), 61-64 (Nhóm G), 65-66 (Nhóm H) | ~9 tasks  | Review + Exception Handler + Validation + Common | GlobalExceptionHandler, ApiResponse, PageResponse, Custom Validators (@ValidPhone, @ValidPassword, @ValidEnum), ProductReview, ProductReviewRepository, ReviewService, ReviewController, BaseEntity, JpaConfig, SlugUtils, StringUtils, FormatUtils                                                                                                                                                  |

---

## 🔧 TASKS CHƯA PHÂN CÔNG (Làm sau khi hoàn thành Priority 1-2)

**Performance & Optimization:**

- Task 67: Caching với Redis/Spring Cache
- Task 68: Database Query Optimization (N+1 query, index)

**Security:**

- Task 69: Security Hardening (Rate limiting, XSS protection, CORS)
- Task 70: API Documentation (Swagger/OpenAPI)

**DevOps:**

- Task 71: Application Properties Organization
- Task 72: Logging Configuration
- Task 73: Docker Configuration
- Task 74: Docker Compose
- Task 75: CI/CD Pipeline (Optional)
- Task 76: Health Check & Metrics (Actuator - Optional)

**Testing:**

- Task 77: Unit Tests cho Service Layer (>70% coverage)
- Task 78: Integration Tests (Repository, Controller, E2E flows)

---

## 🔀 Git Workflow

### Quy tắc làm việc với Git

⚠️ **QUAN TRỌNG**: Không commit trực tiếp lên `main` hoặc `develop`

### Quy trình làm việc cho mỗi task:

1. **Cập nhật nhánh develop**

   ```bash
   git checkout develop
   git pull origin develop
   ```

2. **Tạo nhánh mới cho task**

   ```bash
   git checkout -b dev/task-xxx
   ```

   Ví dụ: `dev/task-01`, `dev/task-02`, `dev/task-login`, v.v.

3. **Làm việc và commit**

   ```bash
   # Sau khi code xong
   git add .
   git commit -m "feat: mô tả task"
   ```

4. **Push nhánh lên remote**

   ```bash
   git push origin dev/task-xxx
   ```

5. **Tạo Pull Request**

   - Tạo PR từ `dev/task-xxx` → `develop`

### Ví dụ minh họa:

```bash
# Task 1: Public Layout
git checkout develop
git pull origin develop
git checkout -b dev/task-01
# ... code xong ...
git add .
git commit -m "feat: implement entities"
git push origin dev/task-01
# Tạo PR: dev/task-01 → develop trên github
```

---

## 🎯 MODULE PRIORITY

### ✅ **MUST HAVE (Priority 1)**

1. **Common Infrastructure** - Task 58-60 (Exception Handler, Response DTOs, Validation)
2. **User & Authentication** - Task 1-9 (User Entity, JWT, Security, Profile)
3. **Category & Product** - Task 11-20 (Category, Product, ProductImage, Filter, Search)
4. **Cart** - Task 26-30 (Cart Entity, Service, Controller)
5. **Order & Payment** - Task 31-36 (Order, OrderItem, Payment)
6. **Notification & Email** - Task 38-43 (Notification, Email Service, Async Queue)
7. **Admin Management** - Task 45-47 (User Management, Statistics, Dashboard)
8. **Review & Rating** - Task 61-64 (Review Entity, Service, Approve/Reject)

### 🔶 **SHOULD HAVE (Priority 2)**

9. **Promotion** - Task 52-56 (Promo code, Discount calculation)
10. **Order Tracking** - Task 37 (Status history timeline)
11. **AI Recommendation** - Task 49-51 (Similar products, Trending, Top Selling)
12. **Product View Counter** - Task 21 (Track views for recommendation)
13. **Common Utilities** - Task 65-66 (Base Entity, SlugUtils, StringUtils)
14. **Integration Hooks** - Task 44 (Connect Notification/Email with Order/Review)

### ⚠️ **NICE TO HAVE (Priority 3)**

15. Task 10 - 2FA (OTP Service)
16. Task 22 - Repository Tests (Product module)
17. Task 23 - Wishlist (Favorite products)
18. Task 24 - Search Autocomplete (UX improvement)
19. Task 25 - Product Stock History (Track stock changes)
20. Task 48 - Report Export (PDF/Excel)
21. Task 57 - Cart Repository Tests

### 🔧 **UNASSIGNED (Cross-cutting concerns - làm sau)**

22. Task 67 - Redis Caching
23. Task 68 - Query Optimization
24. Task 69 - Security Hardening
25. Task 70 - Swagger Documentation
26. Task 71-76 - DevOps (Config, Logging, Docker, CI/CD, Health Check)
27. Task 77-78 - Comprehensive Testing (Unit + Integration)

---

## Nhóm A – User & Authentication Module

**Người phụ trách: MEMBER 1**

| Task | Tên Task                          | Priority | Classes/Files cần implement                                                                                                                         | Mô tả                                                                                                                              |
| ---- | --------------------------------- | -------- | --------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- |
| 1    | User Entity + Enums               | 1        | `User.java` (entity), `Role.java` (enum), `UserStatus.java` (enum)                                                                                  | Entity với fields: fullName, email, password, phone, address, role, isActive, emailVerified, twoFactorEnabled, twoFactorSecret     |
| 2    | UserRepository                    | 1        | `UserRepository.java` (interface)                                                                                                                   | JpaRepository với methods: `findByEmail`, `findByRole`, `existsByEmail`                                                            |
| 3    | Security Configuration (Basic)    | 1        | `SecurityConfig.java` (@Configuration)                                                                                                              | PasswordEncoder (BCrypt), AuthenticationManager bean, CORS config, tạm thời permit all                                             |
| 4    | JWT Utility                       | 1        | `JwtUtil.java` (component)                                                                                                                          | generateToken, extractUsername, validateToken, extractClaims. Config: access token 15 phút, refresh token 7 ngày                   |
| 5    | UserDetailsService Implementation | 1        | `UserDetailsServiceImpl.java` (@Service)                                                                                                            | Implement UserDetailsService, load user by email, map User → UserDetails                                                           |
| 6    | JWT Filter & Security Chain       | 1        | `JwtAuthenticationFilter.java` (extends OncePerRequestFilter)                                                                                       | Filter JWT, update SecurityFilterChain: public `/api/auth/**`, `GET /api/products/**`, protected còn lại, disable CSRF             |
| 7    | Auth DTOs                         | 1        | `RegisterRequest.java`, `LoginRequest.java`, `LoginResponse.java`, `UserDTO.java`, `ChangePasswordRequest.java`, `ResetPasswordRequest.java` (DTOs) | Tất cả request/response DTOs cho authentication                                                                                    |
| 8    | Auth Service & Controller         | 1        | `AuthService.java` (@Service), `AuthController.java` (@RestController)                                                                              | Service: register, login, logout, refreshToken, changePassword, forgotPassword, resetPassword. Controller: endpoints `/api/auth/*` |
| 9    | User Profile Management           | 1        | `UserService.java` (@Service), `UserController.java` (@RestController), `UpdateProfileRequest.java` (DTO)                                           | Methods: getProfile, updateProfile, deleteAccount (soft delete). Endpoints: `GET/PUT/DELETE /api/users/me`                         |
| 10   | 2FA - OTP Service (Optional)      | 3        | `OtpService.java` (@Service), `TwoFactorController.java` (@RestController)                                                                          | Generate/verify OTP (in-memory/Redis), Google Authenticator integration. Endpoints: `/api/auth/2fa/enable`, `/verify`, `/disable`  |

**⚠️ Lưu ý Task 10:**

```sql
ALTER TABLE user
ADD COLUMN two_factor_enabled BOOLEAN DEFAULT FALSE,
ADD COLUMN two_factor_secret VARCHAR(255),
ADD COLUMN two_factor_method ENUM('OTP', 'TOTP') DEFAULT NULL;
```

---

## Nhóm B – Category & Product Module

**Người phụ trách: MEMBER 2**

| Task | Tên Task                          | Priority | Classes/Files cần implement                                                                                                                                                                                       | Mô tả                                                                                                                                                                                        |
| ---- | --------------------------------- | -------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 11   | Category Entity + Repository      | 1        | `Category.java` (entity), `CategoryRepository.java` (interface)                                                                                                                                                   | Entity: name, slug, description, isActive, displayOrder. Repository: `findBySlug`, `findByIsActive`                                                                                          |
| 12   | Category DTOs & Mapper            | 1        | `CategoryDTO.java`, `CategoryRequest.java`, `CategoryMapper.java` (optional)                                                                                                                                      | DTOs + Manual mapper hoặc MapStruct                                                                                                                                                          |
| 13   | Category Service & Controller     | 1        | `CategoryService.java` (@Service), `CategoryController.java` (@RestController)                                                                                                                                    | Service: getAll, getById, getBySlug, create, update, delete. Controller: `/api/categories` CRUD (GET public, POST/PUT/DELETE ADMIN only)                                                     |
| 14   | Product & ProductImage Entity     | 1        | `Product.java` (entity), `ProductImage.java` (entity)                                                                                                                                                             | Product: name, slug, description, price, originalPrice, brand, stockQuantity, rating, reviewCount, isActive, categoryId. ProductImage: productId, imageUrl, altText, isPrimary, displayOrder |
| 15   | Product & ProductImage Repository | 1        | `ProductRepository.java`, `ProductImageRepository.java` (interfaces)                                                                                                                                              | ProductRepository: findBySlug, findByCategoryId, findByBrand, findByIsActive. ProductImageRepository: findByProductId, findByProductIdAndIsPrimary                                           |
| 16   | Product DTOs & Mapper             | 1        | `ProductDTO.java`, `ProductListDTO.java`, `ProductDetailDTO.java`, `CreateProductRequest.java`, `UpdateProductRequest.java`, `ProductFilterRequest.java`, `ProductImageDTO.java`, `ProductMapper.java` (optional) | Tất cả DTOs + Mapper (Manual hoặc MapStruct)                                                                                                                                                 |
| 17   | Product Filter & Search           | 1        | `ProductSpecification.java` (JPA Criteria)                                                                                                                                                                        | Filter: category, brand, minPrice, maxPrice, inStock, searchKeyword. Sort: price, rating, createdAt                                                                                          |
| 18   | Product Service                   | 1        | `ProductService.java` (@Service)                                                                                                                                                                                  | Methods: getAll (filter + paging), getById, getBySlug, getByCategory, getByBrand, search, create, update, delete, updateStock. Tính rating trung bình từ reviews (stub)                      |
| 19   | Product Controller                | 1        | `ProductController.java` (@RestController)                                                                                                                                                                        | Endpoints: `/api/products/*` (GET public, POST/PUT/DELETE ADMIN). Apply filter, pagination, sorting                                                                                          |
| 20   | ProductImage Service & Controller | 1        | `ProductImageService.java` (@Service), `ImageService.java` (@Service), `ProductImageController.java` (@RestController)                                                                                            | ProductImageService: addImage, deleteImage, setPrimary, reorderImages. ImageService: upload/delete/getURL. Controller: `/api/products/{id}/images/*` (ADMIN)                                 |
| 21   | Product View Counter              | 2        | Update `Product.java` entity, `ProductService.java`                                                                                                                                                               | Thêm field `viewCount` vào Product, method `incrementViewCount()`, hook vào getById/getBySlug                                                                                                |
| 22   | Repository Tests                  | 3        | `CategoryRepositoryTest.java`, `ProductRepositoryTest.java`, `ProductImageRepositoryTest.java` (@DataJpaTest)                                                                                                     | Unit tests cho repositories                                                                                                                                                                  |
| 23   | Wishlist (Optional)               | 3        | `Wishlist.java` (entity), `WishlistRepository.java`, `WishlistService.java`, `WishlistController.java`                                                                                                            | Yêu thích sản phẩm. Endpoints: `POST/DELETE/GET /api/wishlist/*`, `GET /api/wishlist/check/{productId}`                                                                                      |
| 24   | Search Autocomplete (Optional)    | 3        | Update `ProductService.java`, `ProductController.java`                                                                                                                                                            | Method: `autocompleteSearch(keyword, limit)`. Endpoint: `GET /api/products/autocomplete?q=ray&limit=5`                                                                                       |
| 25   | Product Stock History (Optional)  | 3        | `StockHistory.java` (entity), `StockHistoryService.java`, `StockHistoryDTO.java`, update `AdminProductController.java`                                                                                            | Track stock changes. Methods: recordStockChange, getStockHistory. Hook vào ProductService.updateStock và OrderService.createOrder. Endpoint: `GET /api/admin/products/{id}/stock-history`    |

**⚠️ Lưu ý Task 23 - Wishlist:**

```sql
CREATE TABLE wishlist (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE,
    UNIQUE KEY uk_user_product (user_id, product_id),
    INDEX idx_user (user_id)
);
```

**⚠️ Lưu ý Task 25 - Stock History:**

```sql
CREATE TABLE stock_history (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_id BIGINT NOT NULL,
    change_type ENUM('IN', 'OUT') NOT NULL,
    quantity INT NOT NULL,
    reason VARCHAR(255),
    created_by BIGINT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES product(id),
    FOREIGN KEY (created_by) REFERENCES user(id),
    INDEX idx_product (product_id)
);
```

- `@DataJpaTest` cho CategoryRepository, ProductRepository, ProductImageRepository

### Task 23. Wishlist - Yêu thích sản phẩm (Priority 3 - Optional)

- Tạo bảng `wishlist`:
  ```sql
  CREATE TABLE wishlist (
      id BIGINT AUTO_INCREMENT PRIMARY KEY,
      user_id BIGINT NOT NULL,
      product_id BIGINT NOT NULL,
      created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
      FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
      FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE,
      UNIQUE KEY uk_user_product (user_id, product_id),
      INDEX idx_user (user_id)
  );
  ```
- `Wishlist` entity + `WishlistRepository`
- `WishlistService`:
  - `addToWishlist(userId, productId)`
  - `removeFromWishlist(userId, productId)`
  - `getMyWishlist(userId)` → `List<ProductDTO>`
  - `isInWishlist(userId, productId)` → `Boolean`
- `WishlistController`: `/api/wishlist/*`
  - `POST /api/wishlist/{productId}` - Thêm vào wishlist
  - `DELETE /api/wishlist/{productId}` - Xóa khỏi wishlist
  - `GET /api/wishlist` - Xem wishlist của mình
  - `GET /api/wishlist/check/{productId}` - Check có trong wishlist không

---

## Nhóm C – Cart Module

**Người phụ trách: MEMBER 3**

**Phụ thuộc:** Task 1-2 (User từ Member 1), Task 14-15 (Product từ Member 2) - có thể mock trước

| Task | Tên Task                   | Priority | Classes/Files cần implement                                                                               | Mô tả                                                                                                                                 |
| ---- | -------------------------- | -------- | --------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------- |
| 26   | Cart & CartItem Entity     | 1        | `Cart.java` (entity), `CartItem.java` (entity)                                                            | Cart: userId, unique constraint. CartItem: cartId, productId, quantity, unique constraint                                             |
| 27   | Cart & CartItem Repository | 1        | `CartRepository.java`, `CartItemRepository.java` (interfaces)                                             | CartRepository: findByUserId. CartItemRepository: findByCartId, findByCartIdAndProductId                                              |
| 28   | Cart DTOs                  | 1        | `CartDTO.java`, `CartItemDTO.java`, `AddToCartRequest.java`, `UpdateCartItemRequest.java`                 | Tất cả DTOs cho Cart module                                                                                                           |
| 29   | Cart Custom Exceptions     | 1        | `ProductNotFoundException.java`, `OutOfStockException.java`, `InvalidQuantityException.java` (exceptions) | Custom exceptions, xử lý trong GlobalExceptionHandler                                                                                 |
| 30   | Cart Service & Controller  | 1        | `CartService.java` (@Service), `CartController.java` (@RestController)                                    | Service: getCart, addToCart, updateCartItem, removeCartItem, clearCart, check tồn kho. Controller: `/api/cart/*` (authenticated only) |

---

## Nhóm D – Order & Payment Module

**Người phụ trách: MEMBER 3**

**Phụ thuộc:** Task 1-2 (User từ Member 1), Task 14-15 (Product từ Member 2), Task 26-27 (Cart - cùng member)

| Task | Tên Task                         | Priority | Classes/Files cần implement                                                                                                        | Mô tả                                                                                                                                                                                                                                            |
| ---- | -------------------------------- | -------- | ---------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| 31   | Order & OrderItem Entity + Enums | 1        | `Order.java` (entity), `OrderItem.java` (entity), `OrderStatus.java`, `PaymentMethod.java`, `PaymentStatus.java` (enums)           | Order: orderCode, userId, totalAmount, status, paymentMethod, paymentStatus, shippingAddress, shippingMethod, shippingFee, notes. OrderItem: orderId, productId, productName, productPrice, quantity, subtotal                                   |
| 32   | Order & OrderItem Repository     | 1        | `OrderRepository.java`, `OrderItemRepository.java` (interfaces)                                                                    | OrderRepository: findByUserId, findByOrderCode, findByStatus, findByUserIdAndStatus. OrderItemRepository: findByOrderId                                                                                                                          |
| 33   | Order DTOs                       | 1        | `CreateOrderRequest.java`, `OrderDTO.java`, `OrderItemDTO.java`, `OrderFilterRequest.java`                                         | Tất cả DTOs cho Order module                                                                                                                                                                                                                     |
| 34   | Order Service                    | 1        | `OrderService.java` (@Service)                                                                                                     | Methods: createOrder (validate cart, check stock, apply promotion, tính shipping, tạo order/items, trừ stock, clear cart), getOrderById, getOrderByCode, getMyOrders, getAllOrders (ADMIN), updateOrderStatus, cancelOrder, calculateShippingFee |
| 35   | Order Controller                 | 1        | `OrderController.java` (@RestController)                                                                                           | Endpoints: `/api/orders/*` (User: tạo đơn, xem đơn, cancel. Admin: xem tất cả, update status, filter)                                                                                                                                            |
| 36   | Payment Service & Controller     | 1        | `PaymentService.java` (@Service), `PaymentController.java` (@RestController)                                                       | Service: processPayment, verifyPayment, refundPayment. Mock hoặc integrate payment gateway (COD/Bank/E-wallet). Controller: tạo payment URL, webhook/callback                                                                                    |
| 37   | Order Tracking Detail            | 2        | `OrderStatusHistory.java` (entity), `OrderStatusHistoryRepository.java`, `OrderTrackingService.java`, `OrderStatusHistoryDTO.java` | Track order status changes. Methods: recordStatusChange, getOrderTracking. Hook vào updateOrderStatus. Endpoint: `GET /api/orders/{id}/tracking`                                                                                                 |

---

## Nhóm E – Promotion Module

**Người phụ trách: MEMBER 3**

**Phụ thuộc:** Có thể làm song song với Order

| Task | Tên Task                 | Priority | Classes/Files cần implement                                                                                    | Mô tả                                                                                                                                                                            |
| ---- | ------------------------ | -------- | -------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 52   | Promotion Entity + Enum  | 2        | `Promotion.java` (entity), `DiscountType.java` (enum)                                                          | Promotion: code, description, discountType, discountValue, minOrderValue, maxDiscountAmount, usageLimit, usedCount, startDate, endDate, isActive. Enum: PERCENTAGE, FIXED_AMOUNT |
| 53   | Promotion Repository     | 2        | `PromotionRepository.java` (interface)                                                                         | Methods: findByCode, findByIsActiveAndDates                                                                                                                                      |
| 54   | Promotion DTOs & Service | 2        | `PromotionDTO.java`, `PromotionRequest.java`, `ApplyPromotionRequest.java`, `PromotionService.java` (@Service) | Service: validatePromoCode, applyPromotion (tính discount), create, update, deactivate, incrementUsedCount. Business logic: check dates, usage limit, min order value            |
| 55   | Promotion Controller     | 2        | `PromotionController.java` (@RestController)                                                                   | Public: `POST /api/promotions/validate`. Admin: CRUD `/api/promotions/*`                                                                                                         |
| 56   | Promotion Integration    | 2        | Update `OrderService.java`                                                                                     | Hook PromotionService vào createOrder(), apply discount, increment usedCount                                                                                                     |
| 57   | Cart Repository Tests    | 3        | `CartRepositoryTest.java`, `CartItemRepositoryTest.java` (@DataJpaTest)                                        | Unit tests cho Cart & CartItem repositories                                                                                                                                      |

---

## Nhóm F – Common Infrastructure (Foundation)

**Người phụ trách: MEMBER 4**

**Phụ thuộc:** Không phụ thuộc module nào - làm đầu tiên

| Task | Tên Task                          | Priority | Classes/Files cần implement                                                                                                                                                                                         | Mô tả                                                                                                                                                                                                                        |
| ---- | --------------------------------- | -------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 58   | Global Exception Handler          | 1        | `GlobalExceptionHandler.java` (@RestControllerAdvice), `ResourceNotFoundException.java`, `BadRequestException.java`, `UnauthorizedException.java`, `ForbiddenException.java`, `ConflictException.java` (exceptions) | Handle tất cả exceptions: 404, 400, 401, 403, 409, validation errors, 500. Return standardized error response                                                                                                                |
| 59   | API Response & Page Response DTOs | 1        | `ApiResponse.java` (generic DTO), `PageResponse.java` (generic DTO)                                                                                                                                                 | ApiResponse: fields (code, message, data, errors, timestamp), static methods (success, error). PageResponse: convert Spring Data Page, fields (content, pageNumber, pageSize, totalElements, totalPages, first, last, empty) |
| 60   | Validation Enhancement            | 1        | `ValidPhone.java` (annotation), `ValidPassword.java` (annotation), `ValidEnum.java` (annotation), `PhoneValidator.java`, `PasswordValidator.java`, `EnumValidator.java` (validators)                                | Custom validators cho phone VN, password strength, enum. Rà soát thêm annotations (@NotNull, @NotBlank, @Size, @Email, @Min, @Max, @Pattern) cho tất cả DTOs                                                                 |

---

## Nhóm G – Review & Rating Module

**Người phụ trách: MEMBER 4**

**Phụ thuộc:** Task 14-15 (Product từ Member 2), Task 31-32 (Order từ Member 3)

| Task | Tên Task                    | Priority | Classes/Files cần implement                                                                               | Mô tả                                                                                                                                                                                                                          |
| ---- | --------------------------- | -------- | --------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| 61   | ProductReview Entity + Enum | 1        | `ProductReview.java` (entity), `ReviewStatus.java` (enum)                                                 | ProductReview: productId, userId, orderId, rating, comment, status. Unique constraint: (userId, productId, orderId). Enum: PENDING, APPROVED, REJECTED                                                                         |
| 62   | ProductReview Repository    | 1        | `ProductReviewRepository.java` (interface)                                                                | Methods: findByProductId, findByUserId, findByStatus, findByUserIdAndProductIdAndOrderId                                                                                                                                       |
| 63   | Review DTOs & Service       | 1        | `CreateReviewRequest.java`, `ReviewDTO.java`, `UpdateReviewRequest.java`, `ReviewService.java` (@Service) | Service: createReview (check user đã mua, chưa review trùng), getProductReviews, getMyReviews, updateReview, deleteReview, approveReview, rejectReview (ADMIN), updateProductRating (tính lại rating trung bình + reviewCount) |
| 64   | Review Controller           | 1        | `ReviewController.java` (@RestController)                                                                 | User: tạo, sửa, xóa review của mình. Public: xem review sản phẩm. Admin: approve/reject review                                                                                                                                 |

---

## Nhóm H – Common Utilities (Optional)

**Người phụ trách: MEMBER 4**

**Phụ thuộc:** Không - có thể làm song song với Review module

| Task | Tên Task                   | Priority | Classes/Files cần implement                                                                    | Mô tả                                                                                                                                                                                                                                                                                 |
| ---- | -------------------------- | -------- | ---------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 65   | Base Entity & JPA Auditing | 2        | `BaseEntity.java` (abstract class), `JpaConfig.java` (@Configuration), `AuditorAwareImpl.java` | BaseEntity: fields (id, createdAt, updatedAt, deletedAt), methods (isDeleted, softDelete, restore), annotations (@MappedSuperclass, @EntityListeners). JpaConfig: @EnableJpaAuditing, AuditorAware bean. Lợi ích: tất cả entity extends BaseEntity có auditing + soft delete          |
| 66   | SlugUtils & StringUtils    | 2        | `SlugUtils.java`, `StringUtils.java`, `FormatUtils.java` (utility classes)                     | SlugUtils: toSlug, generateUniqueSlug, convertVietnamese (Vietnamese → URL slug). StringUtils: isEmpty, isNotEmpty, truncate, capitalize, randomString, randomNumeric, maskEmail, maskPhone. FormatUtils: formatPrice (VND), formatPercent. Sử dụng: Product/Category slug generation |

---

## Nhóm I – Notification & Email Module

**Người phụ trách: MEMBER 1**

**Phụ thuộc:** Task 1-2 (User - cùng member) - có thể bắt đầu entity ngay

| Task | Tên Task                       | Priority | Classes/Files cần implement                                                                              | Mô tả                                                                                                                                                             |
| ---- | ------------------------------ | -------- | -------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 38   | Notification Entity + Enum     | 1        | `Notification.java` (entity), `NotificationType.java` (enum)                                             | Notification: userId, title, message, type, isRead. Enum: ORDER, PROMOTION, SYSTEM                                                                                |
| 39   | Notification Repository        | 1        | `NotificationRepository.java` (interface)                                                                | Methods: findByUserId, findByUserIdAndIsRead, countByUserIdAndIsRead                                                                                              |
| 40   | Notification DTOs & Service    | 1        | `NotificationDTO.java`, `NotificationService.java` (@Service)                                            | Service: createNotification, getMyNotifications, markAsRead, markAllAsRead, deleteNotification, getUnreadCount                                                    |
| 41   | Notification Controller        | 1        | `NotificationController.java` (@RestController)                                                          | Endpoints: `/api/notifications/*` (GET list/unread count, PUT mark as read/read all, DELETE)                                                                      |
| 42   | Email Configuration & Service  | 1        | `EmailService.java` (@Service), `application.yml` (config)                                               | Cấu hình Spring Mail (SMTP - Gmail/SendGrid). Methods: sendOrderConfirmation, sendOrderStatusUpdate, sendPasswordResetEmail, sendWelcomeEmail, sendPromotionEmail |
| 43   | Email Queue - Async Processing | 2        | `AsyncConfig.java` (@Configuration), update `EmailService.java`                                          | Config @EnableAsync, ThreadPoolTaskExecutor (emailTaskExecutor). Update EmailService với @Async. Lợi ích: không block main thread                                 |
| 44   | Integration Hooks              | 2        | Update `OrderService.java`, `ReviewService.java`, `AuthService.java`, `PromotionService.java` (optional) | Hook notification/email vào: OrderService (tạo đơn, đổi trạng thái), ReviewService (approve), AuthService (register, reset password)                              |

---

## Nhóm J – Admin Management Module

**Người phụ trách: MEMBER 2**

**Phụ thuộc:** Task 1-2 (User từ Member 1), Task 31-32 (Order từ Member 3), Task 14-15 (Product - cùng member)

| Task | Tên Task                  | Priority | Classes/Files cần implement                                                                                      | Mô tả                                                                                                                                                                                                   |
| ---- | ------------------------- | -------- | ---------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 45   | Admin User Management     | 1        | `UpdateUserRequest.java` (DTO), `AdminUserService.java` (@Service), `AdminUserController.java` (@RestController) | Extend UserDTO. Service: getAllUsers (filter + paging), getUserById, updateUser, lockUser, unlockUser, deleteUser. Controller: `/api/admin/users/*` CRUD + lock/unlock                                  |
| 46   | Statistics Service        | 1        | `StatisticsService.java` (@Service), `DashboardStatsDTO.java`, `RevenueStatsDTO.java` (DTOs)                     | Methods: getDashboardStats (tổng quan: orders, revenue, users, products), getRevenueByPeriod (theo ngày/tuần/tháng), getOrdersByStatus, getTopSellingProducts, getInventoryReport (stock thấp)          |
| 47   | Statistics Controller     | 1        | `StatisticsController.java` (@RestController)                                                                    | Endpoints: `/api/admin/statistics/*` (Dashboard, revenue, orders, products, inventory)                                                                                                                  |
| 48   | Report Export (Excel/PDF) | 3        | `ReportService.java` (@Service), `ReportController.java` (@RestController), `pom.xml` (dependencies)             | Thêm dependency: Apache POI (Excel), iText/OpenPDF (PDF). Service: exportRevenue, exportOrders, exportInventory, exportOrderInvoice. Controller: `/api/admin/reports/*`, `/api/orders/{id}/invoice/pdf` |

---

## Nhóm K – Recommendation Module (Should Have)

**Người phụ trách: MEMBER 2 (làm bản đơn giản)**

**Phụ thuộc:** Task 14-15 (Product - cùng member), Task 31-32 (Order từ Member 3)

| Task | Tên Task                  | Priority | Classes/Files cần implement                                                                 | Mô tả                                                                                                                                                                                               |
| ---- | ------------------------- | -------- | ------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 49   | Recommendation Tracking   | 2        | `ProductViewHistory.java` (entity - optional), update `Product.java` (thêm viewCount field) | Tùy chọn: Tạo bảng product_view_history HOẶC dùng Product.viewCount field. Track trong memory/cache tạm thời. Hook vào ProductService.getById()                                                     |
| 50   | Recommendation Service    | 2        | `RecommendationService.java` (@Service)                                                     | Logic đơn giản KHÔNG cần ML: getSimilarProducts (query by category + brand), getTrendingProducts (ORDER BY createdAt DESC), getTopSelling (JOIN OrderItem, GROUP BY product, ORDER BY SUM quantity) |
| 51   | Recommendation Controller | 2        | `RecommendationController.java` (@RestController) hoặc update `ProductController.java`      | Endpoints: `GET /api/products/recommended` (top selling), `GET /api/products/trending` (sản phẩm mới), `GET /api/products/{id}/similar` (same category/brand)                                       |

---

## 📦 TASKS CHƯA PHÂN CÔNG (Cross-cutting Concerns)

**Các task này sẽ được phân công sau khi hoàn thành Priority 1-2**

### Task 67. Caching với Redis/Spring Cache

- Thêm dependency Redis (nếu chưa)
- Cấu hình `CacheManager`
- Apply `@Cacheable`, `@CacheEvict`, `@CachePut`:
  - Product list, product detail, category list
  - User profile (nếu cần)
- TTL phù hợp

### Task 68. Database Query Optimization

- Rà soát N+1 query, dùng `@EntityGraph` / `JOIN FETCH`
- Đảm bảo pagination cho tất cả list lớn
- Xác nhận index đã đúng (email, slug, userId, productId, orderId)
- Analyze slow queries

### Task 69. Security Hardening

- Rate limiting (Bucket4j) cho `/api/auth/**` và API chung
- XSS protection: sanitize input
- CORS chi tiết hơn (whitelist origins)
- Security headers (CSP, X-Frame-Options, etc.)
- Audit: rà soát authorization cho tất cả endpoint

### Task 70. API Documentation (Swagger/OpenAPI)

- Thêm dependency: springdoc-openapi-ui
- Config Swagger UI
- Gắn `@Tag`, `@Operation`, `@ApiResponse`, `@Schema` cho:
  - Tất cả controller
  - Tất cả DTO
- Customize Swagger UI (logo, description)

### Task 71. Application Properties Organization

- Tách config theo môi trường:
  - `application.yml` (common)
  - `application-dev.yml`
  - `application-prod.yml`
- Externalize sensitive config (DB password, JWT secret)

### Task 72. Logging Configuration

- Cấu hình Logback/Log4j2
- Log level theo môi trường
- Log file rotation
- Log format chuẩn (timestamp, level, class, message)

### Task 73. Docker Configuration

- Viết `Dockerfile` multi-stage build:
  - Stage 1: Maven build
  - Stage 2: Runtime với JRE
- Optimize layer caching

### Task 74. Docker Compose

- `docker-compose.yml` với services:
  - App (Spring Boot)
  - MariaDB
  - Redis (nếu dùng)
- Volume cho data persistence
- Network configuration
- Health checks

### Task 75. CI/CD Pipeline (Optional)

- GitHub Actions hoặc GitLab CI
- Pipeline: build -> test -> docker build -> deploy
- Environment variables management

### Task 76. Health Check & Metrics (Optional)

- Thêm dependency: `spring-boot-starter-actuator`
- Config `application.yml`:
  ```yaml
  management:
    endpoints:
      web:
        exposure:
          include: health,info,metrics,prometheus
    endpoint:
      health:
        show-details: when-authorized
  ```
- Custom Health Indicator:
  ```java
  @Component
  public class DatabaseHealthIndicator implements HealthIndicator {
      @Override
      public Health health() {
          // Check DB connection
          return Health.up().withDetail("database", "MariaDB OK").build();
      }
  }
  ```
- Endpoints:
  - `GET /actuator/health` - Health status
  - `GET /actuator/metrics` - App metrics
  - `GET /actuator/info` - App info
- **Production:** Bảo mật actuator endpoints (Spring Security)

### Task 77. Unit Tests cho Service Layer

- JUnit 5 + Mockito
- Test coverage cho:
  - AuthService, UserDetailsService
  - CategoryService, ProductService
  - CartService
  - OrderService, PaymentService
  - PromotionService, ReviewService
  - NotificationService, EmailService
- Target: >70% coverage cho service layer

### Task 78. Integration Tests

- `@DataJpaTest` cho tất cả repository (mở rộng Task 22, 57)
- `@WebMvcTest` cho controller chính (Auth, Product, Order)
- `@SpringBootTest` cho flow quan trọng:
  - Register -> Login -> Add to Cart -> Create Order
  - Create Review -> Approve -> Update Product Rating

---
