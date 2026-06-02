package org.example.healthcare.repositories;

import org.example.healthcare.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Integer> {
    Page<Patient> findByFirstNameContainingIgnoreCase(String name,Pageable pageable);
    Page<Patient> findByBirthDay()


}
