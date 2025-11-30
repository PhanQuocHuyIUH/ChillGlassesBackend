package iuh.chillteam.service.impl;

import iuh.chillteam.dto.common.PageResponse;
import iuh.chillteam.dto.image.ProductImageDTO;
import iuh.chillteam.dto.product.CreateProductRequest;
import iuh.chillteam.dto.product.ProductFilterRequest;
import iuh.chillteam.dto.product.UpdateProductRequest;
import iuh.chillteam.dto.product.ProductDetailDTO;
import iuh.chillteam.dto.product.ProductListDTO;
import iuh.chillteam.entity.Category;
import iuh.chillteam.entity.Product;
import iuh.chillteam.entity.ProductImage;
import iuh.chillteam.exception.BadRequestException;
import iuh.chillteam.exception.ResourceNotFoundException;
import iuh.chillteam.repository.CategoryRepository;
import iuh.chillteam.repository.ProductImageRepository;
import iuh.chillteam.repository.ProductRepository;
import iuh.chillteam.service.ProductService;
import iuh.chillteam.specification.ProductSpecification;
import iuh.chillteam.utils.FormatUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Product Service Implementation
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductImageRepository productImageRepository;

    @Override
    @Transactional(readOnly = true)
    public PageResponse<ProductListDTO> getAllProducts(ProductFilterRequest filter, Pageable pageable) {
        log.info("Getting all products with filter: {}", filter);
        Specification<Product> spec = ProductSpecification.filterProducts(filter);
        Page<Product> productPage = productRepository.findAll(spec, pageable);
        Page<ProductListDTO> dtoPage = productPage.map(this::convertToListDTO);
        return PageResponse.of(dtoPage);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDetailDTO getProductById(Long id) {
        log.info("Getting product by id: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        if (product.getDeletedAt() != null) {
            throw new ResourceNotFoundException("Product has been deleted");
        }
        return convertToDetailDTO(product);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDetailDTO getProductBySlug(String slug) {
        log.info("Getting product by slug: {}", slug);
        Product product = productRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with slug: " + slug));
        return convertToDetailDTO(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductListDTO> getProductsByCategory(Long categoryId) {
        log.info("Getting products by category: {}", categoryId);
        List<Product> products = productRepository.findByCategoryId(categoryId);
        return products.stream().map(this::convertToListDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductListDTO> getProductsByBrand(String brand) {
        log.info("Getting products by brand: {}", brand);
        List<Product> products = productRepository.findByBrand(brand);
        return products.stream().map(this::convertToListDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<ProductListDTO> searchProducts(String keyword, Pageable pageable) {
        log.info("Searching products with keyword: {}", keyword);
        ProductFilterRequest filter = ProductFilterRequest.builder().keyword(keyword).isActive(true).build();
        return getAllProducts(filter, pageable);
    }

    @Override
    public ProductDetailDTO createProduct(CreateProductRequest request) {
        log.info("Creating product: {}", request.getName());
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + request.getCategoryId()));
        if (category.getDeletedAt() != null) {
            throw new BadRequestException("Cannot create product with deleted category");
        }
        if (productRepository.existsByNameAndDeletedAtIsNull(request.getName())) {
            throw new BadRequestException("Product with name '" + request.getName() + "' already exists");
        }
        Product product = Product.builder()
                .name(request.getName()).slug(request.getSlug()).description(request.getDescription())
                .price(request.getPrice()).originalPrice(request.getOriginalPrice()).brand(request.getBrand())
                .category(category).stockQuantity(request.getStockQuantity() != null ? request.getStockQuantity() : 0)
                .isActive(request.getIsActive() != null ? request.getIsActive() : true)
                .rating(0.0).reviewCount(0).build();
        product = productRepository.save(product);
        log.info("Product created successfully with id: {}", product.getId());
        return convertToDetailDTO(product);
    }

    @Override
    public ProductDetailDTO updateProduct(Long id, UpdateProductRequest request) {
        log.info("Updating product with id: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        if (product.getDeletedAt() != null) {
            throw new BadRequestException("Cannot update deleted product");
        }
        if (request.getName() != null) product.setName(request.getName());
        if (request.getSlug() != null) product.setSlug(request.getSlug());
        if (request.getDescription() != null) product.setDescription(request.getDescription());
        if (request.getPrice() != null) product.setPrice(request.getPrice());
        if (request.getOriginalPrice() != null) product.setOriginalPrice(request.getOriginalPrice());
        if (request.getBrand() != null) product.setBrand(request.getBrand());
        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + request.getCategoryId()));
            product.setCategory(category);
        }
        if (request.getStockQuantity() != null) product.setStockQuantity(request.getStockQuantity());
        if (request.getIsActive() != null) product.setIsActive(request.getIsActive());
        product = productRepository.save(product);
        log.info("Product updated successfully");
        return convertToDetailDTO(product);
    }

    @Override
    public void deleteProduct(Long id) {
        log.info("Deleting product with id: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        if (product.getDeletedAt() != null) {
            throw new BadRequestException("Product already deleted");
        }
        product.softDelete();
        productRepository.save(product);
        log.info("Product deleted successfully");
    }

    private ProductListDTO convertToListDTO(Product product) {
        String primaryImageUrl = null;
        ProductImage primaryImage = productImageRepository.findPrimaryImageByProductId(product.getId()).orElse(null);
        if (primaryImage != null) {
            primaryImageUrl = primaryImage.getImageUrl();
        }
        return ProductListDTO.builder()
                .id(product.getId()).name(product.getName()).slug(product.getSlug())
                .price(product.getPrice())
                .formattedPrice(FormatUtils.formatPrice(product.getPrice()))
                .originalPrice(product.getOriginalPrice())
                .formattedOriginalPrice(product.getOriginalPrice() != null ? FormatUtils.formatPrice(product.getOriginalPrice()) : null)
                .brand(product.getBrand())
                .categoryId(product.getCategory().getId()).categoryName(product.getCategory().getName())
                .stockQuantity(product.getStockQuantity()).rating(product.getRating()).reviewCount(product.getReviewCount())
                .isActive(product.getIsActive()).primaryImageUrl(primaryImageUrl).createdAt(product.getCreatedAt()).build();
    }

    private ProductDetailDTO convertToDetailDTO(Product product) {
        List<ProductImage> images = productImageRepository.findByProductId(product.getId());
        List<ProductImageDTO> imageDTOs = images.stream()
                .map(img -> ProductImageDTO.builder()
                        .id(img.getId()).imageUrl(img.getImageUrl()).altText(img.getAltText())
                        .isPrimary(img.getIsPrimary()).displayOrder(img.getDisplayOrder()).build())
                .collect(Collectors.toList());
        return ProductDetailDTO.builder()
                .id(product.getId()).name(product.getName()).slug(product.getSlug()).description(product.getDescription())
                .price(product.getPrice())
                .formattedPrice(FormatUtils.formatPrice(product.getPrice()))
                .originalPrice(product.getOriginalPrice())
                .formattedOriginalPrice(product.getOriginalPrice() != null ? FormatUtils.formatPrice(product.getOriginalPrice()) : null)
                .brand(product.getBrand())
                .categoryId(product.getCategory().getId()).categoryName(product.getCategory().getName())
                .stockQuantity(product.getStockQuantity()).rating(product.getRating()).reviewCount(product.getReviewCount())
                .isActive(product.getIsActive()).images(imageDTOs)
                .createdAt(product.getCreatedAt()).updatedAt(product.getUpdatedAt()).build();
    }
}
