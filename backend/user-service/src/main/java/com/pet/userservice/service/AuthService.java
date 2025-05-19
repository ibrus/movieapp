package com.pet.userservice.service;

import com.pet.common.enums.Role;
import com.pet.common.event.UserRegisteredEvent;
import com.pet.common.security.JwtService;
import com.pet.userservice.dto.AuthRequest;
import com.pet.userservice.dto.AuthResponse;
import com.pet.userservice.dto.RegisterRequest;
import com.pet.userservice.kafka.publisher.UserRegisteredEventPublisher;
import com.pet.userservice.model.User;
import com.pet.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);
    private final UserRegisteredEventPublisher eventPublisher;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(user.getUsername(), List.of(user.getRole().name()));

        //Send Kafka event
        eventPublisher.publish(UserRegisteredEvent.builder()
                .username(user.getUsername())
                .build());

        return new AuthResponse(token);
    }

    public AuthResponse login(AuthRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(user.getUsername(), List.of(user.getRole().name()));
        return new AuthResponse(token);
    }
}
