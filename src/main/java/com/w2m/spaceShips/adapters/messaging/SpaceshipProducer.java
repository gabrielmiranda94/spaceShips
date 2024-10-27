package com.w2m.spaceShips.adapters.messaging;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class SpaceshipProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public SpaceshipProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        kafkaTemplate.send("spaceshipTopic", message);
    }
}