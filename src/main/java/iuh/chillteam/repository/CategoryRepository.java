package iuh.chillteam.repository;

import iuh.chillteam.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Category Repository
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * Tìm category theo slug
     * @param slug Category slug
     * @return Optional Category
     */
    Optional<Category> findBySlug(String slug);

    /**
     * Tìm tất cả categories theo trạng thái active
     * @param isActive true = active, false = inactive
     * @return List categories
     */
    List<Category> findByIsActive(Boolean isActive);

    /**
     * Tìm tất cả categories active, sắp xếp theo displayOrder
     * @return List categories
     */
    @Query("SELECT c FROM Category c WHERE c.isActive = true ORDER BY c.displayOrder ASC, c.name ASC")
    List<Category> findAllActiveOrderedByDisplayOrder();

    /**
     * Kiểm tra slug đã tồn tại chưa (dùng khi create/update)
     * @param slug Category slug
     * @return true nếu exists
     */
    boolean existsBySlug(String slug);

    /**
     * Kiểm tra slug đã tồn tại chưa, ngoại trừ id hiện tại (dùng khi update)
     * @param slug Category slug
     * @param id Current category id
     * @return true nếu exists
     */
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Category c WHERE c.slug = :slug AND c.id != :id")
    boolean existsBySlugAndIdNot(String slug, Long id);

    /**
     * Đếm số lượng categories active
     * @return Count
     */
    @Query("SELECT COUNT(c) FROM Category c WHERE c.isActive = true")
    long countActiveCategories();
}