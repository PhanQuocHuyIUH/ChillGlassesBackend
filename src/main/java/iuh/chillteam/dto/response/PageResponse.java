package iuh.chillteam.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Pagination response wrapper
 *
 * Example response:
 * {
 *   "content": [...],
 *   "pageNumber": 0,
 *   "pageSize": 20,
 *   "totalElements": 100,
 *   "totalPages": 5,
 *   "last": false,
 *   "first": true
 * }
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageResponse<T> {

    /**
     * List of items trong page hiện tại
     */
    private List<T> content;

    /**
     * Page number (0-indexed)
     */
    private int pageNumber;

    /**
     * Page size
     */
    private int pageSize;

    /**
     * Total số elements
     */
    private long totalElements;

    /**
     * Total số pages
     */
    private int totalPages;

    /**
     * Có phải page cuối không
     */
    private boolean last;

    /**
     * Có phải page đầu không
     */
    private boolean first;

    /**
     * Có content không
     */
    private boolean empty;

    /**
     * Convert từ Spring Data Page
     */
    public static <T> PageResponse<T> of(Page<T> page) {
        return PageResponse.<T>builder()
                .content(page.getContent())
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .first(page.isFirst())
                .empty(page.isEmpty())
                .build();
    }

    /**
     * Convert từ Spring Data Page với mapper
     */
    public static <T, U> PageResponse<U> of(Page<T> page, List<U> mappedContent) {
        return PageResponse.<U>builder()
                .content(mappedContent)
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .first(page.isFirst())
                .empty(page.isEmpty())
                .build();
    }
}