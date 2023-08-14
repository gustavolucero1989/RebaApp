package com.reba.challenge.config.exception;

import com.reba.challenge.config.exception.model.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class CustomErrorHandler {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<GenericResponse<String>> handleNoSuchElementException(NoSuchElementException ex) {
        GenericResponse<String> response = GenericResponse.<String>builder()
            .statusCode(HttpStatus.NOT_FOUND.value())
            .status(HttpStatus.NOT_FOUND.getReasonPhrase())
            .data(ex.getMessage())
            .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler
    public ResponseEntity<GenericResponse<String>> handleOtherExceptions(Exception ex) {
        GenericResponse<String> response = GenericResponse.<String>builder()
            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .status(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
            .data(ex.getMessage())
            .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<GenericResponse<String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        String errorMessage = ex.getMessage();
        GenericResponse<String> response = GenericResponse.<String>builder()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .data(errorMessage)
            .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
