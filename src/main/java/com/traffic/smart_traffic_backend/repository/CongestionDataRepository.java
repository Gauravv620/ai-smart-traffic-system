package com.traffic.smart_traffic_backend.repository;

import com.traffic.smart_traffic_backend.model.CongestionData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CongestionDataRepository
        extends JpaRepository<CongestionData, Long> {
}