package org.example.healthcare.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.healthcare.DTO.user.AuthResponse;
import org.example.healthcare.DTO.user.LoginRequest;
import org.example.healthcare.DTO.user.RegisterRequest;
import org.example.healthcare.services.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public AuthResponse register(@Valid @RequestBody RegisterRequest registerRequest){
        return authenticationService.register(registerRequest);
    }

    @PostMapping("login")
    public AuthResponse login(@Valid @RequestBody LoginRequest loginRequest){
        return authenticationService.login(loginRequest);
    }
}
