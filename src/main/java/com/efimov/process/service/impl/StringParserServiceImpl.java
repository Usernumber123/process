package com.efimov.process.service.impl;

import com.efimov.process.entity.Word;
import com.efimov.process.model.Message;
import com.efimov.process.service.StringParserService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Slf4j
@RequiredArgsConstructor
public class StringParserServiceImpl implements StringParserService {
    private final ForbiddenWordsStoreServiceImpl forbiddenWordsService;

    private Message jsonMessageConverter(String messageReceived) {
        return new Gson().fromJson(messageReceived, Message.class);
    }

    @Override
    public Message stringParse(String messageReceived) {
        Message message = jsonMessageConverter(messageReceived);
        message.setCensoredMessage(message.getMessage());
        forbiddenWordsService.updateCashForbiddenWords();

        String[] wordsOfMsg;
        String delimeter = " ";
        wordsOfMsg = message.getMessage().split(delimeter);
        for (String word : wordsOfMsg) {
            if (Word.forbiddenWords.containsKey(word)) {
                if (Word.forbiddenWords.get(word).equals(message.getChat()) || Word.forbiddenWords.get(word).equals("*"))
                    message.setCensoredMessage(message.getCensoredMessage().replace(word, censor(word)));
            }
        }


        return message;
    }

    private String censor(String string) {
        int n = string.length();
        char[] chars = new char[n];
        Arrays.fill(chars, '*');
        return new String(chars);
    }
}
