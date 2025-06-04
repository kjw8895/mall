package com.mall.exception;

import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Map<String, Object>> handleCustomException(CustomException ex) {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("message", ex.getMessage());
        errorBody.put("status", ex.getStatus());
        errorBody.put("timestamp", DateTime.now().toString());
        return new ResponseEntity<>(errorBody, HttpStatus.valueOf(ex.getStatus()));
    }
}