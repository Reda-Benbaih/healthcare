package org.example.healthcare.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "medical_file")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Builder
public class MedicalFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String diagnosis;
    private String observation;
    private LocalDate creationDate;

    @OneToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;
}
