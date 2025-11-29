package iuh.chillteam.service.impl;

import iuh.chillteam.dto.category.CategoryDTO;
import iuh.chillteam.dto.category.CategoryMapper;
import iuh.chillteam.dto.category.CategoryRequest;
import iuh.chillteam.entity.Category;
import iuh.chillteam.exception.CategoryNotFoundException;
import iuh.chillteam.exception.ConflictException;
import iuh.chillteam.repository.CategoryRepository;
import iuh.chillteam.service.CategoryService;
import iuh.chillteam.utils.SlugUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Category Service - Business logic cho Category
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     * Lấy tất cả categories
     * @return List CategoryDTO
     */
    @Override
    public List<CategoryDTO> getAllCategories() {
        log.info("Getting all categories");
        List<Category> categories = categoryRepository.findAll();
        return CategoryMapper.toDTOList(categories);
    }

    /**
     * Lấy tất cả categories active, sắp xếp theo displayOrder
     * @return List CategoryDTO
     */
    @Override
    public List<CategoryDTO> getAllActiveCategories() {
        log.info("Getting all active categories");
        List<Category> categories = categoryRepository.findAllActiveOrderedByDisplayOrder();
        return CategoryMapper.toDTOList(categories);
    }

    /**
     * Lấy category theo ID
     * @param id Category ID
     * @return CategoryDTO
     * @throws CategoryNotFoundException nếu không tìm thấy
     */
    @Override
    public CategoryDTO getCategoryById(Long id) {
        log.info("Getting category by id: {}", id);
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        return CategoryMapper.toDTO(category);
    }

    /**
     * Lấy category theo slug
     * @param slug Category slug
     * @return CategoryDTO
     * @throws CategoryNotFoundException nếu không tìm thấy
     */
    @Override
    public CategoryDTO getCategoryBySlug(String slug) {
        log.info("Getting category by slug: {}", slug);
        Category category = categoryRepository.findBySlug(slug)
                .orElseThrow(() -> new CategoryNotFoundException(slug));
        return CategoryMapper.toDTO(category);
    }

    /**
     * Tạo category mới
     * @param request CategoryRequest
     * @return CategoryDTO
     * @throws ConflictException nếu slug đã tồn tại
     */
    @Transactional
    @Override
    public CategoryDTO createCategory(CategoryRequest request) {
        log.info("Creating new category: {}", request.getName());

        // Generate slug nếu chưa có
        String slug = request.getSlug();
        if (slug == null || slug.trim().isEmpty()) {
            slug = SlugUtils.toSlug(request.getName());
            request.setSlug(slug);
        }

        // Check slug đã tồn tại chưa
        if (categoryRepository.existsBySlug(slug)) {
            throw new ConflictException("Category with slug '" + slug + "' already exists");
        }

        // Tạo entity và save
        Category category = CategoryMapper.toEntity(request);
        category = categoryRepository.save(category);

        log.info("Created category with id: {}", category.getId());
        return CategoryMapper.toDTO(category);
    }

    /**
     * Cập nhật category
     * @param id Category ID
     * @param request CategoryRequest
     * @return CategoryDTO
     * @throws CategoryNotFoundException nếu không tìm thấy
     * @throws ConflictException nếu slug đã tồn tại
     */
    @Transactional
    @Override
    public CategoryDTO updateCategory(Long id, CategoryRequest request) {
        log.info("Updating category id: {}", id);

        // Tìm category
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));

        // Check slug nếu có thay đổi
        if (request.getSlug() != null && !request.getSlug().equals(category.getSlug())) {
            if (categoryRepository.existsBySlugAndIdNot(request.getSlug(), id)) {
                throw new ConflictException("Category with slug '" + request.getSlug() + "' already exists");
            }
        }

        // Update entity
        CategoryMapper.updateEntity(category, request);

        // Nếu name thay đổi mà không có slug mới, tự động generate
        if (request.getName() != null &&
                (request.getSlug() == null || request.getSlug().trim().isEmpty())) {
            category.setSlug(SlugUtils.toSlug(request.getName()));
        }

        category = categoryRepository.save(category);

        log.info("Updated category id: {}", id);
        return CategoryMapper.toDTO(category);
    }

    /**
     * Xóa category (soft delete)
     * @param id Category ID
     * @throws CategoryNotFoundException nếu không tìm thấy
     */
    @Transactional
    @Override
    public void deleteCategory(Long id) {
        log.info("Deleting category id: {}", id);

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));

        // Soft delete
        category.softDelete();
        categoryRepository.save(category);

        log.info("Deleted category id: {}", id);
    }

    /**
     * Xóa category vĩnh viễn (hard delete)
     * @param id Category ID
     * @throws CategoryNotFoundException nếu không tìm thấy
     */
    @Transactional
    @Override
    public void hardDeleteCategory(Long id) {
        log.info("Hard deleting category id: {}", id);

        if (!categoryRepository.existsById(id)) {
            throw new CategoryNotFoundException(id);
        }

        categoryRepository.deleteById(id);

        log.info("Hard deleted category id: {}", id);
    }

    /**
     * Kích hoạt category
     * @param id Category ID
     * @return CategoryDTO
     */
    @Transactional
    @Override
    public CategoryDTO activateCategory(Long id) {
        log.info("Activating category id: {}", id);

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));

        category.setIsActive(true);
        category = categoryRepository.save(category);

        log.info("Activated category id: {}", id);
        return CategoryMapper.toDTO(category);
    }

    /**
     * Vô hiệu hóa category
     * @param id Category ID
     * @return CategoryDTO
     */
    @Transactional
    @Override
    public CategoryDTO deactivateCategory(Long id) {
        log.info("Deactivating category id: {}", id);

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));

        category.setIsActive(false);
        category = categoryRepository.save(category);

        log.info("Deactivated category id: {}", id);
        return CategoryMapper.toDTO(category);
    }
}