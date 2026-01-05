package afsj.prod_metrics.entities;

import jakarta.persistence.*;

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
   private Double baseSalary;

   private LocalDate admissionDate;

   protected Employee() {
   }

   public Employee(String name, String cpf, Double baseSalary, LocalDate admissionDate) {
      this.name = name;
      this.cpf = cpf;
      this.baseSalary = baseSalary;
      this.admissionDate = admissionDate;
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

   public Double getBaseSalary() {
      return baseSalary;
   }

   public void setBaseSalary(Double baseSalary) {
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
}
