package org.example.healthcare.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.healthcare.DTO.request.MedicalFileRequestDTO;
import org.example.healthcare.DTO.response.MedicalFileResponseDTO;
import org.example.healthcare.services.MedicalFileService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medicalFile")
@RequiredArgsConstructor
public class MedicalFileController {
    private final MedicalFileService medicalFileService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<MedicalFileResponseDTO> addMedicalFile(@Valid @RequestBody MedicalFileRequestDTO medicalFileRequestDTO){
        MedicalFileResponseDTO response = medicalFileService.addMedicalFile(medicalFileRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR') or @medicalFileService.isMedicalFileOwner(#id, authentication.name)")
    public ResponseEntity<MedicalFileResponseDTO> showMedicalFileById(@PathVariable Integer id){
        return ResponseEntity.ok(medicalFileService.showMedicalFileById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<MedicalFileResponseDTO> updateMedicalFile(@PathVariable Integer id, @Valid @RequestBody MedicalFileRequestDTO medicalFileRequestDTO){
        MedicalFileResponseDTO response = medicalFileService.updateMedicalFile(id, medicalFileRequestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<Page<MedicalFileResponseDTO>> showAllMedicalFiles(Pageable pageable){
        return ResponseEntity.ok(medicalFileService.getMedicalFiles(pageable));
    }
}