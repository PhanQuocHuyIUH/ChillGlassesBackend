package iuh.chillteam.repository;

import iuh.chillteam.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * author: QUOC HUY
 * date: 07/11/2025
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Find product by name
     */
    Optional<Product> findByName(String name);

    /**
     * Find all active products (not soft deleted)
     */
    @Query("SELECT p FROM Product p WHERE p.deletedAt IS NULL")
    List<Product> findAllActive();

    /**
     * Find all active products with pagination
     */
    @Query("SELECT p FROM Product p WHERE p.deletedAt IS NULL")
    Page<Product> findAllActive(Pageable pageable);

    /**
     * Find products by price range
     */
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice AND p.deletedAt IS NULL")
    List<Product> findByPriceRange(Double minPrice, Double maxPrice);
}
