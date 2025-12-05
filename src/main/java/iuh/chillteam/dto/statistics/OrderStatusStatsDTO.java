package iuh.chillteam.dto.statistics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for Order Statistics by Status
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderStatusStatsDTO {

    private String status;
    private Long count;
    private Double percentage;
}
