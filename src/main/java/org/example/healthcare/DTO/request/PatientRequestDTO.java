package org.example.healthcare.DTO.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@Builder
public class PatientRequestDTO {
    @NotBlank(message = "first name necessaire")
    private String firstName;
    @NotBlank(message = "last name necesssaire")
    private String lastName;
    @NotNull(message = "email necessaire")
    @Email(message = "email invalid")
    private String email;
    @NotNull(message = "telephone necessaire")
    private Long phone;
    @NotNull(message = "birth dat necessaire")
    @Past(message = "it should be in past")
    private LocalDate birthDay;
}
