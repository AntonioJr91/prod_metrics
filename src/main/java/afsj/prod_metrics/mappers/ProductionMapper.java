package afsj.prod_metrics.mappers;

import afsj.prod_metrics.dtos.ProductionReportItemDTO;
import afsj.prod_metrics.dtos.ProductionReportResponseDTO;
import afsj.prod_metrics.dtos.ProductionResponseDTO;
import afsj.prod_metrics.entities.Production;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

public final class ProductionMapper {

   public static ProductionResponseDTO toDto(Production production) {
      return new ProductionResponseDTO(
              production.getEmployee().getName(),
              production.getProduct().getName(),
              production.getProduct().getUnitOfMeasure(),
              production.getQuantity(),
              production.getUnitPrice(),
              production.getProductionPeriod(),
              production.getTotalPrice()
      );
   }

   public static Page<ProductionResponseDTO> toDtoList(Page<Production> productionPage) {
      return productionPage.map(ProductionMapper::toDto);
   }

   public static ProductionReportResponseDTO toReportResponseDto(Integer year, Integer month, BigDecimal total,
                                                                 Page<ProductionReportItemDTO> items) {
      return new ProductionReportResponseDTO(year, month, total, items);
   }
}
