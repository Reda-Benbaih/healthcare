package org.example.healthcare.repositories;

import org.example.healthcare.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {
    List<Appointment> findByPatientId(Integer id);

    List<Appointment> findByDoctorId(Integer id);
}
