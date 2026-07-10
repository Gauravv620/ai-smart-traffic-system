package com.traffic.smart_traffic_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SmartTrafficBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartTrafficBackendApplication.class, args);
	}

}
