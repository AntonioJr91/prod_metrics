package afsj.prod_metrics.repositories;

import afsj.prod_metrics.entities.Production;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductionRepository extends JpaRepository<Production, UUID> {
}
