package org.example.healthcare.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "doctor")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Builder
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String speciality;
    private String email;
    private Long phone;

    @OneToMany(mappedBy = "doctor" ,cascade = CascadeType.ALL)
    private List<Appointment> appointmentList;
}
