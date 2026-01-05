package afsj.prod_metrics.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.YearMonth;

public record ProductionCreateDTO(
        @NotNull
        Long employeeId,

        @NotNull
        Long productId,

        @NotNull
        @Positive
        Double quantity,

        @NotNull
        @Positive
        BigDecimal unitPrice,

        @NotNull
        YearMonth productionPeriod
) {
}
