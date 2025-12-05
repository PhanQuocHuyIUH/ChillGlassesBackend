package iuh.chillteam.dto.statistics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO for Revenue Statistics by Period
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RevenueStatsDTO {

    private LocalDate date;
    private String period; // "2025-01-20", "2025-W03", "2025-01"
    private Long orderCount;
    private Double totalRevenue;
    private Double averageOrderValue;
}
