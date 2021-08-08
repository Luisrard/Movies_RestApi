package com.luisrard.movies_api.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Exception handler to handle the runtime exception of the rest api
 */
@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e)
    {
        //Create the pay load
        ApiException exceptionInfo = new ApiException(e.getMessage(), e.getHttpStatus(), ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(exceptionInfo, e.getHttpStatus());
    }
}
