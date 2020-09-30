package com.mercadolibre.testgian.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ApiException {
    public ApiException(String message, java.lang.Throwable throwable, org.springframework.http.HttpStatus httpStatus, ZonedDateTime timeStamp) {
        Message = message;
        HttpStatus = httpStatus;
        TimeStamp = timeStamp;
    }

    public String getMessage() {
        return Message;
    }



    public org.springframework.http.HttpStatus getHttpStatus() {
        return HttpStatus;
    }

    public ZonedDateTime getTimeStamp() {
        return TimeStamp;
    }

    private final String Message;
    private final HttpStatus HttpStatus;
    private final ZonedDateTime TimeStamp;




}
