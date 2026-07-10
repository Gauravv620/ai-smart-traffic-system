package com.traffic.smart_traffic_backend.service;

import com.traffic.smart_traffic_backend.dto.LoginRequest;
import com.traffic.smart_traffic_backend.dto.RegisterRequest;
import com.traffic.smart_traffic_backend.model.User;
import com.traffic.smart_traffic_backend.repository.UserRepository;
import com.traffic.smart_traffic_backend.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public AuthService(
            UserRepository userRepository,
            BCryptPasswordEncoder encoder,
            JwtUtil jwtUtil) {

        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
    }

    public String register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return "Email already exists";
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(
                encoder.encode(request.getPassword())
        );
        user.setRole("ADMIN");

        userRepository.save(user);

        return "User Registered Successfully";
    }

    public String login(LoginRequest request) {

        System.out.println("================================");
        System.out.println("LOGIN REQUEST RECEIVED");
        System.out.println("EMAIL = " + request.getEmail());
        System.out.println("PASSWORD = " + request.getPassword());

        System.out.println("ALL USERS IN DATABASE:");
        System.out.println(userRepository.findAll());

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElse(null);

        System.out.println("FOUND USER = " + user);

        if (user == null) {
            return "User Not Found";
        }

        boolean passwordMatches =
                encoder.matches(
                        request.getPassword(),
                        user.getPassword());

        System.out.println(
                "PASSWORD MATCHES = " + passwordMatches);

        if (!passwordMatches) {
            return "Invalid Password";
        }

        String token =
                jwtUtil.generateToken(user.getEmail());

        System.out.println("JWT GENERATED");
        System.out.println("================================");

        return token;
    }
}