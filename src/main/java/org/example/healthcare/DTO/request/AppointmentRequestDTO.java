package org.example.healthcare.DTO.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@Builder
public class AppointmentRequestDTO {
    @NotNull(message = "la date necessaire")
    @Future(message = "must be in future")
    private LocalDate appointmentDate;
    @NotBlank(message = "le status necessaire")
    private String status;
    @NotNull(message = "id de doctor necessaire")
    @Positive(message = "positive number")
    private Integer doctorId;
    @NotNull(message = "id de patient necessaire")
    @Positive(message = "positive number")
    private Integer patientId;
}

