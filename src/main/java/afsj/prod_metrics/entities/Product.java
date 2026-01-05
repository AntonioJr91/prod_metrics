package afsj.prod_metrics.entities;

import afsj.prod_metrics.enums.UnitOfMeasure;
import afsj.prod_metrics.utils.Validations;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Product {

   @Id
   @GeneratedValue(strategy = GenerationType.UUID)
   private UUID id;

   @Column(nullable = false, updatable = false)
   private String name;

   @Enumerated(EnumType.STRING)
   @Column(nullable = false)
   private UnitOfMeasure unitOfMeasure;

   protected Product() {
   }

   public Product(String name, UnitOfMeasure unitOfMeasure) {
      Validations.validateName(name);
      Validations.validateEnum(unitOfMeasure);
      this.name = name;
      this.unitOfMeasure = unitOfMeasure;
   }

   public UUID getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public UnitOfMeasure getUnitOfMeasure() {
      return unitOfMeasure;
   }

   @Override
   public boolean equals(Object o) {
      if (!(o instanceof Product product)) return false;
      return id != null && id.equals(product.id);
   }

   @Override
   public int hashCode() {
      return getClass().hashCode();
   }
}
