package com.traffic.smart_traffic_backend.service;

import com.traffic.smart_traffic_backend.model.CongestionData;
import com.traffic.smart_traffic_backend.model.EmergencyVehicle;
import com.traffic.smart_traffic_backend.model.TrafficSignal;
import com.traffic.smart_traffic_backend.repository.CongestionDataRepository;
import com.traffic.smart_traffic_backend.repository.EmergencyVehicleRepository;
import com.traffic.smart_traffic_backend.repository.TrafficSignalRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TrafficSimulationService {

    private final TrafficSignalRepository signalRepository;
    private final CongestionDataRepository congestionRepository;
    private final EmergencyVehicleRepository emergencyRepository;
    private final TrafficNotificationService notificationService;
    private final Random random = new Random();

    // Node coordinates map for pathfinding lookup
    private static final Map<String, double[]> NODE_COORDINATES = Map.ofEntries(
            Map.entry("Shivajinagar", new double[]{18.5308, 73.8475}),
            Map.entry("FC Road", new double[]{18.5220, 73.8550}),
            Map.entry("JM Road", new double[]{18.5255, 73.8500}),
            Map.entry("Camp", new double[]{18.5018, 73.8636}),
            Map.entry("Swargate", new double[]{18.4974, 73.8501}),
            Map.entry("Pune Station", new double[]{18.5289, 73.8742}),
            Map.entry("Deccan", new double[]{18.5167, 73.8562}),
            Map.entry("Aundh", new double[]{18.5590, 73.8070}),
            Map.entry("Baner", new double[]{18.5679, 73.7797}),
            Map.entry("Kothrud", new double[]{18.5074, 73.8077}),
            Map.entry("Hadapsar", new double[]{18.5089, 73.9260}),
            Map.entry("Wakad", new double[]{18.5975, 73.7898}),
            Map.entry("Hinjewadi", new double[]{18.5912, 73.7389}),
            Map.entry("Nigdi", new double[]{18.6513, 73.7706}),
            Map.entry("Pimpri", new double[]{18.6298, 73.7997}),
            Map.entry("Chinchwad", new double[]{18.6270, 73.7820}),
            Map.entry("Yerawada", new double[]{18.5510, 73.8950}),
            Map.entry("Kharadi", new double[]{18.5519, 73.9476}),
            Map.entry("Viman Nagar", new double[]{18.5679, 73.9143}),
            Map.entry("Magarpatta", new double[]{18.5150, 73.9250}),
            Map.entry("Warje", new double[]{18.4897, 73.7980}),
            Map.entry("Karve Nagar", new double[]{18.4890, 73.8210}),
            Map.entry("Bibwewadi", new double[]{18.4768, 73.8660}),
            Map.entry("Dhankawadi", new double[]{18.4630, 73.8520}),
            Map.entry("Sinhagad Road", new double[]{18.4700, 73.8200}),
            Map.entry("Market Yard", new double[]{18.4870, 73.8690}),
            Map.entry("Kondhwa", new double[]{18.4730, 73.8940}),
            Map.entry("Wanowrie", new double[]{18.4980, 73.8970}),
            Map.entry("Fatima Nagar", new double[]{18.5070, 73.8990}),
            Map.entry("Pashan", new double[]{18.5350, 73.7980}),
            Map.entry("Bavdhan", new double[]{18.5200, 73.7800}),
            Map.entry("Balewadi", new double[]{18.5760, 73.7890}),
            Map.entry("University Circle", new double[]{18.5390, 73.8300}),
            Map.entry("Model Colony", new double[]{18.5340, 73.8370}),
            Map.entry("Nal Stop", new double[]{18.5130, 73.8330}),
            Map.entry("Dandekar Bridge", new double[]{18.5030, 73.8420}),
            Map.entry("Alka Talkies", new double[]{18.5150, 73.8490}),
            Map.entry("Tilak Road", new double[]{18.5080, 73.8490}),
            Map.entry("RTO", new double[]{18.5310, 73.8680}),
            Map.entry("Bund Garden", new double[]{18.5390, 73.8820}),
            Map.entry("Sangamwadi", new double[]{18.5440, 73.8680}),
            Map.entry("Koregaon Park", new double[]{18.5380, 73.8920}),
            Map.entry("Mundhwa", new double[]{18.5330, 73.9200}),
            Map.entry("Phursungi", new double[]{18.4790, 73.9620}),
            Map.entry("Bhosari", new double[]{18.6210, 73.8450}),
            Map.entry("Alandi Road", new double[]{18.6000, 73.8680}),
            Map.entry("Dighi", new double[]{18.6080, 73.8600}),
            Map.entry("Lohegaon", new double[]{18.5910, 73.9100}),
            Map.entry("Ruby Hall Clinic", new double[]{18.5362, 73.8767}),
            Map.entry("Sassoon Hospital", new double[]{18.5284, 73.8747}),
            Map.entry("Jehangir Hospital", new double[]{18.5322, 73.8788}),
            Map.entry("KEM Hospital", new double[]{18.5015, 73.8630}),
            Map.entry("City Hospital", new double[]{18.5165, 73.8785})
    );

    // City road connections graph
    private static final Map<String, List<String>> ROAD_GRAPH = Map.ofEntries(
            Map.entry("Hinjewadi", List.of("Wakad")),
            Map.entry("Wakad", List.of("Hinjewadi", "Baner")),
            Map.entry("Baner", List.of("Wakad", "Aundh", "Balewadi")),
            Map.entry("Balewadi", List.of("Baner")),
            Map.entry("Aundh", List.of("Baner", "University Circle")),
            Map.entry("University Circle", List.of("Aundh", "Model Colony", "Pashan")),
            Map.entry("Pashan", List.of("University Circle", "Bavdhan")),
            Map.entry("Bavdhan", List.of("Pashan")),
            Map.entry("Model Colony", List.of("University Circle", "Shivajinagar")),
            Map.entry("Shivajinagar", List.of("Model Colony", "FC Road", "JM Road", "Deccan")),
            Map.entry("FC Road", List.of("Shivajinagar", "Deccan")),
            Map.entry("JM Road", List.of("Shivajinagar", "Deccan")),
            Map.entry("Deccan", List.of("Shivajinagar", "FC Road", "JM Road", "Karve Road", "Nal Stop", "Alka Talkies", "City Hospital")),
            Map.entry("Karve Road", List.of("Deccan", "Kothrud")),
            Map.entry("Kothrud", List.of("Karve Road")),
            Map.entry("Warje", List.of("Karve Nagar")),
            Map.entry("Karve Nagar", List.of("Warje", "Nal Stop")),
            Map.entry("Nal Stop", List.of("Karve Nagar", "Deccan")),
            Map.entry("Sinhagad Road", List.of("Dandekar Bridge")),
            Map.entry("Dandekar Bridge", List.of("Sinhagad Road", "Alka Talkies")),
            Map.entry("Alka Talkies", List.of("Deccan", "Dandekar Bridge", "Tilak Road", "Camp")),
            Map.entry("Tilak Road", List.of("Alka Talkies", "Swargate")),
            Map.entry("Swargate", List.of("Tilak Road", "Bibwewadi", "Market Yard", "Camp", "KEM Hospital")),
            Map.entry("Dhankawadi", List.of("Bibwewadi")),
            Map.entry("Bibwewadi", List.of("Dhankawadi", "Swargate", "Market Yard")),
            Map.entry("Market Yard", List.of("Swargate", "Bibwewadi")),
            Map.entry("Camp", List.of("Alka Talkies", "Swargate", "Fatima Nagar", "RTO", "KEM Hospital", "City Hospital")),
            Map.entry("Fatima Nagar", List.of("Camp", "Hadapsar")),
            Map.entry("Hadapsar", List.of("Fatima Nagar", "Phursungi", "Magarpatta", "Wanowrie")),
            Map.entry("Phursungi", List.of("Hadapsar")),
            Map.entry("Wanowrie", List.of("Hadapsar", "Kondhwa")),
            Map.entry("Kondhwa", List.of("Wanowrie")),
            Map.entry("Magarpatta", List.of("Hadapsar", "Mundhwa")),
            Map.entry("Mundhwa", List.of("Magarpatta", "Koregaon Park")),
            Map.entry("Koregaon Park", List.of("Mundhwa", "Bund Garden")),
            Map.entry("Bund Garden", List.of("Koregaon Park", "Pune Station", "Ruby Hall Clinic")),
            Map.entry("Pune Station", List.of("Bund Garden", "RTO", "Ruby Hall Clinic", "Jehangir Hospital", "Sassoon Hospital")),
            Map.entry("RTO", List.of("Pune Station", "Camp", "Sangamwadi", "Sassoon Hospital")),
            Map.entry("Sangamwadi", List.of("RTO", "Yerawada")),
            Map.entry("Yerawada", List.of("Sangamwadi", "Viman Nagar", "Alandi Road")),
            Map.entry("Viman Nagar", List.of("Yerawada", "Kharadi")),
            Map.entry("Kharadi", List.of("Viman Nagar", "Mundhwa")),
            Map.entry("Alandi Road", List.of("Yerawada", "Dighi")),
            Map.entry("Dighi", List.of("Alandi Road", "Lohegaon", "Bhosari")),
            Map.entry("Lohegaon", List.of("Dighi")),
            Map.entry("Bhosari", List.of("Dighi", "Pimpri")),
            Map.entry("Pimpri", List.of("Bhosari", "Chinchwad")),
            Map.entry("Chinchwad", List.of("Pimpri", "Nigdi")),
            Map.entry("Nigdi", List.of("Chinchwad")),
            // Hospital connections (bidirectional)
            Map.entry("Ruby Hall Clinic", List.of("Pune Station", "Bund Garden")),
            Map.entry("Jehangir Hospital", List.of("Pune Station")),
            Map.entry("Sassoon Hospital", List.of("Pune Station", "RTO")),
            Map.entry("KEM Hospital", List.of("Camp", "Swargate")),
            Map.entry("City Hospital", List.of("Camp", "Deccan"))
    );

    public TrafficSimulationService(
            TrafficSignalRepository signalRepository,
            CongestionDataRepository congestionRepository,
            EmergencyVehicleRepository emergencyRepository,
            TrafficNotificationService notificationService) {

        this.signalRepository = signalRepository;
        this.congestionRepository = congestionRepository;
        this.emergencyRepository = emergencyRepository;
        this.notificationService = notificationService;
    }

    // Breadth-First Search unweighted shortest path finder
    private List<String> findShortestPath(String start, String target) {
        if (start == null || target == null) return List.of();
        String cleanedStart = start.trim();
        String cleanedTarget = target.trim();

        if (cleanedStart.equals(cleanedTarget)) {
            return List.of(cleanedStart);
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Map<String, String> parentMap = new HashMap<>();

        queue.add(cleanedStart);
        visited.add(cleanedStart);

        boolean found = false;
        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current.equals(cleanedTarget)) {
                found = true;
                break;
            }

            List<String> neighbors = ROAD_GRAPH.getOrDefault(current, List.of());
            for (String neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        if (!found) return List.of();

        List<String> path = new ArrayList<>();
        String curr = cleanedTarget;
        while (curr != null) {
            path.add(0, curr);
            curr = parentMap.get(curr);
        }
        return path;
    }

    @Scheduled(fixedRate = 3000)
    public void runSimulationStep() {
        // 1. Fetch active emergency vehicles
        List<EmergencyVehicle> allVehicles = emergencyRepository.findAll();
        Set<String> signalsToGreen = new HashSet<>();

        // 2. Simulate Emergency Vehicle Movements along graph connections
        for (EmergencyVehicle vehicle : allVehicles) {
            if (vehicle.isRouteCleared()) {
                String startNode = vehicle.getLocation();
                if (startNode != null) {
                    startNode = startNode.trim();
                }
                // If startNode contains "En Route" or is not in the ROAD_GRAPH, resolve startNode as the closest junction node to current coordinates.
                if (startNode == null || startNode.contains("En Route") || !ROAD_GRAPH.containsKey(startNode)) {
                    startNode = findClosestNodeName(vehicle.getLatitude(), vehicle.getLongitude());
                }

                List<String> path = findShortestPath(startNode, vehicle.getDestination());
                if (!path.isEmpty() && vehicle.getLatitude() != null && vehicle.getLongitude() != null) {
                    // Find node in path closest to current vehicle position
                    int closestIdx = 0;
                    double minDist = Double.MAX_VALUE;
                    for (int j = 0; j < path.size(); j++) {
                        double[] nodeCoords = NODE_COORDINATES.get(path.get(j));
                        if (nodeCoords != null) {
                            double d = Math.sqrt(
                                    Math.pow(nodeCoords[0] - vehicle.getLatitude(), 2) +
                                    Math.pow(nodeCoords[1] - vehicle.getLongitude(), 2)
                            );
                            if (d < minDist) {
                                minDist = d;
                                closestIdx = j;
                            }
                        }
                    }

                    // Target the next node along the path
                    int targetIdx = Math.min(path.size() - 1, closestIdx + 1);
                    String targetNode = path.get(targetIdx);
                    double[] targetCoords = NODE_COORDINATES.get(targetNode);

                    if (targetCoords != null) {
                        double distanceToTarget = Math.sqrt(
                                Math.pow(targetCoords[0] - vehicle.getLatitude(), 2) +
                                Math.pow(targetCoords[1] - vehicle.getLongitude(), 2)
                        );

                        // If very close to the target node, check if it's the final hospital destination
                        if (distanceToTarget < 0.0012 && targetIdx == path.size() - 1) {
                            // Arrived at hospital!
                            vehicle.setRouteCleared(false);
                            vehicle.setLocation("Hospital Reached");
                            vehicle.setLatitude(targetCoords[0]);
                            vehicle.setLongitude(targetCoords[1]);
                            emergencyRepository.save(vehicle);

                            notificationService.sendNotification("🎉 Emergency Alert: Vehicle " 
                                    + vehicle.getVehicleNumber() + " arrived at " + vehicle.getDestination() 
                                    + " successfully via route corridor.");
                        } else {
                            // Move step closer to the target node in path (18% step for smooth jumps)
                            double nextLat = vehicle.getLatitude() + (targetCoords[0] - vehicle.getLatitude()) * 0.18;
                            double nextLng = vehicle.getLongitude() + (targetCoords[1] - vehicle.getLongitude()) * 0.18;
                            vehicle.setLatitude(nextLat);
                            vehicle.setLongitude(nextLng);
                            vehicle.setLocation("En Route to " + targetNode);
                            emergencyRepository.save(vehicle);

                            // Sequentially force green light on the junction we are traveling towards
                            signalsToGreen.add(targetNode);

                            notificationService.sendNotification("🚑 Corridor Alert: Vehicle " 
                                    + vehicle.getVehicleNumber() + " routing towards " + targetNode 
                                    + " (Next Target: " + vehicle.getDestination() + ")");
                        }
                    }
                }
            }
        }

        // 3. Simulate Traffic Signals (cycle normal ones, force green for nodes in active paths)
        List<TrafficSignal> signals = signalRepository.findAll();
        for (TrafficSignal signal : signals) {
            // Fluctuate vehicleCount
            int delta = random.nextInt(11) - 5; // -5 to +5
            int currentCount = signal.getVehicleCount() != null ? signal.getVehicleCount() : 100;
            int count = Math.max(10, Math.min(250, currentCount + delta));
            signal.setVehicleCount(count);

            // Recalculate congestion level
            double level = Math.min(100.0, count * 0.4);
            signal.setCongestionLevel(level);

            // Short name lookup to see if this junction lies ahead on an active corridor path
            String shortJunctionName = signal.getJunctionName().replace(" Junction", "").replace(" Signal", "").trim();

            if (signalsToGreen.contains(shortJunctionName)) {
                signal.setStatus("GREEN");
            } else if (signal.isManualOverride()) {
                // Keep the manual status, do not cycle
            } else {
                // Toggles states RED -> GREEN -> YELLOW -> RED for normal intersections
                if (random.nextDouble() < 0.25) { 
                    String currentStatus = signal.getStatus();
                    if ("GREEN".equalsIgnoreCase(currentStatus)) {
                        signal.setStatus("YELLOW");
                    } else if ("YELLOW".equalsIgnoreCase(currentStatus)) {
                        signal.setStatus("RED");
                    } else {
                        signal.setStatus("GREEN");
                    }
                }
            }
            signalRepository.save(signal);
        }

        // 4. Simulate Congestion Data
        List<CongestionData> congestionDataList = congestionRepository.findAll();
        for (CongestionData data : congestionDataList) {
            int delta = random.nextInt(31) - 15; // -15 to +15
            int currentCount = data.getVehicleCount() != null ? data.getVehicleCount() : 200;
            int count = Math.max(30, Math.min(500, currentCount + delta));
            data.setVehicleCount(count);

            double score = Math.min(100.0, count * 0.22);
            data.setCongestionScore(score);

            if (score > 80.0) {
                data.setCongestionLevel("HIGH");
            } else if (score > 50.0) {
                data.setCongestionLevel("MEDIUM");
            } else {
                data.setCongestionLevel("LOW");
            }
            congestionRepository.save(data);
        }

        // 5. Send generic refresh notification to sync UI
        notificationService.sendNotification("refresh");
    }

    // Helper to find closest junction node based on current coordinates
    private String findClosestNodeName(Double lat, Double lng) {
        if (lat == null || lng == null) return "Shivajinagar";
        String closestNode = "Shivajinagar";
        double minDist = Double.MAX_VALUE;

        for (Map.Entry<String, double[]> entry : NODE_COORDINATES.entrySet()) {
            // Exclude hospitals as start nodes
            if (entry.getKey().contains("Hospital") || entry.getKey().contains("Clinic")) {
                continue;
            }
            double[] coords = entry.getValue();
            double dist = Math.sqrt(Math.pow(coords[0] - lat, 2) + Math.pow(coords[1] - lng, 2));
            if (dist < minDist) {
                minDist = dist;
                closestNode = entry.getKey();
            }
        }
        return closestNode;
    }
}
