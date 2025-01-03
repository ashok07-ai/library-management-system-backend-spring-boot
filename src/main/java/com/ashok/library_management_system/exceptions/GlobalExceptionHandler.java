package com.ashok.library_management_system.exceptions;

import com.ashok.library_management_system.payload.response.APIResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Handles validation errors for request body.
     *
     * @param e The {@link MethodArgumentNotValidException} containing validation errors.
     * @return A consistent error response wrapped in {@link APIResponse}.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse<Map<String, String>>> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        Map<String, String> fieldErrors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(err -> {
            String fieldName = ((FieldError) err).getField();
            String message = err.getDefaultMessage();
            fieldErrors.put(fieldName, message);
        });

        APIResponse<Map<String, String>> apiResponse = new APIResponse<>("Validation failed", false, fieldErrors);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles validation errors for request parameters.
     *
     * @param e The {@link ConstraintViolationException}.
     * @return A consistent error response wrapped in {@link APIResponse}.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<APIResponse<Map<String, String>>> ConstraintViolationExceptionHandler(ConstraintViolationException e) {
        Map<String, String> fieldErrors = new HashMap<>();
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            fieldErrors.put(propertyPath, message);
        }

        APIResponse<Map<String, String>> apiResponse = new APIResponse<>("Validation failed", false, fieldErrors);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles resource not found exceptions.
     *
     * @param e The {@link ResourceNotFoundException}.
     * @return A consistent error response wrapped in {@link APIResponse}.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponse<Void>> ResourceNotFoundExceptionHandler(ResourceNotFoundException e) {
        APIResponse<Void> apiResponse = new APIResponse<>(e.getMessage(), false, null);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles application-specific API exceptions.
     *
     * @param e The {@link APIException}.
     * @return A consistent error response wrapped in {@link APIResponse}.
     */
    @ExceptionHandler(APIException.class)
    public ResponseEntity<APIResponse<Void>> APIExceptionHandler(APIException e) {
        APIResponse<Void> apiResponse = new APIResponse<>(e.getMessage(), false, null);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     *Handles exceptions for URL not found.
     *
     * @param e The {@link NoHandlerFoundException}.
     * @return A `ResponseEntity` with a custom `APIResponse` and a 404 NOT_FOUND status.
     */

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<APIResponse> handleNoHandlerFoundException(NoHandlerFoundException e) {
        String message = "The requested URL was not found: " + e.getRequestURL();
        APIResponse<String> response = new APIResponse<>(message, false, null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     *Handles exceptions for METHOD not allowed.
     *
     * @param e The {@link HttpRequestMethodNotSupportedException}.
     * @return A `ResponseEntity` with a custom `APIResponse` and a 405 METHOD_NOT_ALLOWED status.
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<APIResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        String message = "The request method '" + e.getMethod() + "' is not supported for this URL.";
        APIResponse<String> response = new APIResponse<>(message, false, null);
        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * Handles any unexpected exceptions.
     *
     * @param ex The {@link Exception}.
     * @return A consistent error response wrapped in {@link APIResponse}.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<Void>> handleGenericException(Exception ex) {
        APIResponse<Void> apiResponse = new APIResponse<>(
                "An unexpected error occurred. Please contact support if the issue persists.",
                false,
                null
        );
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
