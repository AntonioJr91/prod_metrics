package afsj.prod_metrics.dtos;

import afsj.prod_metrics.enums.UnitOfMeasure;

import java.math.BigDecimal;

public record ProductionReportItemDTO(
        String employeeName,
        String productName,
        UnitOfMeasure unitOfMeasure,
        Double quantity,
        BigDecimal unitPrice,
        BigDecimal totalPrice
) {
}
