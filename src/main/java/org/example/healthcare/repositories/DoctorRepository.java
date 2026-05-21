package org.example.healthcare.repositories;

import org.example.healthcare.model.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
    Page<Doctor> findBySpecialityContainingIgnoreCase(
            String name,
            Pageable pageable
    );
    
}
