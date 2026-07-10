package com.traffic.smart_traffic_backend.controller;

import com.traffic.smart_traffic_backend.dto.TrafficUpdate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class TestTrafficController {

    private final SimpMessagingTemplate messagingTemplate;

    public TestTrafficController(
            SimpMessagingTemplate messagingTemplate) {

        this.messagingTemplate = messagingTemplate;
    }

    @GetMapping("/send")
    public String sendMessage() {

        messagingTemplate.convertAndSend(
                "/topic/traffic",
                new TrafficUpdate(
                        "Traffic Updated at "
                                + System.currentTimeMillis()
                )
        );

        return "Message Sent";
    }
}