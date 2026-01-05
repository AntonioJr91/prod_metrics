package afsj.prod_metrics.mappers;

import afsj.prod_metrics.dtos.ProductResponseDTO;
import afsj.prod_metrics.entities.Product;
import org.springframework.data.domain.Page;

public final class ProductMapper {

   public static ProductResponseDTO toDto(Product product) {
      return new ProductResponseDTO(product.getId(), product.getName(), product.getUnitOfMeasure());
   }

   public static Page<ProductResponseDTO> toDtoList(Page<Product> productPage) {
      return productPage.map(ProductMapper::toDto);
   }
}
