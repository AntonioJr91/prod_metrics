package afsj.prod_metrics.services;

import afsj.prod_metrics.dtos.EmployeeCreateDTO;
import afsj.prod_metrics.dtos.EmployeeResponseDTO;
import afsj.prod_metrics.entities.Employee;
import afsj.prod_metrics.exceptions.DuplicateResourceException;
import afsj.prod_metrics.exceptions.ResourceNotFoundException;
import afsj.prod_metrics.mappers.EmployeeMapper;
import afsj.prod_metrics.repositories.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class EmployeeService {

   private final EmployeeRepository repository;

   public EmployeeService(EmployeeRepository repository) {
      this.repository = repository;
   }

   @Transactional(readOnly = true)
   public Page<EmployeeResponseDTO> findAll(Pageable pageable) {
      return EmployeeMapper.toDtoList(repository.findAll(pageable));
   }

   @Transactional(readOnly = true)
   public EmployeeResponseDTO findById(UUID id) {
      var employee = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found."));
      return EmployeeMapper.toDto(employee);
   }

   @Transactional
   public EmployeeResponseDTO create(EmployeeCreateDTO dto) {
      repository.findByCpf(dto.cpf()).ifPresent(e -> {
         throw new DuplicateResourceException("This CPF is already in use. Please try another.");
      });

      var employeeSaved = repository.save(
              Employee.create(dto.name(), dto.cpf(), dto.baseSalary(), dto.admissionDate())
      );

      return EmployeeMapper.toDto(employeeSaved);
   }

   @Transactional
   public void delete(UUID id) {
      if (!repository.existsById(id)) {
         throw new ResourceNotFoundException("Employee not found.");
      }
      repository.deleteById(id);
   }
}
