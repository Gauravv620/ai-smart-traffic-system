# 🚦 AI Smart Traffic Management System

An AI-powered Smart Traffic Management System that enables real-time monitoring of traffic congestion, emergency vehicles, and traffic signals while providing intelligent route visualization and green corridor management for emergency response.

---

## 📌 Features

- 🚑 Real-time Emergency Vehicle Tracking
- 🚦 Smart Traffic Signal Monitoring
- 📍 Interactive Map using Leaflet.js
- 🛣️ Green Corridor Visualization
- 📈 Live Traffic Congestion Analysis
- 🏥 Hospital Route Navigation
- 📊 Real-time Dashboard
- 🔐 JWT Authentication
- 🔄 WebSocket Support for Live Updates
- 💾 H2 Database Integration
- 🌐 REST APIs using Spring Boot

---

## 🖥️ Tech Stack

### Backend
- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- JWT Authentication
- WebSocket (STOMP + SockJS)
- Maven

### Frontend
- HTML5
- CSS3
- JavaScript
- Leaflet.js
- Chart.js

### Database
- Data.sql

### Tools
- Git
- GitHub
- VS Code
- Render (Deployment)

---

## 📂 Project Structure

```
smart-traffic-backend
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.traffic.smart_traffic_backend
│   │   │       ├── config
│   │   │       ├── controller
│   │   │       ├── dto
│   │   │       ├── model
│   │   │       ├── repository
│   │   │       ├── security
│   │   │       ├── service
│   │   │       └── SmartTrafficBackendApplication.java
│   │   │
│   │   └── resources
│   │       ├── static
│   │       │   ├── dashboard.html
│   │       │   └── map.html
│   │       ├── templates
│   │       ├── application.yaml
│   │       └── data.sql
│
├── Dockerfile
├── pom.xml
└── README.md
```

---

## 🚀 Getting Started

### Clone Repository

```bash
git clone https://github.com/yourusername/ai-smart-traffic-system.git
```

```bash
cd smart-traffic-backend
```

---

## ▶️ Run the Project

### Using Maven Wrapper

Windows

```bash
mvnw.cmd spring-boot:run
```

Linux/Mac

```bash
./mvnw spring-boot:run
```

---

## Application URLs

Dashboard

```
http://localhost:8080/dashboard.html
```

Traffic Map

```
http://localhost:8080/map.html
```

---

## REST APIs

### Traffic Signals

```
GET /api/signals
```

### Emergency Vehicles

```
GET /api/emergency
```

### Congestion Data

```
GET /api/congestion
```

### Authentication

```
POST /api/auth/login
```

```
POST /api/auth/register
```

---

## Dashboard Features

- Live Emergency Vehicle Count
- Traffic Signal Status
- Congestion Monitoring
- Emergency Route Visualization
- Green Corridor Tracking
- Hospital Destination Monitoring
- Live Statistics
- Digital Clock

---

## Map Features

- Interactive Leaflet Map
- Animated Ambulance Movement
- Green Corridor Route
- Hospital Locations
- Traffic Signal Markers
- Congestion Zones
- Live ETA
- Route Completion Animation

---

## Database

The application uses **H2 Database** for development.

Console

```
http://localhost:8080/h2-console
```

Driver

```
org.h2.Driver
```

---

## Deployment

The project can be deployed on

- Render
- Railway
- Docker
- AWS
- Azure

---

## Screenshots

### Dashboard

(Add Screenshot)

### Live Traffic Map

(Add Screenshot)

---

## Future Improvements

- AI-based Route Optimization
- Google Maps API Integration
- GPS Live Tracking
- Accident Detection
- Traffic Prediction using Machine Learning
- Role-based Authentication
- SMS & Email Alerts
- Mobile Application

---

## Author

**Gaurav Singh Rajput**

LinkedIn:
https://www.linkedin.com/in/gaurav-kumar-4b1552170

GitHub:
https://github.com/Gaurav620

---

## License

This project is licensed under the MIT License.