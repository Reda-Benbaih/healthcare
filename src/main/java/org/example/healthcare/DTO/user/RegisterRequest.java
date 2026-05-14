package org.example.healthcare.DTO.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "Username is required")
    private String username;

    @Email(message = "invalid email format")
    @NotBlank(message = "email is required")
    private String email;
    @Size(min = 4 ,message = "password shouldve at least 4 characters")
    private String password;
}
