package com.miniassignment.userValidationAssignment.exceptions;

import com.miniassignment.userValidationAssignment.entity.ErrorEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;


//Global exception handler

@ControllerAdvice
public class GlobalExceptionHandler {


// To handle custom exceptions
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException ex){
        ErrorEntity errorEntity=new ErrorEntity(ex.getMessage(),ex.getCode(),LocalDateTime.now());
        return new ResponseEntity<>(errorEntity, HttpStatusCode.valueOf(ex.getCode()));
    }

}
