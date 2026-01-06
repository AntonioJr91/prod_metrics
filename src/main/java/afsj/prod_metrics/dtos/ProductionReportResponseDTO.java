package afsj.prod_metrics.dtos;

import org.springframework.data.domain.Page;

import java.math.BigDecimal;

public record ProductionReportResponseDTO(
        Integer year,
        Integer month,
        BigDecimal total,
        Page<ProductionReportItemDTO> items
) {
}
