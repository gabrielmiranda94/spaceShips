package com.w2m.spaceShips.infrastructure.messaging;

import com.w2m.spaceShips.application.ports.output.EventProducer;
import com.w2m.spaceShips.domain.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import jakarta.servlet.http.HttpServletRequest;


import java.time.LocalDateTime;

@Service
public class EventProducerImpl implements EventProducer {
    @Autowired
    private  KafkaTemplate<String, Event> kafkaTemplate;
    @Autowired
    private HttpServletRequest request;

    @Override
    public void sendEvent() {

        Event event = Event.builder()
                .endpoint(request.getRequestURI())
                .user(getCurrentUsername())
                .timestamp(LocalDateTime.now())
                .build();

        kafkaTemplate.send("audit", event);
    }

    private String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }

}
