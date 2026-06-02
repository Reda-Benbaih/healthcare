package org.example.healthcare.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "doctor")
@PrimaryKeyJoinColumn(name = "id")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@lombok.experimental.SuperBuilder
public class Doctor extends User {

    private String name;
    private String speciality;
    private Long phone;

    @OneToMany(mappedBy = "doctor" ,cascade = CascadeType.ALL)
    private List<Appointment> appointmentList;
}
