package org.example.healthcare.controllers;

import lombok.RequiredArgsConstructor;
import org.example.healthcare.DTO.request.PatientRequestDTO;
import org.example.healthcare.DTO.response.PatientResponseDTO;
import org.example.healthcare.services.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @PostMapping
    public PatientResponseDTO addPatient(@RequestBody PatientRequestDTO patientRequestDTO){
        return patientService.addPatient(patientRequestDTO);
    }

    @GetMapping
    public List<PatientResponseDTO> showAllPatient(){
        return patientService.showAllPatients();
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Integer id){
        patientService.deletePatient(id);
    }

    @GetMapping("/{id}")
    public PatientResponseDTO showPatientById(@PathVariable Integer id){
        return patientService.showPatientById(id);
    }

    @PutMapping("/{id}")
    public PatientResponseDTO updatePatient(@PathVariable Integer id,@RequestBody PatientRequestDTO patientRequestDTO){
        return patientService.updatePatient(id,patientRequestDTO);
    }

}
