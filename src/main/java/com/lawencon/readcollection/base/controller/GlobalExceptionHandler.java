package com.lawencon.readcollection.base.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.lawencon.readcollection.base.dto.res.BaseInsertResDto;
import com.lawencon.readcollection.base.dto.validation.ValidationRuntimeException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    // technical error
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<BaseInsertResDto> handleRuntimeException(RuntimeException ex) {
        BaseInsertResDto res = new BaseInsertResDto();
        ex.printStackTrace();
        res.setMessage("Technical Error"); // pesan dari service
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
    }

    // validation via annotation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex) {
        BaseInsertResDto res = new BaseInsertResDto();

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage())
        );

        res.setErrors(errors);
        res.setMessage(ex.getMessage());

        return ResponseEntity.badRequest().body(res);
    }

    // validation via service
    @ExceptionHandler(ValidationRuntimeException.class)
    public ResponseEntity<?> handleBindException(ValidationRuntimeException ex) {
        BaseInsertResDto res = new BaseInsertResDto();
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
            errors.put(error.getField(), error.getDefaultMessage())
        );
        res.setErrors(errors);
        res.setMessage(ex.getMessage());
        return ResponseEntity.badRequest().body(res);
    }

    // about data
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handleBindException(ResponseStatusException ex) {
        BaseInsertResDto res = new BaseInsertResDto();
        res.setMessage(ex.getReason());
        return ResponseEntity.status(ex.getStatus()).body(res);
    }
}
