package com.traffic.smart_traffic_backend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader =
                request.getHeader("Authorization");

        if (authHeader != null &&
                authHeader.startsWith("Bearer ")) {

            String token =
                    authHeader.substring(7);

try {

    System.out.println("TOKEN RECEIVED:");
    System.out.println(token);

    String email =
            jwtUtil.extractUsername(token);

    System.out.println("EMAIL FROM TOKEN:");
    System.out.println(email);

    UsernamePasswordAuthenticationToken auth =
            new UsernamePasswordAuthenticationToken(
                    email,
                    null,
                    Collections.emptyList()
            );

    auth.setDetails(
            new WebAuthenticationDetailsSource()
                    .buildDetails(request)
    );

    SecurityContextHolder
            .getContext()
            .setAuthentication(auth);

} catch (Exception e) {

    System.out.println("JWT ERROR:");
    e.printStackTrace();
}

        }

        filterChain.doFilter(request, response);
    }
}