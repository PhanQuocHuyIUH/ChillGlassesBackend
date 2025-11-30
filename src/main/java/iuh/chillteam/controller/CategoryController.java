package iuh.chillteam.controller;

import iuh.chillteam.dto.category.CategoryDTO;
import iuh.chillteam.dto.category.CategoryRequest;
import iuh.chillteam.dto.common.ApiResponse;
import iuh.chillteam.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Category Controller
 *
 * Public endpoints:
 * - GET /api/categories (xem tất cả)
 * - GET /api/categories/active (xem active only)
 * - GET /api/categories/{id} (xem chi tiết)
 * - GET /api/categories/slug/{slug} (xem theo slug)
 *
 * Admin only endpoints:
 * - POST /api/categories (tạo mới)
 * - PUT /api/categories/{id} (cập nhật)
 * - DELETE /api/categories/{id} (xóa)
 * - PATCH /api/categories/{id}/activate (kích hoạt)
 * - PATCH /api/categories/{id}/deactivate (vô hiệu hóa)
 */
@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * GET /api/categories
     * Lấy tất cả categories (Public)
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryDTO>>> getAllCategories() {
        log.info("REST request to get all categories");
        List<CategoryDTO> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(ApiResponse.success(categories));
    }

    /**
     * GET /api/categories/active
     * Lấy tất cả categories active (Public)
     */
    @GetMapping("/active")
    public ResponseEntity<ApiResponse<List<CategoryDTO>>> getAllActiveCategories() {
        log.info("REST request to get all active categories");
        List<CategoryDTO> categories = categoryService.getAllActiveCategories();
        return ResponseEntity.ok(ApiResponse.success(categories));
    }

    /**
     * GET /api/categories/{id}
     * Lấy category theo ID (Public)
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryDTO>> getCategoryById(@PathVariable Long id) {
        log.info("REST request to get category by id: {}", id);
        CategoryDTO category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(ApiResponse.success(category));
    }

    /**
     * GET /api/categories/slug/{slug}
     * Lấy category theo slug (Public)
     */
    @GetMapping("/slug/{slug}")
    public ResponseEntity<ApiResponse<CategoryDTO>> getCategoryBySlug(@PathVariable String slug) {
        log.info("REST request to get category by slug: {}", slug);
        CategoryDTO category = categoryService.getCategoryBySlug(slug);
        return ResponseEntity.ok(ApiResponse.success(category));
    }

    /**
     * POST /api/categories
     * Tạo category mới (ADMIN only)
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<CategoryDTO>> createCategory(
            @Valid @RequestBody CategoryRequest request) {
        log.info("REST request to create category: {}", request.getName());
        CategoryDTO category = categoryService.createCategory(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Category created successfully", category));
    }

    /**
     * PUT /api/categories/{id}
     * Cập nhật category (ADMIN only)
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<CategoryDTO>> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryRequest request) {
        log.info("REST request to update category id: {}", id);
        CategoryDTO category = categoryService.updateCategory(id, request);
        return ResponseEntity.ok(ApiResponse.success("Category updated successfully", category));
    }

    /**
     * DELETE /api/categories/{id}
     * Xóa category - soft delete (ADMIN only)
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteCategory(@PathVariable Long id) {
        log.info("REST request to delete category id: {}", id);
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(ApiResponse.success("Category deleted successfully"));
    }

    /**
     * DELETE /api/categories/{id}/hard
     * Xóa category vĩnh viễn - hard delete (ADMIN only)
     */
    @DeleteMapping("/{id}/hard")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> hardDeleteCategory(@PathVariable Long id) {
        log.info("REST request to hard delete category id: {}", id);
        categoryService.hardDeleteCategory(id);
        return ResponseEntity.ok(ApiResponse.success("Category permanently deleted"));
    }

    /**
     * PATCH /api/categories/{id}/activate
     * Kích hoạt category (ADMIN only)
     */
    @PatchMapping("/{id}/activate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<CategoryDTO>> activateCategory(@PathVariable Long id) {
        log.info("REST request to activate category id: {}", id);
        CategoryDTO category = categoryService.activateCategory(id);
        return ResponseEntity.ok(ApiResponse.success("Category activated successfully", category));
    }

    /**
     * PATCH /api/categories/{id}/deactivate
     * Vô hiệu hóa category (ADMIN only)
     */
    @PatchMapping("/{id}/deactivate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<CategoryDTO>> deactivateCategory(@PathVariable Long id) {
        log.info("REST request to deactivate category id: {}", id);
        CategoryDTO category = categoryService.deactivateCategory(id);
        return ResponseEntity.ok(ApiResponse.success("Category deactivated successfully", category));
    }
}