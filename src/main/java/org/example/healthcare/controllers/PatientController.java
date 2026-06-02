package org.example.healthcare.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.healthcare.DTO.request.PatientRequestDTO;
import org.example.healthcare.DTO.response.PatientResponseDTO;
import org.example.healthcare.services.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<PatientResponseDTO> addPatient(@Valid @RequestBody PatientRequestDTO patientRequestDTO){
        PatientResponseDTO response = patientService.addPatient(patientRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletePatient(@PathVariable Integer id){
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<PatientResponseDTO> showPatientById(@PathVariable Integer id){
        return ResponseEntity.ok(patientService.showPatientById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR') or @patientService.isPatientOwner(#id, authentication.name)")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable Integer id, @Valid @RequestBody PatientRequestDTO patientRequestDTO){
        PatientResponseDTO response = patientService.updatePatient(id, patientRequestDTO);
        return ResponseEntity.ok(response);
    }
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<Page<PatientResponseDTO>> showAllPatients(
            @PageableDefault(sort = "lastName", direction = org.springframework.data.domain.Sort.Direction.ASC)
            Pageable pageable){
        return ResponseEntity.ok(patientService.getPatients(pageable));
    }
    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<Page<PatientResponseDTO>> searchPatients(@RequestParam String name, Pageable pageable){
        return ResponseEntity.ok(patientService.searchPatients(name,pageable));
    }
}