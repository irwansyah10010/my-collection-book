package com.lawencon.readcollection.base.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.lawencon.readcollection.base.dto.res.BaseInsertResDto;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<BaseInsertResDto> handleRuntimeException(RuntimeException ex) {
        BaseInsertResDto res = new BaseInsertResDto();
        res.setMessage(ex.getMessage()); // pesan dari service
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }
}
