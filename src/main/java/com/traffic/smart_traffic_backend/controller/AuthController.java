package com.traffic.smart_traffic_backend.controller;

import com.traffic.smart_traffic_backend.dto.LoginRequest;
import com.traffic.smart_traffic_backend.dto.RegisterRequest;
import com.traffic.smart_traffic_backend.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(
            @RequestBody RegisterRequest request) {

        return authService.register(request);
    }

    @PostMapping("/login")
    public String login(
            @RequestBody LoginRequest request) {

        return authService.login(request);
    }
}