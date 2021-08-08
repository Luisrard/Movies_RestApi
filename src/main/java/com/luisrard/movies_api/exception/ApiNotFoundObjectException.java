package com.luisrard.movies_api.exception;

import org.springframework.http.HttpStatus;

public class ApiNotFoundObjectException extends ApiRequestException{
    public ApiNotFoundObjectException(String nameObject){
        super(nameObject + " does not exist", HttpStatus.NOT_FOUND);
    }

    public ApiNotFoundObjectException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
