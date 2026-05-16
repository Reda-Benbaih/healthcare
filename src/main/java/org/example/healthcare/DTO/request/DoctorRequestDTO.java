package org.example.healthcare.DTO.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class DoctorRequestDTO {
    @NotBlank(message = " le nom necessaire")
    private String name;
    @NotBlank(message = "la specialite necessaire")
    private String speciality;
    @NotBlank(message = "email necessaire")
    @Email(message = "email invalid")
    private String email;
    @NotNull(message = "telephone necessaire")
    private Long phone;
}
