package afsj.prod_metrics.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record ProductionReportResponseDTO(
        Integer year,
        Integer month,
        BigDecimal total,
        List<ProductionReportItemDTO> items
) {
}
