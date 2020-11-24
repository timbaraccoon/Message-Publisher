package com.message.publisher.entity;

import lombok.*;

import java.sql.Timestamp;

@RequiredArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class Message {

    private final int MessageId;
    private final int Msisdn;
    private final String action;
    private final Timestamp timestamp;

}
