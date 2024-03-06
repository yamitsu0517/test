package com.example.demo.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class HelloExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler
    public ResponseEntity<Object> handleConstraintViolationException (ConstraintViolationException ex,
            WebRequest request) {
        return super.handleExceptionInternal (ex, "validation error", null, HttpStatus.BAD_REQUEST, request);
    }
}