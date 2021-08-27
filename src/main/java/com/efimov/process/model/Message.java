package com.efimov.process.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Message {
    private String author;
    private String message;
    private String censoredMessage;
    private Integer age;
    private String dateAndTime;
    private String chat;

}
