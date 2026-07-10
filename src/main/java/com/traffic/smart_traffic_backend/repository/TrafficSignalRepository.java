package com.traffic.smart_traffic_backend.repository;

import com.traffic.smart_traffic_backend.model.TrafficSignal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrafficSignalRepository
        extends JpaRepository<TrafficSignal, Long> {
}