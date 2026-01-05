package afsj.prod_metrics.repositories;

import afsj.prod_metrics.entities.Product;
import afsj.prod_metrics.entities.Production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;
import java.util.Optional;

@Repository
public interface ProductionRepository extends JpaRepository<Production, Long> {
   Optional<Production> findByProductAndProductionPeriod(Product product, YearMonth productionPeriod);
}
