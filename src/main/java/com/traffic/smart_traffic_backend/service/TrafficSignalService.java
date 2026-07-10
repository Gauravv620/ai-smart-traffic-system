package com.traffic.smart_traffic_backend.service;

import com.traffic.smart_traffic_backend.model.TrafficSignal;
import com.traffic.smart_traffic_backend.repository.TrafficSignalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrafficSignalService {

    private final TrafficSignalRepository repository;
    private final TrafficNotificationService notificationService;

    public TrafficSignalService(
            TrafficSignalRepository repository,
            TrafficNotificationService notificationService) {

        this.repository = repository;
        this.notificationService = notificationService;
    }

    public TrafficSignal save(TrafficSignal signal) {
        return repository.save(signal);
    }

    public List<TrafficSignal> getAll() {
        return repository.findAll();
    }

    public TrafficSignal overrideStatus(Long id, String status) {
        TrafficSignal signal = repository.findById(id).orElse(null);
        if (signal != null) {
            if ("RELEASE".equalsIgnoreCase(status)) {
                signal.setManualOverride(false);
                TrafficSignal saved = repository.save(signal);
                notificationService.sendNotification("🚦 Manual Override Released: " + signal.getJunctionName() + " is now back in automatic mode.");
                return saved;
            } else {
                signal.setStatus(status.toUpperCase());
                signal.setManualOverride(true);
                TrafficSignal saved = repository.save(signal);
                notificationService.sendNotification("🚦 Manual Override: " + signal.getJunctionName() + " has been manually set to " + status.toUpperCase() + ".");
                return saved;
            }
        }
        return null;
    }
}