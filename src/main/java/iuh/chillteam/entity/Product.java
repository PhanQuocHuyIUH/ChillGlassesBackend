package iuh.chillteam.entity;

import iuh.chillteam.utils.SlugUtils;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * Product Entity - Sản phẩm mắt kính
 */
@Entity
@Table(name = "product", indexes = {
        @Index(name = "idx_product_slug", columnList = "slug"),
        @Index(name = "idx_product_brand", columnList = "brand"),
        @Index(name = "idx_product_category", columnList = "category_id"),
        @Index(name = "idx_product_active", columnList = "is_active")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product extends BaseEntity {

    @NotBlank(message = "Product name is required")
    @Size(max = 255, message = "Product name must not exceed 255 characters")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "Product slug is required")
    @Size(max = 255, message = "Product slug must not exceed 255 characters")
    @Column(name = "slug", nullable = false, unique = true)
    private String slug;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "original_price")
    private Double originalPrice;

    @NotBlank(message = "Brand is required")
    @Size(max = 100, message = "Brand must not exceed 100 characters")
    @Column(name = "brand", nullable = false)
    private String brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false, foreignKey = @ForeignKey(name = "fk_product_category"))
    private Category category;

    @Min(value = 0, message = "Stock quantity must be greater than or equal to 0")
    @Column(name = "stock_quantity", nullable = false)
    @Builder.Default
    private Integer stockQuantity = 0;

    @Column(name = "rating")
    @Builder.Default
    private Double rating = 0.0;

    @Column(name = "review_count")
    @Builder.Default
    private Integer reviewCount = 0;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;

    /**
     * Pre-persist hook - Tự động tạo slug nếu chưa có
     */
    @PrePersist
    @PreUpdate
    public void generateSlug() {
        if (this.slug == null || this.slug.trim().isEmpty()) {
            this.slug = SlugUtils.toSlug(this.name);
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                ", isActive=" + isActive +
                '}';
    }
}