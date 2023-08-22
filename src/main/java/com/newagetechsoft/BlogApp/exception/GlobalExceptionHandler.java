package com.newagetechsoft.BlogApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionDetails> handleAccessDeniedException(AccessDeniedException accessDeniedException,
                                                                        WebRequest webRequest){
        ExceptionDetails exceptionDetails = new ExceptionDetails();
        exceptionDetails.setTimestamp(LocalDateTime.now());
        exceptionDetails.setMessage(accessDeniedException.getMessage());
        exceptionDetails.setDetail(webRequest.getDescription(false));

        return new ResponseEntity<>(exceptionDetails, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionDetails> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException,
                                                   WebRequest webRequest){
        ExceptionDetails exceptionDetails = new ExceptionDetails();
        exceptionDetails.setTimestamp(LocalDateTime.now());
        exceptionDetails.setMessage(resourceNotFoundException.getMessage());
        exceptionDetails.setDetail(webRequest.getDescription(false));

        return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);
    }
    /*
        .
        .
        .
        Other Exceptions that we can add them here to be handled
        .
        .
        .
     */

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDetails> handleGlobalException(Exception exception, WebRequest webRequest){
        ExceptionDetails exceptionDetails = new ExceptionDetails();
        exceptionDetails.setMessage(exception.getMessage());
        exceptionDetails.setTimestamp(LocalDateTime.now());
        exceptionDetails.setDetail(webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BlogApiException.class)
    public ResponseEntity<ExceptionDetails> blogApiExceptionResponseEntity(BlogApiException exception,
                                                                           WebRequest webRequest){
       ExceptionDetails exceptionDetails = new ExceptionDetails();
       exceptionDetails.setTimestamp(LocalDateTime.now());
       exceptionDetails.setMessage(exception.getMessage());
       exceptionDetails.setDetail(webRequest.getDescription(false));

       return new ResponseEntity<>(exceptionDetails,exception.getHttpStatus());
    }

}
