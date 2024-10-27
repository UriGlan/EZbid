package com.ezbid.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// This class is an exception that returns a 404 Not Found status code
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    // Constructor that accepts a message and passes it to the parent class constructo
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
