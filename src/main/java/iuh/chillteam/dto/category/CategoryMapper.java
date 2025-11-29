package iuh.chillteam.dto.category;

import iuh.chillteam.entity.Category;
import java.util.List;

/**
 * Category Mapper - Manual mapping
 */
public class CategoryMapper {

    /**
     * Entity → DTO
     */
    public static CategoryDTO toDTO(Category entity) {
        if (entity == null) {
            return null;
        }

        return CategoryDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .slug(entity.getSlug())
                .description(entity.getDescription())
                .isActive(entity.getIsActive())
                .displayOrder(entity.getDisplayOrder())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    /**
     * Request → Entity (for create)
     */
    public static Category toEntity(CategoryRequest request) {
        if (request == null) {
            return null;
        }

        return Category.builder()
                .name(request.getName())
                .slug(request.getSlug())
                .description(request.getDescription())
                .isActive(request.getIsActive() != null ? request.getIsActive() : true)
                .displayOrder(request.getDisplayOrder() != null ? request.getDisplayOrder() : 0)
                .build();
    }

    /**
     * Update entity from request (for update)
     */
    public static void updateEntity(Category entity, CategoryRequest request) {
        if (entity == null || request == null) {
            return;
        }

        if (request.getName() != null) {
            entity.setName(request.getName());
        }

        if (request.getSlug() != null) {
            entity.setSlug(request.getSlug());
        }

        if (request.getDescription() != null) {
            entity.setDescription(request.getDescription());
        }

        if (request.getIsActive() != null) {
            entity.setIsActive(request.getIsActive());
        }

        if (request.getDisplayOrder() != null) {
            entity.setDisplayOrder(request.getDisplayOrder());
        }
    }

    /**
     * List Entity → List DTO
     */
    public static java.util.List<CategoryDTO> toDTOList(List<Category> entities) {
        if (entities == null) {
            return java.util.Collections.emptyList();
        }

        return entities.stream()
                .map(CategoryMapper::toDTO)
                .collect(java.util.stream.Collectors.toList());
    }
}