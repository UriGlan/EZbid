package com.ezbid.exception;  // Adjust this based on your package structure

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException e) {
        // Prepare the response as a map with a 'message' key
        Map<String, String> response = new HashMap<>();
        response.put("message", e.getMessage());

        // Return the response with a bad request (400) status
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
