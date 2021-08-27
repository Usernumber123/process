package com.efimov.process.service;

import com.efimov.process.model.Message;

public interface ProducerService {
    void sendMessageForStore(Message msg);
}
