package afsj.prod_metrics.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EmployeeResponseDTO(
        String name,
        String cpf,
        BigDecimal baseSalary,
        LocalDate admissionDate
) {
}
