package com.traffic.smart_traffic_backend.config;

import com.traffic.smart_traffic_backend.security.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http)
            throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/api/signals/**", "/api/congestion/**", "/api/emergency/**").permitAll()
                        .requestMatchers(
        "/",
        "/index.html",
        "/dashboard.html",
        "/map.html",
        "/favicon.ico",
        "/error",
        "/css/**",
        "/js/**",
        "/images/**",
        "/api/auth/**",
        "/api/signals/**",
        "/api/congestion/**",
        "/api/emergency/**",
        "/traffic-websocket/**",
        "/api/notify/**",
        "/h2-console/**"
).permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/signals/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/signals/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/emergency/**").authenticated()
                        .anyRequest()
                        .authenticated()
                )
                .headers(headers ->
                        headers.frameOptions(
                                frame -> frame.disable()
                        )
                );

        http.addFilterBefore(
                jwtFilter,
                UsernamePasswordAuthenticationFilter.class
        );

        return http.build();
    }
}