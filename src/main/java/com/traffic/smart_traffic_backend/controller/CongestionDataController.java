package com.traffic.smart_traffic_backend.controller;

import com.traffic.smart_traffic_backend.model.CongestionData;
import com.traffic.smart_traffic_backend.service.CongestionDataService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/congestion")
@CrossOrigin("*")
public class CongestionDataController {

    private final CongestionDataService service;

    public CongestionDataController(
            CongestionDataService service) {

        this.service = service;
    }

    @PostMapping
    public CongestionData create(
            @RequestBody CongestionData data) {

        return service.save(data);
    }

    @GetMapping
    public List<CongestionData> getAll() {

        return service.getAll();
    }
}