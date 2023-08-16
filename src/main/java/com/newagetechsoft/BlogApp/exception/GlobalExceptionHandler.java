package com.newagetechsoft.BlogApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionDetails> handle(ResourceNotFoundException resourceNotFoundException,
                                                   WebRequest webRequest){
        ExceptionDetails exceptionDetails = new ExceptionDetails();
        exceptionDetails.setTimestamp(LocalDateTime.now());
        exceptionDetails.setMessage(resourceNotFoundException.getMessage());
        exceptionDetails.setDetail(webRequest.getDescription(false));

        return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);
    }
}
