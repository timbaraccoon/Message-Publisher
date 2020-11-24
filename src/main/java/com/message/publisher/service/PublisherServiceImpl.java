package com.message.publisher.service;

import com.message.publisher.entity.Message;
import com.message.publisher.service.messagegenerator.MessageGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublisherServiceImpl implements PublisherService {

    private final MessageGenerator messageGenerator;

    @Autowired
    public PublisherServiceImpl(MessageGenerator messageGenerator) {
        this.messageGenerator = messageGenerator;
    }

    @Override
    public Message sendMessage() {
        return messageGenerator.generateMessage();
    }
}
