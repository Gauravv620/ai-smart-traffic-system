package com.traffic.smart_traffic_backend.service;

import com.traffic.smart_traffic_backend.model.CongestionData;
import com.traffic.smart_traffic_backend.repository.CongestionDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CongestionDataService {

    private final CongestionDataRepository repository;

    public CongestionDataService(
            CongestionDataRepository repository) {

        this.repository = repository;
    }

    public CongestionData save(
            CongestionData data) {

        return repository.save(data);
    }

    public List<CongestionData> getAll() {

        return repository.findAll();
    }
}