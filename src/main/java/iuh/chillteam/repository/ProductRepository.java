package iuh.chillteam.repository;

import iuh.chillteam.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Product Repository
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    /**
     * Find product by slug
     */
    @Query("SELECT p FROM Product p WHERE p.slug = :slug AND p.deletedAt IS NULL")
    Optional<Product> findBySlug(String slug);

    /**
     * Find products by category
     */
    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId AND p.deletedAt IS NULL AND p.isActive = true")
    List<Product> findByCategoryId(Long categoryId);

    /**
     * Find products by brand
     */
    @Query("SELECT p FROM Product p WHERE LOWER(p.brand) = LOWER(:brand) AND p.deletedAt IS NULL AND p.isActive = true")
    List<Product> findByBrand(String brand);

    /**
     * Check if product exists by name (for duplicate check)
     */
    boolean existsByNameAndDeletedAtIsNull(String name);

    /**
     * Check if product exists by slug (for duplicate check)
     */
    boolean existsBySlugAndDeletedAtIsNull(String slug);
}
