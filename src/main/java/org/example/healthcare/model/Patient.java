package org.example.healthcare.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "patient")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Builder
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;
    private String lastName;
    private String email;
    private Long phone;
    private LocalDate birthDay;

    @OneToMany(mappedBy = "patient",cascade = CascadeType.PERSIST)
    private List<Appointment> appointment;

    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL)
    private MedicalFile medicalFile;

}
