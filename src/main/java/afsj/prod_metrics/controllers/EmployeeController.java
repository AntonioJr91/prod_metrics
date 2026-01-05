package afsj.prod_metrics.controllers;

import afsj.prod_metrics.dtos.EmployeeCreateDTO;
import afsj.prod_metrics.dtos.EmployeeResponseDTO;
import afsj.prod_metrics.services.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

   private final EmployeeService service;

   public EmployeeController(EmployeeService service) {
      this.service = service;
   }

   @GetMapping
   public ResponseEntity<Page<EmployeeResponseDTO>> findAll(Pageable pageable) {
      return ResponseEntity.ok().body(service.findAll(pageable));
   }

   @GetMapping("/{id}")
   public ResponseEntity<EmployeeResponseDTO> findById(@PathVariable Long id) {
      return ResponseEntity.ok().body(service.findById(id));
   }

   @PostMapping
   public ResponseEntity<EmployeeResponseDTO> create(@RequestBody EmployeeCreateDTO dto) {
      var employee = service.create(dto);
      return ResponseEntity.status(HttpStatus.CREATED).body(employee);
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> delete(@PathVariable Long id) {
      service.delete(id);
      return ResponseEntity.noContent().build();
   }
}
