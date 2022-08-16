package com.paychex.timekeeper.exception.exceptionHandler;

import com.paychex.timekeeper.exception.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler({ ApiException.class })
    protected ResponseEntity<ApiErrorResponse> handleApiException(ApiException ex) {
        return new ResponseEntity<>(
                new ApiErrorResponse(ex.getMessage(), ex.getCause(), ex.getClassName()),
                ex.getStatus());
    }
}
