package afsj.prod_metrics.dtos;

import afsj.prod_metrics.enums.UnitOfMeasure;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProductCreateDTO(
        @NotBlank
        @Size(min = 3, max = 5)
        String name,

        @NotNull
        UnitOfMeasure unitOfMeasure) {
}
