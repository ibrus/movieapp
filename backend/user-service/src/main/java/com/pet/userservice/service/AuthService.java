package com.pet.userservice.service;

import com.pet.common.enums.Role;
import com.pet.common.event.UserRegisteredEvent;
import com.pet.userservice.dto.AuthRequest;
import com.pet.userservice.dto.AuthResponse;
import com.pet.userservice.dto.RegisterRequest;
import com.pet.userservice.entity.User;
import com.pet.userservice.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);
    private final KafkaTemplate<String, UserRegisteredEvent> kafkaTemplate;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER) // or Role.ADMIN etc.
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(user);

        //Send Kafka event
        UserRegisteredEvent event = UserRegisteredEvent.builder()
                .username(user.getUsername())
                .build();

        kafkaTemplate.send("user-registered", event)
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        log.info("✅ Kafka UserRegisteredEvent sent successfully: {}", result.getRecordMetadata());
                    } else {
                        log.error("❌ Kafka UserRegisteredEvent failed to send", ex);
                    }
                });

        return new AuthResponse(token);
    }

    public AuthResponse login(AuthRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }
}
