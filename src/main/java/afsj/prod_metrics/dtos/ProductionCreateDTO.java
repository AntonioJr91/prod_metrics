package afsj.prod_metrics.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.UUID;

public record ProductionCreateDTO(
        @NotNull
        UUID employeeId,

        @NotNull
        UUID productId,

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
