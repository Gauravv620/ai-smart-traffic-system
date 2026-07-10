package com.traffic.smart_traffic_backend.controller;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notify")
public class TrafficNotificationController {

    private final SimpMessagingTemplate messagingTemplate;

    public TrafficNotificationController(
            SimpMessagingTemplate messagingTemplate) {

        this.messagingTemplate = messagingTemplate;
    }

    @PostMapping
    public String notifyDashboard() {

        messagingTemplate.convertAndSend(
                "/topic/traffic",
                "refresh"
        );

        return "Notification Sent";
    }
}