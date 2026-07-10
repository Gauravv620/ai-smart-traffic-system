package com.traffic.smart_traffic_backend.model;

public class TrafficNotification {

    private String message;

    public TrafficNotification() {
    }

    public TrafficNotification(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}