package org.example.healthcare.DTO.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@Builder
public class PatientRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private Long phone;
    private LocalDate birthDay;
}
