package org.example.healthcare.DTO.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@Builder
public class MedicalFileRequestDTO {
    private String diagnosis;
    private String observation;
    private LocalDate creationDate;
    private Integer patientId;
}
