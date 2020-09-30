package com.mercadolibre.testgian.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
@ControllerAdvice
public class ApiExceptionHandler  {
    @ExceptionHandler(value={ApiRequestException.class})
    public ResponseEntity<Object> HandleApiRequestException(ApiRequestException e){
        HttpStatus badRequest= HttpStatus.NOT_FOUND;
        ApiException apiException =new ApiException(
                e.getMessage(),
                e,
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException,badRequest);
    }
}
