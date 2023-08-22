package com.newagetechsoft.BlogApp.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BlogApiException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String message;

    public BlogApiException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message;
    }


}
