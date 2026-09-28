package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;


import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> handleValidation(MethodArgumentNotValidException exception) {
        Map<String,String> errors = new LinkedHashMap<>();

        for(var error : exception.getBindingResult().getFieldErrors()){
            errors.put(error.getField(),error.getDefaultMessage());

        }
        return errors;
    }
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String,String>> handleStatus(
            ResponseStatusException exception
    ) {
        String message = exception.getReason();
        if(message == null) {
            message = "요청 처리 불가";

        }

        return ResponseEntity
                .status(exception.getStatusCode())
                .body(Map.of("message",message));
    }


}


