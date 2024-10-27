package com.w2m.spaceShips.domain.model;


import java.time.LocalDateTime;

public class LogMessage {
    private String endpoint;
    private String user;
    private LocalDateTime timestamp;

    // Constructor, getters y setters

    public LogMessage(String endpoint, String user, LocalDateTime timestamp) {
        this.endpoint = endpoint;
        this.user = user;
        this.timestamp = timestamp;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
