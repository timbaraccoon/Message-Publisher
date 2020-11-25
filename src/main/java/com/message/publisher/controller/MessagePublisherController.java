package com.message.publisher.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.message.publisher.entity.Message;
import com.message.publisher.service.PublisherService;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@EnableScheduling
public class MessagePublisherController {

    private final PublisherService service;

    @Autowired
    public MessagePublisherController(PublisherService service) {
        this.service = service;
    }

    @Scheduled(fixedRate = 15_000)
    private void RunMessageSendInThreads() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Runnable task = this::sendMessageViaPost;

        for (int i = 0; i < 5; i++) {
            executorService.execute(task);
        }
        executorService.shutdown();
    }

    private void sendMessageViaPost() {
        Message message = service.getMessage();
        String json = messageToJson(message);


        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpPost request = new HttpPost("http://localhost:8080/subscriber/message");
            StringEntity params = new StringEntity(json);
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            httpClient.execute(request);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private String messageToJson(Message message) {
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if (json != null) {
            return json;
        } else {
            throw new RuntimeException("Can't convert message, Get Null Json");
        }
    }
}
