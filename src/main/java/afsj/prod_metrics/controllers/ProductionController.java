package afsj.prod_metrics.controllers;

import afsj.prod_metrics.dtos.ProductionCreateDTO;
import afsj.prod_metrics.dtos.ProductionFilterDTO;
import afsj.prod_metrics.dtos.ProductionReportResponseDTO;
import afsj.prod_metrics.dtos.ProductionResponseDTO;
import afsj.prod_metrics.services.ProductionService;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productions")
public class ProductionController {

   private final ProductionService service;

   public ProductionController(ProductionService service) {
      this.service = service;
   }

   @GetMapping("/reports")
   public ResponseEntity<ProductionReportResponseDTO> findByReport(ProductionFilterDTO dto) {
      return ResponseEntity.ok().body(service.findByReport(dto));
   }

   @GetMapping
   public ResponseEntity<Page<ProductionResponseDTO>> findAll(Pageable pageable) {
      return ResponseEntity.ok().body(service.findAll(pageable));
   }

   @GetMapping("/{id}")
   public ResponseEntity<ProductionResponseDTO> findById(@PathVariable Long id) {
      return ResponseEntity.ok().body(service.findById(id));
   }

   @PostMapping
   public ResponseEntity<ProductionResponseDTO> create(@RequestBody ProductionCreateDTO dto) {
      var production = service.create(dto);
      return ResponseEntity.status(HttpStatus.CREATED).body(production);
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> delete(@PathVariable Long id) {
      service.delete(id);
      return ResponseEntity.noContent().build();
   }
}
