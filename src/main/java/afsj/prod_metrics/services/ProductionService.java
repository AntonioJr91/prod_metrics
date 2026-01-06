package afsj.prod_metrics.services;

import afsj.prod_metrics.dtos.*;
import afsj.prod_metrics.entities.Employee;
import afsj.prod_metrics.entities.Product;
import afsj.prod_metrics.entities.Production;
import afsj.prod_metrics.exceptions.DuplicateResourceException;
import afsj.prod_metrics.exceptions.ResourceNotFoundException;
import afsj.prod_metrics.mappers.ProductionMapper;
import afsj.prod_metrics.repositories.EmployeeRepository;
import afsj.prod_metrics.repositories.ProductRepository;
import afsj.prod_metrics.repositories.ProductionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;

@Service
public class ProductionService {

   private final ProductionRepository repository;
   private final EmployeeRepository employeeRepository;
   private final ProductRepository productRepository;

   public ProductionService(ProductionRepository repository, EmployeeRepository employeeRepository, ProductRepository productRepository) {
      this.repository = repository;
      this.employeeRepository = employeeRepository;
      this.productRepository = productRepository;
   }

   @Transactional(readOnly = true)
   public ProductionReportResponseDTO findByReport(ProductionFilterDTO dto) {
      List<ProductionReportItemDTO> items;

      if (dto.month() == null) {
         items = repository.findAnnualReport(dto.year(), dto.productId(), dto.employeeId());
      } else {
         YearMonth period = YearMonth.of(dto.year(), dto.month());

         items = repository.findMonthlyReport(period, dto.productId(), dto.employeeId());
      }

      BigDecimal total = items.stream().map(ProductionReportItemDTO::totalPrice)
              .reduce(BigDecimal.ZERO, BigDecimal::add);

      return ProductionMapper.toReportResponseDto(dto.year(), dto.month(), total, items);
   }

   @Transactional(readOnly = true)
   public Page<ProductionResponseDTO> findAll(Pageable pageable) {
      return ProductionMapper.toDtoList(repository.findAll(pageable));
   }

   @Transactional(readOnly = true)
   public ProductionResponseDTO findById(Long id) {
      var production = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Production not found."));
      return ProductionMapper.toDto(production);
   }

   @Transactional
   public ProductionResponseDTO create(ProductionCreateDTO dto) {
      Employee employee = employeeRepository.findById(dto.employeeId())
              .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

      Product product = productRepository.findById(dto.productId())
              .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

      repository.findByProductAndProductionPeriod(product, dto.productionPeriod())
              .ifPresent(p -> {
                 throw new DuplicateResourceException("Production already exists for this product and period.");
              });

      var productionSaved = repository.save(
              Production.create(employee, product, dto.quantity(), dto.unitPrice(), dto.productionPeriod())
      );

      return ProductionMapper.toDto(productionSaved);
   }

   @Transactional
   public void delete(Long id) {
      if (!repository.existsById(id)) {
         throw new ResourceNotFoundException("Production not found.");
      }
      repository.deleteById(id);
   }
}
