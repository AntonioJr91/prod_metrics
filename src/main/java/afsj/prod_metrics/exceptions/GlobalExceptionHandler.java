package afsj.prod_metrics.exceptions;

import afsj.prod_metrics.dtos.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

   @ExceptionHandler(DuplicateResourceException.class)
   public ResponseEntity<ErrorResponse> handlerDuplicateResourceException(Exception ex, HttpServletRequest req) {
      ErrorResponse error = new ErrorResponse(
              HttpStatus.CONFLICT.value(),
              ex.getMessage(),
              req.getRequestURI(),
              LocalDateTime.now()
      );
      return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
   }

   @ExceptionHandler(ResourceNotFoundException.class)
   public ResponseEntity<ErrorResponse> handlerResourceNotFoundException(Exception ex, HttpServletRequest req) {
      ErrorResponse error = new ErrorResponse(
              HttpStatus.NOT_FOUND.value(),
              ex.getMessage(),
              req.getRequestURI(),
              LocalDateTime.now()
      );
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
   }

   @ExceptionHandler(DomainException.class)
   public ResponseEntity<ErrorResponse> handlerDomainException(Exception ex, HttpServletRequest req) {
      ErrorResponse error = new ErrorResponse(
              HttpStatus.BAD_REQUEST.value(),
              ex.getMessage(),
              req.getRequestURI(),
              LocalDateTime.now()
      );
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
   }

   @ExceptionHandler(Exception.class)
   public ResponseEntity<ErrorResponse> handlerGenericException(Exception ex, HttpServletRequest req) {
      ErrorResponse error = new ErrorResponse(
              HttpStatus.INTERNAL_SERVER_ERROR.value(),
              "Unexpected internal error.",
              req.getRequestURI(),
              LocalDateTime.now()
      );
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
   }
}