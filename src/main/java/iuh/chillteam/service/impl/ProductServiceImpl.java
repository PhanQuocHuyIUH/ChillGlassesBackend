package iuh.chillteam.service.impl;

import iuh.chillteam.dto.response.PageResponse;
import iuh.chillteam.entity.Product;
import iuh.chillteam.exception.BadRequestException;
import iuh.chillteam.exception.ResourceNotFoundException;
import iuh.chillteam.repository.ProductRepository;
import iuh.chillteam.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * author: QUOC HUY
 * date: 07/11/2025
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public PageResponse<Product> getAllProducts(Pageable pageable) {
        log.info("Getting all products with pagination: page={}, size={}", pageable.getPageNumber(), pageable.getPageSize());
        Page<Product> productPage = productRepository.findAllActive(pageable);
        return PageResponse.of(productPage);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        log.info("Getting all products");
        return productRepository.findAllActive();
    }

    @Override
    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        log.info("Getting product by id: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        // Check if soft deleted
        if (product.isDeleted()) {
            throw new ResourceNotFoundException("Product has been deleted");
        }

        return product;
    }

    @Override
    public Product createProduct(Product product) {
        log.info("Creating new product: {}", product.getName());

        // Validate
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new BadRequestException("Product name is required");
        }

        if (product.getPrice() == null || product.getPrice() <= 0) {
            throw new BadRequestException("Product price must be greater than 0");
        }

        // Check duplicate name
        productRepository.findByName(product.getName())
                .ifPresent(p -> {
                    throw new BadRequestException("Product name already exists");
                });

        Product savedProduct = productRepository.save(product);
        log.info("Product created successfully with id: {}", savedProduct.getId());
        return savedProduct;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        log.info("Updating product with id: {}", id);

        Product existingProduct = getProductById(id);

        // Update fields
        if (product.getName() != null && !product.getName().trim().isEmpty()) {
            existingProduct.setName(product.getName());
        }

        if (product.getPrice() != null && product.getPrice() > 0) {
            existingProduct.setPrice(product.getPrice());
        }

        Product updatedProduct = productRepository.save(existingProduct);
        log.info("Product updated successfully");
        return updatedProduct;
    }

    @Override
    public void deleteProduct(Long id) {
        log.info("Deleting product with id: {}", id);

        Product product = getProductById(id);

        // Soft delete
        product.softDelete();
        productRepository.save(product);

        log.info("Product deleted successfully (soft delete)");
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getProductsByPriceRange(Double minPrice, Double maxPrice) {
        log.info("Getting products by price range: {} - {}", minPrice, maxPrice);

        if (minPrice < 0 || maxPrice < 0) {
            throw new BadRequestException("Price must be greater than or equal to 0");
        }

        if (minPrice > maxPrice) {
            throw new BadRequestException("Min price must be less than or equal to max price");
        }

        return productRepository.findByPriceRange(minPrice, maxPrice);
    }
}
