package com.traffic.smart_traffic_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "traffic_signals")
public class TrafficSignal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String junctionName;

    private String status;

    private Integer vehicleCount;

    private Double congestionLevel;

    private Double latitude;

    private Double longitude;

    private boolean manualOverride = false;

    public TrafficSignal() {
    }

    public Long getId() {
        return id;
    }

    public String getJunctionName() {
        return junctionName;
    }

    public void setJunctionName(String junctionName) {
        this.junctionName = junctionName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getVehicleCount() {
        return vehicleCount;
    }

    public void setVehicleCount(Integer vehicleCount) {
        this.vehicleCount = vehicleCount;
    }

    public Double getCongestionLevel() {
        return congestionLevel;
    }

    public void setCongestionLevel(Double congestionLevel) {
        this.congestionLevel = congestionLevel;
    }

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

    public boolean isManualOverride() {
        return manualOverride;
    }

    public void setManualOverride(boolean manualOverride) {
        this.manualOverride = manualOverride;
    }
}