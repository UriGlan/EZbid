package com.ezbid.exception;  // Adjust this based on your package structure
//
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

// This class is a global exception handler that catches all exceptions thrown by the application
@RestControllerAdvice
public class GlobalExceptionHandler {

    // This method handles all exceptions of type ResourceNotFoundException
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException e) {
        // Prepare the response as a map with a 'message' key
        Map<String, String> response = new HashMap<>();
        response.put("message", e.getMessage());

        // Return the response with a bad request (400) status;
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // This method handles DataIntegrityViolationException
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolation(DataIntegrityViolationException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Data integrity violation");
        // Check if it's a duplicate key violation
        if (ex.getMessage().contains("Duplicate entry")) {
            response.put("message", "Email or Username already exists");
        }
        // Generic message for other data integrity violations
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
