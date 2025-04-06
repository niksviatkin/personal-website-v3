package com.viatkin.portfolio_backend.error.handler;

import com.viatkin.portfolio_backend.error.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // TODO: Add handlers for other exceptions later (e.g., ValidationException for 400 Bad Request)
}