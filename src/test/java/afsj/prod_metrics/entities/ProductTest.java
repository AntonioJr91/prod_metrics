package afsj.prod_metrics.entities;


import afsj.prod_metrics.enums.UnitOfMeasure;
import afsj.prod_metrics.exceptions.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductTest {

   private UnitOfMeasure unit;

   @BeforeEach
   void setUp() {
      unit = UnitOfMeasure.KG;
   }

   @Test
   void shouldCreateProduct() {
      String name = "Cacau";

      Product product = new Product(name, unit);

      Assertions.assertNotNull(product);
      Assertions.assertEquals(name, product.getName());
      Assertions.assertEquals(unit, product.getUnitOfMeasure());
   }

   @Test
   void shouldThrowExceptionWhenNameIsNull() {
      Assertions.assertThrows(DomainException.class, () ->
              new Product(null, unit));
   }

   @Test
   void shouldThrowExceptionWhenNameIsBlank() {
      Assertions.assertThrows(DomainException.class, () ->
              new Product("   ", unit));
   }

   @Test
   void shouldThrowExceptionWhenNameIsLesserThan3Characters() {
      Assertions.assertThrows(DomainException.class, () ->
              new Product("a", unit));
   }

   @Test
   void shouldThrowExceptionWhenNameIsGreaterThan50Characters() {
      Assertions.assertThrows(DomainException.class, () ->
              new Product("a".repeat(51), unit));
   }

   @Test
   void shouldThrowExceptionWhenUnitOfMeasureIsNull() {
      Assertions.assertThrows(DomainException.class, () ->
              new Product("Cacau", null));
   }
}
