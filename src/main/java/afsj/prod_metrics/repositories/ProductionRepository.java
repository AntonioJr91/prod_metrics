package afsj.prod_metrics.repositories;

import afsj.prod_metrics.dtos.ProductionReportItemDTO;
import afsj.prod_metrics.entities.Product;
import afsj.prod_metrics.entities.Production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductionRepository extends JpaRepository<Production, Long> {
   Optional<Production> findByProductAndProductionPeriod(Product product, YearMonth productionPeriod);

   @Query("""
              SELECT new afsj.prod_metrics.dtos.ProductionReportItemDTO(
                 e.name,
                 pr.name,
                 pr.unitOfMeasure,
                 p.quantity,
                 p.unitPrice,
                 p.totalPrice
              )
              FROM Production p
              JOIN p.employee e
              JOIN p.product pr
              WHERE p.productionPeriod LIKE CONCAT(:year, '%')
                AND (:productId IS NULL OR pr.id = :productId)
                AND (:employeeId IS NULL OR e.id = :employeeId)
           """)
   List<ProductionReportItemDTO> findAnnualReport(
           @Param("year") Integer year,
           @Param("productId") Long productId,
           @Param("employeeId") Long employeeId
   );

   @Query("""
              SELECT new afsj.prod_metrics.dtos.ProductionReportItemDTO(
                 e.name,
                 pr.name,
                 pr.unitOfMeasure,
                 p.quantity,
                 p.unitPrice,
                 p.totalPrice
              )
              FROM Production p
              JOIN p.employee e
              JOIN p.product pr
              WHERE p.productionPeriod = :period
                AND (:productId IS NULL OR pr.id = :productId)
                AND (:employeeId IS NULL OR e.id = :employeeId)
           """)
   List<ProductionReportItemDTO> findMonthlyReport(
           @Param("period") YearMonth period,
           @Param("productId") Long productId,
           @Param("employeeId") Long employeeId
   );
}
