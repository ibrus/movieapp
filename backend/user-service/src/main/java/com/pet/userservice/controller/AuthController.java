package com.pet.userservice.controller;

import com.pet.userservice.dto.AuthRequest;
import com.pet.userservice.dto.AuthResponse;
import com.pet.userservice.dto.RegisterRequest;
import com.pet.userservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return authService.login(request);
    }

    @GetMapping("/secure-hello")
    public String securedHello() {
        return "Hello from a protected endpoint!";
    }

}
