package com.traffic.smart_traffic_backend.controller;

import com.traffic.smart_traffic_backend.dto.TrafficUpdate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class TrafficWebSocketController {

    @MessageMapping("/traffic")
    @SendTo("/topic/traffic")
    public TrafficUpdate sendUpdate(
            TrafficUpdate update) {

        return update;
    }
}