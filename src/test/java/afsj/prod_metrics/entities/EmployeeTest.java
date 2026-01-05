package afsj.prod_metrics.entities;

import afsj.prod_metrics.exceptions.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EmployeeTest {
   String name = "John";
   String cpf = "12345678910";
   BigDecimal baseSalary = BigDecimal.valueOf(1500.00);
   LocalDate admissionDate = LocalDate.of(2024, 1, 1);

   @Test
   void shouldCreateEmployee() {
      Employee employee = new Employee(name, cpf, baseSalary, admissionDate);

      Assertions.assertEquals(name, employee.getName());
      Assertions.assertEquals(cpf, employee.getCpf());
      Assertions.assertTrue(baseSalary.compareTo(employee.getBaseSalary()) == 0);
      Assertions.assertEquals(admissionDate, employee.getAdmissionDate());
   }

   @Test
   void shouldThrowExceptionWhenNameIsNull() {
      Assertions.assertThrows(DomainException.class,
              () -> new Employee(null, cpf, baseSalary, admissionDate));
   }

   @Test
   void shouldThrowExceptionWhenNameIsEmpty() {
      Assertions.assertThrows(DomainException.class,
              () -> new Employee("   ", cpf, baseSalary, admissionDate));
   }

   @Test
   void shouldThrowExceptionWhenNameIsLesserThan3Characters() {
      Assertions.assertThrows(DomainException.class,
              () -> new Employee("a", cpf, baseSalary, admissionDate));
   }

   @Test
   void shouldThrowExceptionWhenNameIsGreaterThan50Characters() {
      Assertions.assertThrows(DomainException.class,
              () -> new Employee("a".repeat(51), cpf, baseSalary, admissionDate));
   }

   @Test
   void shouldThrowExceptionWhenCpfIsNull() {
      Assertions.assertThrows(DomainException.class,
              () -> new Employee(name, null, baseSalary, admissionDate));
   }

   @Test
   void shouldThrowExceptionWhenCpfIsBlank() {
      Assertions.assertThrows(DomainException.class,
              () -> new Employee(name, "   ", baseSalary, admissionDate));
   }

   @Test
   void shouldThrowExceptionWhenLengthCpfLesserThan11Characters() {
      Assertions.assertThrows(DomainException.class,
              () -> new Employee(name, "1", baseSalary, admissionDate));
   }

   @Test
   void shouldThrowExceptionWhenLengthCpfGreaterThan11Characters() {
      Assertions.assertThrows(DomainException.class,
              () -> new Employee(name, "1".repeat(12), baseSalary, admissionDate));
   }

   @Test
   void shouldThrowExceptionWhenBaseSalaryIsNull() {
      Assertions.assertThrows(DomainException.class,
              () -> new Employee(name, cpf, null, admissionDate));
   }

   @Test
   void shouldThrowExceptionWhenBaseSalaryIsLesserThan0() {
      Assertions.assertThrows(DomainException.class,
              () -> new Employee(name, cpf, BigDecimal.valueOf(-150), admissionDate));
   }

   @Test
   void shouldThrowExceptionWhenBaseSalaryIsEquals0() {
      Assertions.assertThrows(DomainException.class,
              () -> new Employee(name, cpf, BigDecimal.valueOf(0), admissionDate));
   }

   @Test
   void shouldThrowExceptionWhenSettingBaseSalaryNull() {
      Employee employee = new Employee(name, cpf, baseSalary, admissionDate);
      Assertions.assertThrows(DomainException.class,
              () -> employee.setBaseSalary(null));
   }

   @Test
   void shouldThrowExceptionWhenSettingBaseSalaryZeroOrNegative() {
      BigDecimal zero = BigDecimal.ZERO;
      BigDecimal negative = BigDecimal.valueOf(-100);

      Employee employee = new Employee(name, cpf, baseSalary, admissionDate);

      Assertions.assertThrows(DomainException.class,
              () -> employee.setBaseSalary(zero));

      Assertions.assertThrows(DomainException.class,
              () -> employee.setBaseSalary(negative));
   }

   @Test
   void shouldThrowExceptionWhenAdmissionDateIsNull() {
      Assertions.assertThrows(DomainException.class,
              () -> new Employee(name, cpf, baseSalary, null));
   }
}
