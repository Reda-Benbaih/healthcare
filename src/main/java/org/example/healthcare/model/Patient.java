package org.example.healthcare.model;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "patient")
@PrimaryKeyJoinColumn(name = "id")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@lombok.experimental.SuperBuilder
public class Patient extends User{

    private String firstName;
    private String lastName;
    private Long phone;
    private LocalDate birthDay;

    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL)
    private List<Appointment> appointment;

    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL)
    private MedicalFile medicalFile;

}
