package com.paychex.timekeeper.exception.exceptionHandler;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ErrorMessageResponse {
    Set<String> errors = new HashSet<>();
    String error;
    boolean err = false;

    public ErrorMessageResponse(String m) {
        this.error = m;
        this.err = true;
    }

    public void addError(String m) {
        this.errors.add(m);
        this.err = true;
    }
}
