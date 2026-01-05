package afsj.prod_metrics.dtos;

import afsj.prod_metrics.enums.UnitOfMeasure;

import java.math.BigDecimal;
import java.time.YearMonth;

public record ProductionResponseDTO(
        String employeeName,
        String productName,
        UnitOfMeasure productUnitOfMeasure,
        Double quantity,
        BigDecimal unitPrice,
        YearMonth productionPeriod,
        BigDecimal totalPrice
) {
}
