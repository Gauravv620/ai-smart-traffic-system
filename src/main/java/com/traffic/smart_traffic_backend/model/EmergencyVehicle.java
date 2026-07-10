package com.traffic.smart_traffic_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "EMERGENCY_VEHICLE")
public class EmergencyVehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vehicleNumber;

    private String type;

    private String location;

    private String destination;

    private String priority;


        private Double latitude;
private Double longitude;


public Double getLatitude() {
    return latitude;
}

public void setLatitude(Double latitude) {
    this.latitude = latitude;
}

public Double getLongitude() {
    return longitude;
}

public void setLongitude(Double longitude) {
    this.longitude = longitude;
}


    @Column(name = "ROUTE_CLEARED")
    private boolean routeCleared = false;

    public EmergencyVehicle() {
    }

    public Long getId() {
        return id;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public boolean isRouteCleared() {
        return routeCleared;
    }

    public void setRouteCleared(boolean routeCleared) {
        this.routeCleared = routeCleared;
    }
}




