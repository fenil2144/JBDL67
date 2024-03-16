package com.example.demo.config;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.controller.PersonController;
import com.example.demo.dtos.ErrorResponse;

@Configuration
@ControllerAdvice
public class GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodNotValidException(MethodArgumentNotValidException ex){
        logger.error("In handleMethodNotValidException()");
        BindingResult result =  ex.getBindingResult();
        List<FieldError> fieldErrors =  result.getFieldErrors();

        List<String> errors = fieldErrors.stream().map(field -> field.getDefaultMessage()).collect(Collectors.toList());

        ErrorResponse errorResponse = ErrorResponse.builder().message(errors).httpStatus(HttpStatus.NOT_ACCEPTABLE).devMessage(ex.getMessage())
                .errorCode("PER_501").build();


        return new ResponseEntity(errorResponse,HttpStatus.NOT_ACCEPTABLE);
    }
}
