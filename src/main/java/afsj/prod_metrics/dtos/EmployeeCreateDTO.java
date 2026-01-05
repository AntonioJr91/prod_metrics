package afsj.prod_metrics.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EmployeeCreateDTO(
        @NotBlank
        @Size(min = 3, max = 50)
        String name,

        @NotBlank
        @Size(min = 11, max = 11)
        String cpf,

        @NotNull
        @Positive
        BigDecimal baseSalary,

        @NotNull
        LocalDate admissionDate
) {
}
