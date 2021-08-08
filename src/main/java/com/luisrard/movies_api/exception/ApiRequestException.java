package com.luisrard.movies_api.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * Exception implementation to use on the controllers and also save the http status
 */
@Getter
@Setter
public class ApiRequestException extends RuntimeException{
    private final HttpStatus httpStatus;

    public ApiRequestException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

}
