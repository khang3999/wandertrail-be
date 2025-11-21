package com.example.wandertrail_user_service.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // Xử lí exception cho các lỗi 
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidation(ValidationException ex) {
        Map<String, Object> response = Map.of(
                "status", 400,
                "message", ex.getMessage(),
                "fieldErrors", ex.getFieldErrors());
        return ResponseEntity.badRequest().body(response);
        // Map<String, String> error = new HashMap<>();
        // error.put("error", e.getMessage());
        // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(error -> fieldErrors.put(error.getField(), error.getDefaultMessage()));

        Map<String, Object> response = Map.of(
                "status", 400,
                "message", "Validation failed",
                "fieldErrors", fieldErrors);

        return ResponseEntity.badRequest().body(response);
    }
}
