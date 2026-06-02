package org.example.healthcare.services;

import lombok.RequiredArgsConstructor;
import org.example.healthcare.DTO.user.AuthResponse;
import org.example.healthcare.DTO.user.LoginRequest;
import org.example.healthcare.DTO.user.RegisterRequest;
import org.example.healthcare.model.Doctor;
import org.example.healthcare.model.Patient;
import org.example.healthcare.model.User;
import org.example.healthcare.model.UserRoles;
import org.example.healthcare.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest registerRequest){
        User user;

        if (registerRequest.getUserRoles() == UserRoles.DOCTOR) {
            Doctor doctor = new Doctor();
            doctor.setUsername(registerRequest.getUsername());
            doctor.setEmail(registerRequest.getEmail());
            doctor.setUserRoles(registerRequest.getUserRoles());
            doctor.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            // Vous pouvez initialiser les champs par défaut du docteur ici si nécessaire
            user = doctor;
        } else if (registerRequest.getUserRoles() == UserRoles.PATIENT) {
            Patient patient = new Patient();
            patient.setUsername(registerRequest.getUsername());
            patient.setEmail(registerRequest.getEmail());
            patient.setUserRoles(registerRequest.getUserRoles());
            patient.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            user = patient;
        } else {
            user = User.builder()
                    .username(registerRequest.getUsername())
                    .email(registerRequest.getEmail())
                    .userRoles(registerRequest.getUserRoles())
                    .password(passwordEncoder.encode(registerRequest.getPassword()))
                    .build();
        }

        userRepository.save(user);

        String token = jwtService.generateToken(user);
        return AuthResponse.builder().token(token).build();
    }

    public AuthResponse login(LoginRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        String token = jwtService.generateToken(user);

        return AuthResponse.builder().token(token).build();

    }




}
