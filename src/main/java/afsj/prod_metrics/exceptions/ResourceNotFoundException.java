package afsj.prod_metrics.exceptions;

public class ResourceNotFoundException extends RuntimeException {
   public ResourceNotFoundException(String message) {
      super(message);
   }
}
