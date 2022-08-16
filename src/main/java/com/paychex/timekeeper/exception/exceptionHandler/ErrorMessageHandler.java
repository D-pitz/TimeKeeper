package com.paychex.timekeeper.exception.exceptionHandler;

import com.paychex.timekeeper.messages.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorMessageHandler {

    @ExceptionHandler( {ErrorMessage.class} )
    protected ResponseEntity<ErrorMessageResponse> handleErrorMessage(ErrorMessage em) {
        return new ResponseEntity<>(new ErrorMessageResponse(em.getError()), HttpStatus.BAD_REQUEST);
    }
}
