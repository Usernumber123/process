package com.example.process.service;

import com.example.process.model.Message;
import com.example.process.model.MessageCensured;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
@Service
public class ProducerService {
    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    public void sendMessageToUI(Message msg) {
        ListenableFuture<SendResult<String, Message>> future = kafkaTemplate.send("msg3", msg);
        future.addCallback(System.out::println, System.err::println);
        kafkaTemplate.flush();
    }
}
