package afsj.prod_metrics.entities;

import afsj.prod_metrics.exceptions.DomainException;
import afsj.prod_metrics.utils.Validations;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Employee {

   @Id
   @GeneratedValue(strategy = GenerationType.UUID)
   private UUID id;

   @Column(nullable = false)
   private String name;

   @Column(unique = true, nullable = false, updatable = false)
   private String cpf;

   @Column(nullable = false)
   private BigDecimal baseSalary;

   @Column(nullable = false, updatable = false)
   private LocalDate admissionDate;

   protected Employee() {
   }

   public Employee(String name, String cpf, BigDecimal baseSalary, LocalDate admissionDate) {
      Validations.validateName(name);
      validateCpf(cpf);
      validateBaseSalary(baseSalary);
      Validations.validateDate(admissionDate);

      this.name = name;
      this.cpf = cpf;
      this.baseSalary = baseSalary;
      this.admissionDate = admissionDate;
   }

   public static Employee create(String name, String cpf, BigDecimal baseSalary, LocalDate admissionDate){
      return new Employee(name, cpf, baseSalary, admissionDate);
   }

   public UUID getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public String getCpf() {
      return cpf;
   }

   public BigDecimal getBaseSalary() {
      return baseSalary;
   }

   public void setBaseSalary(BigDecimal baseSalary) {
      validateBaseSalary(baseSalary);
      this.baseSalary = baseSalary;
   }

   public LocalDate getAdmissionDate() {
      return admissionDate;
   }

   @Override
   public boolean equals(Object o) {
      if (!(o instanceof Employee employee)) return false;
      return id != null && id.equals(employee.id);
   }

   @Override
   public int hashCode() {
      return getClass().hashCode();
   }

   private void validateCpf(String cpf) {
      DomainException.when(cpf == null || cpf.isBlank(), "CPF is required");
      DomainException.when(cpf.length() != 11, "CPF must contain exactly 11 characters.");
   }

   private void validateBaseSalary(BigDecimal salary) {
      DomainException.when(salary == null || salary.compareTo(BigDecimal.ZERO) <= 0, "Base Salary must be greater than 0.");
   }
}
