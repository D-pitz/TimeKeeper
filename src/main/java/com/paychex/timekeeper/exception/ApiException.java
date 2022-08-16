package com.paychex.timekeeper.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Getter
@Setter
@ControllerAdvice
@NoArgsConstructor
public class ApiException extends RuntimeException {
    private final long serialVersionUID = 1L;

    private HttpStatus status;
    private String message;
    private Throwable cause;
    private String className;
    private Boolean error;

    public ApiException(String message, Throwable e, String className) {
        super(e.getLocalizedMessage(), e, false, false);
        this.message = message;
        this.cause = super.getCause();
        this.className = className;
        this.error = true;
        this.status = HttpStatus.BAD_REQUEST;
        throwEx(this);
    }

    public void throwEx(ApiException ex) {
        throw ex;
    }
}
