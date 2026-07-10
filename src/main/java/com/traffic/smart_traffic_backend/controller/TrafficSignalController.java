package com.traffic.smart_traffic_backend.controller;

import com.traffic.smart_traffic_backend.model.TrafficSignal;
import com.traffic.smart_traffic_backend.service.TrafficSignalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/signals")
@CrossOrigin("*")
public class TrafficSignalController {

    private final TrafficSignalService service;

    public TrafficSignalController(
            TrafficSignalService service) {

        this.service = service;
    }

    @PostMapping
    public TrafficSignal createSignal(
            @RequestBody TrafficSignal signal) {

        return service.save(signal);
    }

    @GetMapping
    public List<TrafficSignal> getSignals() {

        return service.getAll();
    }

    @PutMapping("/{id}/status")
    public TrafficSignal overrideStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return service.overrideStatus(id, status);
    }
}