package org.example.healthcare.DTO.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "password necessary")
    private String password;
}
