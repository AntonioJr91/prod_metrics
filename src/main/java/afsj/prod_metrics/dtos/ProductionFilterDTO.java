package afsj.prod_metrics.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ProductionFilterDTO(
        @NotNull
        @Min(2000)
        Integer year,

        @Min(1)
        @Max(12)
        Integer month,

        Long productId,
        Long employeeId
) {
}
