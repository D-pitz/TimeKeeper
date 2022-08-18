package com.paychex.timekeeper.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ControllerAdvice
public class ErrorMessage extends RuntimeException{

    HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
    Set<String> errors = new HashSet<>();
    String error;
    boolean err = false;

    public ErrorMessage(String m) {
        this.error = m;
        this.err = true;
        throwEx(this);
    }

    public void addError(String m) {
        this.errors.add(m);
        this.err = true;
        throwEx(this);
    }

    public void throwEx(ErrorMessage ex) { throw ex; }
}
