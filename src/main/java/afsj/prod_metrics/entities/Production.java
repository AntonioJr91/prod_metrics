package afsj.prod_metrics.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Production {

   @Id
   @GeneratedValue(strategy = GenerationType.UUID)
   private UUID id;

   @ManyToOne(fetch = FetchType.LAZY, optional = false)
   @JoinColumn(name = "employee_id", nullable = false)
   private Employee employee;

   @ManyToOne(fetch = FetchType.LAZY, optional = false)
   @JoinColumn(name = "product_id", nullable = false)
   private Product product;

   @Column(nullable = false, updatable = false)
   private Double quantity;

   @Column(nullable = false, updatable = false)
   private BigDecimal unitPrice;

   @Column(nullable = false, updatable = false)
   private LocalDate productionMonth;

   protected Production() {
   }

   public Production(Employee employee, Product product, Double quantity, BigDecimal unitPrice, LocalDate productionMonth) {
      this.employee = employee;
      this.product = product;
      this.quantity = quantity;
      this.unitPrice = unitPrice;
      this.productionMonth = productionMonth;
   }

   public UUID getId() {
      return id;
   }

   public Employee getEmployee() {
      return employee;
   }

   public Product getProduct() {
      return product;
   }

   public Double getQuantity() {
      return quantity;
   }

   public BigDecimal getUnitPrice() {
      return unitPrice;
   }

   public LocalDate getProductionMonth() {
      return productionMonth;
   }

   @Override
   public boolean equals(Object o) {
      if (!(o instanceof Production production)) return false;
      return id != null && id.equals(production.id);
   }

   @Override
   public int hashCode() {
      return getClass().hashCode();
   }
}
