package org.example.healthcare.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.healthcare.DTO.request.DoctorRequestDTO;
import org.example.healthcare.DTO.response.DoctorResponseDTO;
import org.example.healthcare.services.DoctorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DoctorResponseDTO> addDoctor(@Valid @RequestBody DoctorRequestDTO doctorRequestDTO){
        DoctorResponseDTO response = doctorService.addDoctor(doctorRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Integer id){
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @doctorService.isDoctorOwner(#id, authentication.name)")
    public ResponseEntity<DoctorResponseDTO> updateDoctor(@PathVariable Integer id, @Valid @RequestBody DoctorRequestDTO doctorRequestDTO){
        DoctorResponseDTO response = doctorService.updateDoctor(id, doctorRequestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'PATIENT')")
    public ResponseEntity<Page<DoctorResponseDTO>> showDoctors(Pageable pageable){
        return ResponseEntity.ok(doctorService.getDoctors(pageable));
    }

    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'PATIENT')")
    public ResponseEntity<Page<DoctorResponseDTO>> searchDoctors(@RequestParam String speciality, Pageable pageable){
        return ResponseEntity.ok(doctorService.searchDoctors(speciality, pageable));
    }
}