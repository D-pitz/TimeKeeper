package com.paychex.timekeeper.exception;

import com.paychex.timekeeper.messages.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//  Overrides SpringBoot default method
//  Returns class Message with set of messages with invalid inputs, sets error to true
@ControllerAdvice
public class Validator extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ErrorMessage errors = new ErrorMessage();

        ex.getBindingResult().getAllErrors().forEach((e) -> {
            String m = e.getDefaultMessage();
            errors.addError(m);
            errors.setErr(true);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
