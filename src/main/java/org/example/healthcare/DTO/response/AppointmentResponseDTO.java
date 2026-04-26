package org.example.healthcare.DTO.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@Builder
public class AppointmentResponseDTO {
    private Integer id;
    private LocalDate appointmentDate;
    private String status;
    private Integer doctorId;
    private Integer patientId;
}
