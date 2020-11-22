package com.message.publisher.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@Data
@RequiredArgsConstructor
public class Message {

    private final int MessageId;
    private final int Msisdn;
    private final String action;
    private final Timestamp timestamp;

}
