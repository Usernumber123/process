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
        String[] wordsOfMsg;
        String delimeter = " ";
        wordsOfMsg = message.getMessage().split(delimeter);
        for (String word : wordsOfMsg) {
            if (Word.forbiddenWords.containsKey(word)) {
                if(Word.forbiddenWords.get(word)>=(message.getAge()))
                messageCensured.setCensoredMessage( messageCensured.getCensoredMessage().replace(word, censor(word)));
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
