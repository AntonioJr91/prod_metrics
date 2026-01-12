package afsj.prod_metrics.mappers;

import afsj.prod_metrics.dtos.EmployeeResponseDTO;
import afsj.prod_metrics.entities.Employee;
import org.springframework.data.domain.Page;

public final class EmployeeMapper {

   public static EmployeeResponseDTO toDto(Employee employee) {
      return new EmployeeResponseDTO(employee.getId(), employee.getName(), employee.getCpf(),
              employee.getBaseSalary(), employee.getAdmissionDate());
   }

   public static Page<EmployeeResponseDTO> toDtoList(Page<Employee> employeePage) {
      return employeePage.map(EmployeeMapper::toDto);
   }
}
