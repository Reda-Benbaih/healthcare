package org.example.healthcare.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.healthcare.DTO.request.PatientRequestDTO;
import org.example.healthcare.DTO.response.PatientResponseDTO;
import org.example.healthcare.services.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<PatientResponseDTO> addPatient(@Valid @RequestBody PatientRequestDTO patientRequestDTO){
        PatientResponseDTO response = patientService.addPatient(patientRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> showAllPatient(){
        return ResponseEntity.ok(patientService.showAllPatients());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Integer id){
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> showPatientById(@PathVariable Integer id){
        return ResponseEntity.ok(patientService.showPatientById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable Integer id, @Valid @RequestBody PatientRequestDTO patientRequestDTO){
        PatientResponseDTO response = patientService.updatePatient(id, patientRequestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<PatientResponseDTO>> showAllPatient(Pageable pageable){
        return ResponseEntity.ok(patientService.getPatients(pageable));
    }
}