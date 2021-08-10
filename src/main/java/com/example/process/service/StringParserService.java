package com.example.process.service;

import com.example.process.entity.Word;
import com.example.process.model.Message;
import com.example.process.model.MessageCensured;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Slf4j
@RequiredArgsConstructor
public class StringParserService {
    private final ForbiddenWordsService forbiddenWordsService;

    public Message jsonMessageConverter(String messageReceived){
        Message message=new Gson().fromJson(messageReceived, Message.class);
        return message;
    }

    public Message stringParse (String messageReceived){
        Message message= jsonMessageConverter(messageReceived);

        forbiddenWordsService.updateForbiddenWords();

        MessageCensured messageCensured=new MessageCensured(message);
        String[] subStr;
        String delimeter = " ";
        subStr = message.getMessage().split(delimeter);
        for (String s : subStr) {
            if (Word.forbiddenWords.containsKey(s)) {
                messageCensured.setCensoredMessage( messageCensured.getCensoredMessage().replace(s, censor(s)));
            }
        }


      return  messageCensured;
    }

    private String censor(String string){
        int n = string.length();
        char[] chars = new char[n];
        Arrays.fill(chars, '*');
        return new String(chars);
    }
}
