package iuh.chillteam.entity;

import iuh.chillteam.utils.SlugUtils;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * Category Entity - Danh mục sản phẩm
 * Ví dụ: Kính mát, Gọng kính, Kính áp tròng
 */
@Entity
@Table(name = "category", indexes = {
        @Index(name = "idx_slug", columnList = "slug"),
        @Index(name = "idx_is_active", columnList = "is_active")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category extends BaseEntity {

    /**
     * Tên danh mục
     * Ví dụ: "Kính mát", "Gọng kính"
     */
    @NotBlank(message = "Category name is required")
    @Size(max = 255, message = "Category name must not exceed 255 characters")
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Slug - URL friendly name
     * Ví dụ: "kinh-mat", "gong-kinh"
     */
    @NotBlank(message = "Category slug is required")
    @Size(max = 255, message = "Category slug must not exceed 255 characters")
    @Column(name = "slug", nullable = false, unique = true)
    private String slug;

    /**
     * Mô tả danh mục
     */
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    /**
     * Trạng thái active/inactive
     * true = đang hiển thị, false = ẩn
     */
    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;

    /**
     * Thứ tự hiển thị
     * Số càng nhỏ càng ưu tiên hiển thị trước
     */
    @Column(name = "display_order")
    @Builder.Default
    private Integer displayOrder = 0;

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
        return "Category{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                ", isActive=" + isActive +
                ", displayOrder=" + displayOrder +
                '}';
    }
}