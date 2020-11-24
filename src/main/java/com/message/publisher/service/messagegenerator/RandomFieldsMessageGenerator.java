package com.message.publisher.service.messagegenerator;

import com.message.publisher.entity.Message;
import com.message.publisher.entity.MessageTypesHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RandomFieldsMessageGenerator implements MessageGenerator{

    private final MessageTypesHolder messageTypesHolder;
    private final AtomicInteger messageIdCounter;

    @Autowired
    public RandomFieldsMessageGenerator(MessageTypesHolder messageTypesHolder) {
        this.messageTypesHolder = messageTypesHolder;
        messageIdCounter = new AtomicInteger(0);
    }


    @Override
    public Message generateMessage() {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        int msisdn = ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE);
        String actionType = messageTypesHolder
                .getTypeById(ThreadLocalRandom.current()
                .nextInt(0, messageTypesHolder.getAllTypes().size()));

        return new Message(
                messageIdCounter.getAndIncrement(),
                msisdn,
                actionType,
                timestamp
        );
    }

}
