package iuh.chillteam.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * ProductImage Entity - Hình ảnh sản phẩm
 */
@Entity
@Table(name = "product_image", indexes = {
        @Index(name = "idx_product_image_product", columnList = "product_id"),
        @Index(name = "idx_product_image_primary", columnList = "is_primary")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductImage extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(name = "fk_product_image_product"))
    private Product product;

    @NotBlank(message = "Image URL is required")
    @Size(max = 500, message = "Image URL must not exceed 500 characters")
    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Size(max = 255, message = "Alt text must not exceed 255 characters")
    @Column(name = "alt_text")
    private String altText;

    @Column(name = "is_primary", nullable = false)
    @Builder.Default
    private Boolean isPrimary = false;

    @Min(value = 0, message = "Display order must be greater than or equal to 0")
    @Column(name = "display_order")
    @Builder.Default
    private Integer displayOrder = 0;

    @Override
    public String toString() {
        return "ProductImage{" +
                "id=" + getId() +
                ", imageUrl='" + imageUrl + '\'' +
                ", isPrimary=" + isPrimary +
                ", displayOrder=" + displayOrder +
                '}';
    }
}