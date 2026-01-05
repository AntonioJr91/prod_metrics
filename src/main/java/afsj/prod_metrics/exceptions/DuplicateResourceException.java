package afsj.prod_metrics.exceptions;

public class DuplicateResourceException extends RuntimeException {
   public DuplicateResourceException(String message) {
      super(message);
   }
}
