package com.transaction.execption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ErrorHandler {
	
	@ExceptionHandler(value = {BusinessException.class})
    public ResponseEntity businessException(BusinessException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
	
	@ExceptionHandler(value = {UsernameNotFoundException.class})
    public ResponseEntity unauthorizedException(BusinessException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }
	
	
}
