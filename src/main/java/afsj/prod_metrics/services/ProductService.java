package afsj.prod_metrics.services;

import afsj.prod_metrics.dtos.ProductCreateDTO;
import afsj.prod_metrics.dtos.ProductResponseDTO;
import afsj.prod_metrics.entities.Product;
import afsj.prod_metrics.mappers.ProductMapper;
import afsj.prod_metrics.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ProductService {

   private final ProductRepository repository;

   public ProductService(ProductRepository repository) {
      this.repository = repository;
   }

   @Transactional(readOnly = true)
   public Page<ProductResponseDTO> findAll(Pageable pageable) {
      return ProductMapper.toDtoList(repository.findAll(pageable));
   }

   @Transactional(readOnly = true)
   public ProductResponseDTO findById(UUID id) {
      var product = repository.findById(id).orElseThrow(IllegalAccessError::new);
      return ProductMapper.toDto(product);
   }

   @Transactional
   public ProductResponseDTO create(ProductCreateDTO dto) {
      repository.findByName(dto.name()).ifPresent(p -> {
         throw new IllegalArgumentException("Product with this name already exists.");
      });

      Product productSaved = repository.save(Product.create(dto.name(), dto.unitOfMeasure()));

      return ProductMapper.toDto(productSaved);
   }

   @Transactional
   public void delete(UUID id) {
      if (!repository.existsById(id)) {
         throw new IllegalArgumentException("Product not found.");
      }
      repository.deleteById(id);
   }
}
