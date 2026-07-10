package com.traffic.smart_traffic_backend.dto;

public class TrafficUpdate {

    private String message;

    public TrafficUpdate() {
    }

    public TrafficUpdate(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}