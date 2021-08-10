package com.example.process.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageCensured extends Message{
   private String censoredMessage;
    public MessageCensured(Message message){
        super(message);
        this.censoredMessage=super.getMessage();
    }
}
