package org.example.healthcare.DTO.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@Builder
public class AppointmentRequestDTO {
    private LocalDate appointmentDate;
    private String status;
    private Integer doctorId;
    private Integer patientId;
}

