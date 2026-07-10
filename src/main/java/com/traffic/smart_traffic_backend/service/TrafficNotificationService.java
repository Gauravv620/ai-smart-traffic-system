package com.traffic.smart_traffic_backend.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class TrafficNotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    public TrafficNotificationService(
            SimpMessagingTemplate messagingTemplate) {

        this.messagingTemplate = messagingTemplate;
    }

    public void sendNotification(String message) {

        messagingTemplate.convertAndSend(
                "/topic/traffic",
                message
        );
    }
}