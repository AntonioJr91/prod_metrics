package afsj.prod_metrics.utils;

import afsj.prod_metrics.exceptions.DomainException;

import java.time.LocalDate;

public final class Validations {

   public static void validateName(String str){
      DomainException.when(str == null || str.isBlank(), "Name is required.");
      DomainException.when(str.length() < 3 || str.length() > 50, "Name must contain between 3 and 50 characters.");
   }

   public static void validateEnum(Enum<?> value){
      DomainException.when(value == null, "Enum is required." );
   }

   public static void validateDate(LocalDate date){
      DomainException.when(date == null, "Date is required.");
   }
}
