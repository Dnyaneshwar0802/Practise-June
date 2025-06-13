package com.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> handleProductNotFound(ProductNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of("error", "Product Not Found", "message", ex.getMessage(), "timestamp", LocalDateTime.now())
        );
    }

    @ExceptionHandler(InventoryNotFoundException.class)
    public ResponseEntity<?> handleInventoryNotFound(InventoryNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of("error", "Inventory Not Found", "message", ex.getMessage(), "timestamp", LocalDateTime.now())
        );
    }

    @ExceptionHandler(ProductDeletionException.class)
    public ResponseEntity<?> handleProductDeletion(ProductDeletionException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of("error", "Product Deletion Failed", "message", ex.getMessage(), "timestamp", LocalDateTime.now())
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneral(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                Map.of("error", "Internal Server Error", "message", ex.getMessage(), "timestamp", LocalDateTime.now())
        );
    }
}
