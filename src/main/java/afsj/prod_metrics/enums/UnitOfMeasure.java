package afsj.prod_metrics.enums;

public enum UnitOfMeasure {
   KG(1.0),
   L(1.0),
   UN(1.0),
   ARR(15.0);

   private final double factorToBase;

   UnitOfMeasure(double factorToBase) {
      this.factorToBase = factorToBase;
   }

   public double toBase(double value) {
      return value * factorToBase;
   }
}