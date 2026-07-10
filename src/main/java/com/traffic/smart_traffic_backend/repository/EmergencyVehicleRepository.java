package com.traffic.smart_traffic_backend.repository;

import com.traffic.smart_traffic_backend.model.EmergencyVehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmergencyVehicleRepository
        extends JpaRepository<EmergencyVehicle, Long> {
}