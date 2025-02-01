package com.spring.boilerplate.spring_boilerplate.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RequiredArgsConstructor
@Getter
@Setter
public class GlobalException {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException ex, HttpServletRequest request) {
        return ResponseEntity
                .status(ex.getHttpStatus())
                .body(new ErrorResponse(
                        ex.getHttpStatus().value(), // Status
                        request.getRequestURI(), // Path
                        ex.getHttpStatus().getReasonPhrase(), // Error
                        ex.getMessage(), // Message
                        null // Data
                ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        String message = errors.values().stream().findFirst().orElse("Validation failed");

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(
                        HttpStatus.BAD_REQUEST.value(), // Status
                        request.getRequestURI(), // Path
                        HttpStatus.BAD_REQUEST.getReasonPhrase(), // Error
                        message, // Message
                        errors // Data
                ));
    }

    @Getter
    public static class ErrorResponse {
        private final int status;
        private final String path;
        private final String error;
        private final String message;
        private final Object data;

        public ErrorResponse(int status, String path, String error, String message, Object data) {
            this.status = status;
            this.path = path;
            this.error = error;
            this.message = message;
            this.data = data;
        }
    }
}
