package afsj.prod_metrics.controllers;

import afsj.prod_metrics.dtos.ProductCreateDTO;
import afsj.prod_metrics.dtos.ProductResponseDTO;
import afsj.prod_metrics.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

   private final ProductService service;

   public ProductController(ProductService service) {
      this.service = service;
   }

   @GetMapping
   public ResponseEntity<Page<ProductResponseDTO>> findAll(Pageable pageable) {
      return ResponseEntity.ok().body(service.findAll(pageable));
   }

   @GetMapping("/{id}")
   public ResponseEntity<ProductResponseDTO> findById(@PathVariable Long id) {
      return ResponseEntity.ok().body(service.findById(id));
   }

   @PostMapping
   public ResponseEntity<ProductResponseDTO> create(@RequestBody @Valid ProductCreateDTO dto) {
      var product = service.create(dto);
      return ResponseEntity.status(HttpStatus.CREATED).body(product);
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> delete(@PathVariable Long id) {
      service.delete(id);
      return ResponseEntity.noContent().build();
   }
}
