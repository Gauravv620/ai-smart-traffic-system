package com.traffic.smart_traffic_backend.service;

import com.traffic.smart_traffic_backend.model.EmergencyVehicle;
import com.traffic.smart_traffic_backend.model.TrafficSignal;
import com.traffic.smart_traffic_backend.repository.EmergencyVehicleRepository;
import com.traffic.smart_traffic_backend.repository.TrafficSignalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmergencyVehicleService {

    private final EmergencyVehicleRepository repository;
    private final TrafficSignalRepository signalRepository;
    private final TrafficNotificationService notificationService;

    private static final java.util.Map<String, double[]> START_COORDINATES = java.util.Map.ofEntries(
            java.util.Map.entry("Shivajinagar", new double[]{18.5308, 73.8475}),
            java.util.Map.entry("FC Road", new double[]{18.5220, 73.8550}),
            java.util.Map.entry("JM Road", new double[]{18.5255, 73.8500}),
            java.util.Map.entry("Camp", new double[]{18.5018, 73.8636}),
            java.util.Map.entry("Swargate", new double[]{18.4974, 73.8501}),
            java.util.Map.entry("Pune Station", new double[]{18.5289, 73.8742}),
            java.util.Map.entry("Deccan", new double[]{18.5167, 73.8562}),
            java.util.Map.entry("Aundh", new double[]{18.5590, 73.8070}),
            java.util.Map.entry("Baner", new double[]{18.5679, 73.7797}),
            java.util.Map.entry("Kothrud", new double[]{18.5074, 73.8077}),
            java.util.Map.entry("Hadapsar", new double[]{18.5089, 73.9260}),
            java.util.Map.entry("Wakad", new double[]{18.5975, 73.7898}),
            java.util.Map.entry("Hinjewadi", new double[]{18.5912, 73.7389}),
            java.util.Map.entry("Nigdi", new double[]{18.6513, 73.7706}),
            java.util.Map.entry("Pimpri", new double[]{18.6298, 73.7997}),
            java.util.Map.entry("Chinchwad", new double[]{18.6270, 73.7820}),
            java.util.Map.entry("Yerawada", new double[]{18.5510, 73.8950}),
            java.util.Map.entry("Kharadi", new double[]{18.5519, 73.9476}),
            java.util.Map.entry("Viman Nagar", new double[]{18.5679, 73.9143}),
            java.util.Map.entry("Magarpatta", new double[]{18.5150, 73.9250}),
            java.util.Map.entry("Warje", new double[]{18.4897, 73.7980}),
            java.util.Map.entry("Karve Nagar", new double[]{18.4890, 73.8210}),
            java.util.Map.entry("Bibwewadi", new double[]{18.4768, 73.8660}),
            java.util.Map.entry("Dhankawadi", new double[]{18.4630, 73.8520}),
            java.util.Map.entry("Sinhagad Road", new double[]{18.4700, 73.8200})
    );

    public EmergencyVehicleService(
            EmergencyVehicleRepository emergencyRepository,
            TrafficSignalRepository signalRepository,
            TrafficNotificationService notificationService) {

        this.repository = emergencyRepository;
        this.signalRepository = signalRepository;
        this.notificationService = notificationService;
    }

    public EmergencyVehicle save(EmergencyVehicle vehicle) {
        // Resolve initial coordinates if missing
        if ((vehicle.getLatitude() == null || vehicle.getLongitude() == null) && vehicle.getLocation() != null) {
            String locName = vehicle.getLocation().trim();
            // Try to find in database signals first
            TrafficSignal match = signalRepository.findAll().stream()
                    .filter(s -> s.getJunctionName().replace(" Junction", "").replace(" Signal", "").trim().equalsIgnoreCase(locName))
                    .findFirst()
                    .orElse(null);
            if (match != null) {
                vehicle.setLatitude(match.getLatitude());
                vehicle.setLongitude(match.getLongitude());
            } else {
                double[] coords = START_COORDINATES.get(locName);
                if (coords != null) {
                    vehicle.setLatitude(coords[0]);
                    vehicle.setLongitude(coords[1]);
                } else {
                    // Fallback to central Pune
                    vehicle.setLatitude(18.5204);
                    vehicle.setLongitude(73.8567);
                }
            }
        }

        // Set route cleared to true for simulation to start
        if ("HIGH".equalsIgnoreCase(vehicle.getPriority())) {
            vehicle.setRouteCleared(true);
        }

        // Save emergency vehicle
        EmergencyVehicle savedVehicle = repository.save(vehicle);

        // Notify client
        if (savedVehicle.isRouteCleared()) {
            notificationService.sendNotification(
                    "🚑 Emergency Alert: Vehicle "
                            + savedVehicle.getVehicleNumber()
                            + " has been dispatched to "
                            + savedVehicle.getDestination()
                            + "! Green corridor activated."
            );
        }

        return savedVehicle;
    }

    public List<EmergencyVehicle> getAll() {
        return repository.findAll();
    }
}