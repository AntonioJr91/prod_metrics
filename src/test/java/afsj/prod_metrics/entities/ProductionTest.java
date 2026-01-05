package afsj.prod_metrics.entities;

import afsj.prod_metrics.enums.UnitOfMeasure;
import afsj.prod_metrics.exceptions.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;

public class ProductionTest {
   Employee employee = new Employee("John", "12345678910", BigDecimal.valueOf(1500), LocalDate.of(2024, 1, 1));
   Product product = new Product("Cacau", UnitOfMeasure.KG);

   Double quantity = 10.0;
   BigDecimal unitPrice = BigDecimal.valueOf(500.0);
   YearMonth productionPeriod = YearMonth.of(2024, 1);

   @Test
   void shouldCreateProduction() {
      Production production = new Production(employee, product, quantity, unitPrice, productionPeriod);

      Assertions.assertEquals(employee.getName(), production.getEmployee().getName());
      Assertions.assertEquals(product.getName(), production.getProduct().getName());
      Assertions.assertEquals(quantity, production.getQuantity());
      Assertions.assertEquals(0, unitPrice.compareTo(production.getUnitPrice()));
      Assertions.assertEquals(productionPeriod, production.getProductionPeriod());
   }

   @Test
   void shouldThrowExceptionWhenEmployeeIsNull() {
      Assertions.assertThrows(DomainException.class,
              () -> new Production(null, product, quantity, unitPrice, productionPeriod));
   }

   @Test
   void shouldThrowExceptionWhenProductIsNull() {
      Assertions.assertThrows(DomainException.class,
              () -> new Production(employee, null, quantity, unitPrice, productionPeriod));
   }

   @Test
   void shouldThrowExceptionWhenQuantityIsZeroOrNegative() {
      Assertions.assertThrows(DomainException.class,
              () -> new Production(employee, product, 0.0, unitPrice, productionPeriod));

      Assertions.assertThrows(DomainException.class,
              () -> new Production(employee, product, -10.0, unitPrice, productionPeriod));
   }

   @Test
   void shouldThrowExceptionWhenUnitPriceIsNull() {
      Assertions.assertThrows(DomainException.class,
              () -> new Production(employee, product, quantity, null, productionPeriod));
   }

   @Test
   void shouldThrowExceptionWhenUnitPriceIsZeroOrNegative() {
      Assertions.assertThrows(DomainException.class,
              () -> new Production(employee, product, quantity, BigDecimal.ZERO, productionPeriod));

      Assertions.assertThrows(DomainException.class,
              () -> new Production(employee, product, quantity, BigDecimal.valueOf(-100), productionPeriod));
   }

   @Test
   void shouldThrowExceptionWhenProductionPeriodIsNull(){
      Assertions.assertThrows(DomainException.class,
              () -> new Production(employee, product, quantity, unitPrice, null));
   }

}
