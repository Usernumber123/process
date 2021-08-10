package com.example.process.service;

import com.example.process.entity.Word;
import com.example.process.model.Message;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class ConsumerService {
    private final StringParserService stringParserService;
    private final ProducerService producerService;
    private final ForbiddenWordsService forbiddenWordsService;

    @KafkaListener(id = "one", topics = "msg1")
    public void msgListener(ConsumerRecord<String, Object> record) {
        Message message= stringParserService.stringParse(record.value().toString());
        producerService.sendMessageToUI(message);
    }

    @KafkaListener(id = "one1", topics = "forbiddenWords")
    public void forbiddenWordsListener(ConsumerRecord<String, Object> record) {
        forbiddenWordsService.stringParseWord(record.value().toString());
    }

}
