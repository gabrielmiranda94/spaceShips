package com.w2m.spaceShips.application.ports.output;

import com.w2m.spaceShips.domain.model.Event;

public interface EventProducer {
    void sendEvent();
}
