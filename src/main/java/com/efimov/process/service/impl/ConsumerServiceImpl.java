package com.efimov.process.service.impl;

import com.efimov.process.model.Message;
import com.efimov.process.service.ConsumerService;
import com.efimov.process.service.ForbiddenWordsStoreService;
import com.efimov.process.service.ProducerService;
import com.efimov.process.service.StringParserService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {
    public static final String MESSAGE_FOR_CENSURED_TOPIC = "messageForCensuredTopic";
    public static final String REGISTRATION_FORBIDDEN_WORDS_TOPIC = "registrationForbiddenWordsTopic";
    public static final String DELETE_FORBIDDEN_WORDS_TOPIC = "deleteForbiddenWordsTopic";
    public static final String LISTENER_ID_1 = "listenerId1";
    public static final String LISTENER_ID_2 = "listenerId2";
    public static final String LISTENER_ID_3 = "listenerId3";
    private final StringParserService stringParserService;
    private final ProducerService producerService;
    private final ForbiddenWordsStoreService forbiddenWordsService;

    @Override
    @KafkaListener(id = LISTENER_ID_1, topics = MESSAGE_FOR_CENSURED_TOPIC)
    public void messageForCensuredListener(ConsumerRecord<String, Object> record) {
        Message message = stringParserService.stringParse(record.value().toString());
        producerService.sendMessageForStore(message);
    }

    @Override
    @KafkaListener(id = LISTENER_ID_2, topics = REGISTRATION_FORBIDDEN_WORDS_TOPIC)
    public void forbiddenWordsListener(ConsumerRecord<String, Object> record) {
        forbiddenWordsService.saveForbiddenWord(record.value().toString());
    }

    @Override
    @KafkaListener(id = LISTENER_ID_3, topics = DELETE_FORBIDDEN_WORDS_TOPIC)
    public void deleteForbiddenWordsListener(ConsumerRecord<String, Object> record) {
        forbiddenWordsService.deleteForbiddenWord(record.value().toString());
    }

}
