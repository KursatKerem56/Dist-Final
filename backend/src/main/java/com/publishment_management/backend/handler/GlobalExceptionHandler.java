package com.publishment_management.backend.handler;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.publishment_management.backend.exceptions.BaseException;
import com.publishment_management.backend.models.RootEntity;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    
    private static final String HOSTNAME = resolveHostname();

    
    @ExceptionHandler(value = {BaseException.class})
    public ResponseEntity<RootEntity<ApiError<?>>> handleBaseException(BaseException exception, HttpServletRequest request) {
        
        log.error("Base excepion threw {}", exception.getMessage(), exception);
        HttpStatus status = HttpStatus.BAD_REQUEST; 

        ApiError<?> apiError = createApiError(exception.getMessage(), request, status);
        RootEntity<ApiError<?>> rootEntity = RootEntity.error(exception.getMessage(), apiError);

        return ResponseEntity.status(status).body(rootEntity);
    }
    
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<RootEntity<ApiError<?>>> handleGeneralException(Exception ex, HttpServletRequest request) {
    log.error("An unexpected error occured", ex);
    
    ApiError<?> apiError = createApiError("Internal server error",request,HttpStatus.INTERNAL_SERVER_ERROR);
    
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    .body(RootEntity.error("Internal server error", apiError));
    }
    
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RootEntity<ApiError<?>>> handleValidationException(MethodArgumentNotValidException ex,HttpServletRequest request) {
    Map<String, String> errors = new HashMap<>();

    ex.getBindingResult().getFieldErrors().forEach(error ->
    errors.put(error.getField(), error.getDefaultMessage())

    );

    ApiError<?> apiError = createApiError(errors, request, HttpStatus.BAD_REQUEST);

    return ResponseEntity.badRequest()
    .body(RootEntity.error("Validation failed", apiError));
    }
    
    
    private static String resolveHostname() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            log.warn("Hostname could not get, default hostname is using", e);
            return "unknown-host";
        }
    }
    
    
    public <E> ApiError<E> createApiError(E message, HttpServletRequest request, HttpStatus status){
        ApiError<E> apiError = new ApiError<>();
        apiError.setStatus(status.value());
        
        ErrorDetails<E> errorDetails = new ErrorDetails<>();
        errorDetails.setHostName(HOSTNAME); 
        errorDetails.setPath(request.getRequestURI()); 
        errorDetails.setMessage(message);
        
        apiError.setErrorDetails(errorDetails); 
        return apiError;
    }
}
