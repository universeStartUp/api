package com.pe.unieventia.shared.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(ResourceNotFoundException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponseDTO errorResponse = new ErrorResponseDTO();
        errorResponse.setStatus(status.value());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, status);
    }
    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex) {
        HttpStatus status = HttpStatus.CONFLICT;
        ErrorResponseDTO errorResponse = new ErrorResponseDTO();
        errorResponse.setStatus(status.value());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, status);
    }
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationException(ValidationException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponseDTO errorResponse = new ErrorResponseDTO();
        errorResponse.setStatus(status.value());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationException(MethodArgumentNotValidException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String errorMessage = "Validation error(s) occurred.";
        List<String> errorMessages = new ArrayList<>();

        for (FieldError fieldError: ex.getBindingResult().getFieldErrors()){
            errorMessages.add(fieldError.getField() +" - "+ fieldError.getDefaultMessage());
        }
        ErrorResponseDTO errorResponse = new ErrorResponseDTO();
        errorResponse.setStatus(status.value());
        errorResponse.setMessage(errorMessage);
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setValidationErrors(errorMessages);
        return new ResponseEntity<>(errorResponse, status);
    }

}
