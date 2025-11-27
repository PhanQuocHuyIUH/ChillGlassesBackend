package iuh.chillteam.controller;

import iuh.chillteam.dto.response.ApiResponse;
import iuh.chillteam.dto.response.PageResponse;
import iuh.chillteam.entity.Product;
import iuh.chillteam.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * author: QUOC HUY
 * date: 07/11/2025
 */
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    /**
     * GET /api/products - Get all products with pagination
     * Example: GET /api/products?page=0&size=10&sort=name,asc
     */
    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<Product>>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        log.info("GET /api/products - page: {}, size: {}, sortBy: {}, sortDir: {}", page, size, sortBy, sortDir);

        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        PageResponse<Product> products = productService.getAllProducts(pageable);

        return ResponseEntity.ok(ApiResponse.success(products));
    }

    /**
     * GET /api/products/all - Get all products without pagination
     */
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Product>>> getAllProductsNoPaging() {
        log.info("GET /api/products/all");
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(ApiResponse.success(products));
    }

    /**
     * GET /api/products/{id} - Get product by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> getProductById(@PathVariable Long id) {
        log.info("GET /api/products/{}", id);
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(ApiResponse.success(product));
    }

    /**
     * POST /api/products - Create new product
     * Body: {"name": "Product A", "price": 100000}
     */
    @PostMapping
    public ResponseEntity<ApiResponse<Product>> createProduct(@RequestBody Product product) {
        log.info("POST /api/products - name: {}, price: {}", product.getName(), product.getPrice());
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Product created successfully", createdProduct));
    }

    /**
     * PUT /api/products/{id} - Update product
     * Body: {"name": "Updated Name", "price": 150000}
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> updateProduct(
            @PathVariable Long id,
            @RequestBody Product product) {

        log.info("PUT /api/products/{}", id);
        Product updatedProduct = productService.updateProduct(id, product);
        return ResponseEntity.ok(ApiResponse.success("Product updated successfully", updatedProduct));
    }

    /**
     * DELETE /api/products/{id} - Delete product (soft delete)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable Long id) {
        log.info("DELETE /api/products/{}", id);
        productService.deleteProduct(id);
        return ResponseEntity.ok(ApiResponse.success("Product deleted successfully"));
    }

    /**
     * GET /api/products/price-range - Get products by price range
     * Example: GET /api/products/price-range?min=50000&max=200000
     */
    @GetMapping("/price-range")
    public ResponseEntity<ApiResponse<List<Product>>> getProductsByPriceRange(
            @RequestParam Double min,
            @RequestParam Double max) {

        log.info("GET /api/products/price-range - min: {}, max: {}", min, max);
        List<Product> products = productService.getProductsByPriceRange(min, max);
        return ResponseEntity.ok(ApiResponse.success(products));
    }
}
