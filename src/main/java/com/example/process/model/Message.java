package com.example.process.model;

import com.example.process.entity.Word;
import liquibase.pro.packaged.S;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashMap;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Message {
    String author;
    String message;
    Integer age;
    String dateAndTime;
    public Message(Message message) {
        this.author=message.getAuthor();
        this.age=message.getAge();
        this.message=message.getMessage();
        this.dateAndTime = message.getDateAndTime();
    }
}
