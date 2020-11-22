package com.message.publisher.service.messagegenerator;

import com.message.publisher.entity.Message;
import com.message.publisher.entity.MessageTypesHolder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RandomFieldsMessageGenerator implements MessageGenerator{

    private final MessageTypesHolder;

    private final AtomicInteger messageIdCounter;

    public RandomFieldsMessageGenerator() {
        messageIdCounter = new AtomicInteger(0);
    }

    @Override
    public Message generateMessage() {

        int msisdn;
        String action;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        return new Message(
                messageIdCounter.getAndIncrement(),
                msisdn,
                action,
                timestamp
        );
    }

    public Integer getMessageIdCounter() {
        return messageIdCounter.get();
    }

    public static void main(String[] args) {
        RandomFieldsMessageGenerator mg = new RandomFieldsMessageGenerator();

        System.out.println(mg.getMessageIdCounter());

        Runnable task2 = () -> {
            for (int i = 0; i <10000000 ; i++) {
                mg.generateMessage();
            } };

        Runnable task3 = () -> {
            for (int i = 0; i <10000000 ; i++) {
                mg.generateMessage();
            } };

        Runnable task4 = () -> {
            for (int i = 0; i <10000000 ; i++) {
                mg.generateMessage();
            } };

// start the thread
        new Thread(task2).start();
        new Thread(task3).start();
        new Thread(task4).start();
        for (int i = 0; i < 10000000 ; i++) {
            mg.generateMessage();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(mg.getMessageIdCounter());

    }
}
