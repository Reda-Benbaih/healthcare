package org.example.healthcare.DTO.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class DoctorResponseDTO {
    private Integer id;
    private String name;
    private String speciality;
    private String email;
    private Long phone;

}
