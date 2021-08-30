package com.efimov.process.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface ConsumerService {
    void messageForCensuredListener(ConsumerRecord<String, Object> record);

    void forbiddenWordsListener(ConsumerRecord<String, Object> record);

    void deleteForbiddenWordsListener(ConsumerRecord<String, Object> record);
}
