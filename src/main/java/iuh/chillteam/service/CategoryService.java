package iuh.chillteam.service;

import iuh.chillteam.dto.category.CategoryDTO;
import iuh.chillteam.dto.category.CategoryRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();

    List<CategoryDTO> getAllActiveCategories();

    CategoryDTO getCategoryById(Long id);

    CategoryDTO getCategoryBySlug(String slug);

    @Transactional
    CategoryDTO createCategory(CategoryRequest request);

    @Transactional
    CategoryDTO updateCategory(Long id, CategoryRequest request);

    @Transactional
    void deleteCategory(Long id);

    @Transactional
    void hardDeleteCategory(Long id);

    @Transactional
    CategoryDTO activateCategory(Long id);

    @Transactional
    CategoryDTO deactivateCategory(Long id);
}
