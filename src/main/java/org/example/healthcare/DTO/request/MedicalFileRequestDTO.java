package org.example.healthcare.DTO.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@Builder
public class MedicalFileRequestDTO {
    @NotBlank(message = "diagnostic necessaire")
    private String diagnosis;
    @NotBlank(message = "observation necessaire")
    private String observation;
    @NotNull(message = "la date de creation necessair")
    private LocalDate creationDate;
    @NotNull(message = "id patient necessaire")
    @Positive(message = "id patient necessaire")
    private Integer patientId;
}
