package com.ecommerce.orderMS.exceptionHandler;

import com.ecommerce.orderMS.exception.InsufficentInventoryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InsufficentInventoryException.class)
    public ResponseEntity<String> handle(InsufficentInventoryException exp){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exp.getMsg());
    }

}
