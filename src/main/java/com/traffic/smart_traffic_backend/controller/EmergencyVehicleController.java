package com.traffic.smart_traffic_backend.controller;

import com.traffic.smart_traffic_backend.model.EmergencyVehicle;
import com.traffic.smart_traffic_backend.service.EmergencyVehicleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emergency")
public class EmergencyVehicleController {

    private final EmergencyVehicleService service;

    public EmergencyVehicleController(
            EmergencyVehicleService service) {
        this.service = service;
    }

    @PostMapping
    public EmergencyVehicle save(
            @RequestBody EmergencyVehicle vehicle) {

        return service.save(vehicle);
    }

    @GetMapping
    public List<EmergencyVehicle> getAll() {

        return service.getAll();
    }
}