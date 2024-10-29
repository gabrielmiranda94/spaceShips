package com.w2m.spaceShips.domain.model;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Event {
    private String endpoint;
    private String user;
    private LocalDateTime timestamp;
}
