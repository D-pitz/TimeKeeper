package com.paychex.timekeeper.messages;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class Message {

    HttpStatus status = HttpStatus.OK;
    List<String> messages = new ArrayList<>();
    String content;
    boolean message = false;

    public Message(String m) {
        this.content = m;
        this.message = true;
    }

    public void addOne(String m) {
        this.message = true;
        this.messages.add(m);
    }
}
