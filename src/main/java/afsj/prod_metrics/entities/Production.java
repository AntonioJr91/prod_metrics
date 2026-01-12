package afsj.prod_metrics.entities;

import afsj.prod_metrics.exceptions.DomainException;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.YearMonth;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(
        columnNames = {"employee_id", "product_id", "production_period"}
)
)
public class Production {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

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

   @Column(name = "production_period", columnDefinition = "VARCHAR(7)", length = 7, nullable = false, updatable = false)
   private YearMonth productionPeriod;

   @Column(nullable = false, updatable = false)
   private BigDecimal totalPrice;

   protected Production() {
   }

   public Production(Employee employee, Product product, Double quantity, BigDecimal unitPrice, YearMonth productionPeriod) {
      validateEmployee(employee);
      validateProduct(product);
      validateQuantity(quantity);
      validateUnitPrice(unitPrice);
      validateProductionPeriod(productionPeriod);

      this.employee = employee;
      this.product = product;
      this.quantity = quantity;
      this.unitPrice = unitPrice;
      this.productionPeriod = productionPeriod;
      this.totalPrice = setTotalPrice(quantity, unitPrice);
   }

   public static Production create(Employee employee, Product product, Double quantity, BigDecimal unitPrice, YearMonth productionPeriod) {
      return new Production(employee, product, quantity, unitPrice, productionPeriod);
   }

   public Long getId() {
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

   public YearMonth getProductionPeriod() {
      return productionPeriod;
   }

   public BigDecimal getTotalPrice() {
      return totalPrice;
   }

   private BigDecimal setTotalPrice(Double quantity, BigDecimal unitPrice) {
      validateQuantity(quantity);
      validateUnitPrice(unitPrice);
      return unitPrice.multiply(BigDecimal.valueOf(quantity))
              .setScale(2, RoundingMode.UNNECESSARY);
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

   private void validateEmployee(Employee employee) {
      DomainException.when(employee == null, "Employee is required.");
   }

   private void validateProduct(Product product) {
      DomainException.when(product == null, "Product is required.");
   }

   private void validateQuantity(Double quantity) {
      DomainException.when(quantity == null, "Quantity is required.");
      DomainException.when(quantity <= 0.0, "Quantity must be greater than 0.");
   }

   private void validateUnitPrice(BigDecimal unitPrice) {
      DomainException.when(unitPrice == null, "UnitPrice is required.");
      DomainException.when(unitPrice.compareTo(BigDecimal.ZERO) <= 0, "UnitPrice must be greater than 0.");
   }

   private void validateProductionPeriod(YearMonth productionPeriod) {
      DomainException.when(productionPeriod == null, "Production period is required.");
   }
}
