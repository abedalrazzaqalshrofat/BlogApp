package com.newagetechsoft.BlogApp.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExceptionDetails {

    private LocalDateTime timestamp;
    private String message;
    private String detail;


}
