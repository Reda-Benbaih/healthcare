package org.example.healthcare.DTO.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@Builder
public class PatientRequestDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private Long phone;
    private LocalDate BirthDay;
}
