package org.example.healthcare.services;

import lombok.RequiredArgsConstructor;
import org.example.healthcare.DTO.user.LoginRequest;
import org.example.healthcare.DTO.user.RegisterRequest;
import org.example.healthcare.Security.JwtService;
import org.example.healthcare.model.User;
import org.example.healthcare.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public String register(RegisterRequest request) {

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(
                        encoder.encode(request.getPassword())
                )
                .build();

        repository.save(user);

        return "User registered";
    }

    public String login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        return jwtService.generateToken(request.getEmail());
    }
}
