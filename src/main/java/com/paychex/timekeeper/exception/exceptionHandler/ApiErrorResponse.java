package com.paychex.timekeeper.exception.exceptionHandler;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Arrays;

@Getter
@Setter
@NoArgsConstructor
public class ApiErrorResponse {

    private String message;
    private String exception;
    private String cause;
    private String className;
    private String trace;
    private LocalDateTime timeStamp;
    private Boolean error = true;

    public ApiErrorResponse(String message, Throwable cause, String className) {
        this.message = message;
        this.exception = cause.getClass().getSimpleName();
        this.cause = cause.getLocalizedMessage();
        this.trace = Arrays.toString(cause.getStackTrace()).substring(1,190);
        this.className = className;
        this.timeStamp = LocalDateTime.now();
    }
}