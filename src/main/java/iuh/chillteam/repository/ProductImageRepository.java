package iuh.chillteam.repository;

import iuh.chillteam.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * ProductImage Repository
 */
@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    /**
     * Find all images by product ID
     */
    @Query("SELECT pi FROM ProductImage pi WHERE pi.product.id = :productId AND pi.deletedAt IS NULL ORDER BY pi.displayOrder ASC")
    List<ProductImage> findByProductId(Long productId);

    /**
     * Find primary image by product ID
     */
    @Query("SELECT pi FROM ProductImage pi WHERE pi.product.id = :productId AND pi.isPrimary = true AND pi.deletedAt IS NULL")
    Optional<ProductImage> findPrimaryImageByProductId(Long productId);

    /**
     * Count images by product ID
     */
    @Query("SELECT COUNT(pi) FROM ProductImage pi WHERE pi.product.id = :productId AND pi.deletedAt IS NULL")
    long countByProductId(Long productId);
}
