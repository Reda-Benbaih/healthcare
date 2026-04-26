package org.example.healthcare.DTO.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@Builder
public class MedicalFileResonseDTO {
    private Integer id;
    private String diagnosis;
    private String observation;
    private LocalDate creationDate;
}
