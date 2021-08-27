package com.efimov.process.service.impl;

import com.efimov.process.model.Message;
import com.efimov.process.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerServiceImpl implements ProducerService {
    public static final String MESSAGE_FOR_STORE_TOPIC = "MessageForStoreTopic";
    private final KafkaTemplate<String, Message> kafkaTemplate;
    @Override
    public void sendMessageForStore(Message msg) {
        kafkaTemplate.send(MESSAGE_FOR_STORE_TOPIC, msg);
        kafkaTemplate.flush();
    }
}
