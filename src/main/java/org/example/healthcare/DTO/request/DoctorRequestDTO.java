package org.example.healthcare.DTO.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class DoctorRequestDTO {
    private String name;
    private String speciality;
    private String email;
    private Long phone;
}
