/*
package com.w2m.spaceShips.application.service;

import com.w2m.spaceShips.domain.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class KafkaService {

    @Autowired
    private KafkaTemplate<String, Event> kafkaTemplate;


    public void produce(String endpoint, String user) {
        Event logMessage = Event.builder()
                .endpoint(endpoint)
                .user(user)
                .timestamp(LocalDateTime.now()).build();

        kafkaTemplate.send("spaceships-events", logMessage);
    }

}
*/
