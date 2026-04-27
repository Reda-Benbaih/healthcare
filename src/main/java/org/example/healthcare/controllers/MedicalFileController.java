package org.example.healthcare.controllers;

import lombok.RequiredArgsConstructor;
import org.example.healthcare.DTO.request.MedicalFileRequestDTO;
import org.example.healthcare.DTO.response.MedicalFileResponseDTO;
import org.example.healthcare.services.MedicalFileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/medicalFile}")
@RequiredArgsConstructor
public class MedicalFileController {
    private final MedicalFileService medicalFileService;

    @PostMapping
    public MedicalFileResponseDTO addMedicalFile(@RequestBody MedicalFileRequestDTO medicalFileRequestDTO){
        return medicalFileService.addMedicalFile(medicalFileRequestDTO);
    }

    @GetMapping("/{id}")
    public MedicalFileResponseDTO showMedicalFileById(@PathVariable Integer id){
        return medicalFileService.showMedicalFileById(id);
    }

    @PutMapping("/{id]")
    public MedicalFileResponseDTO updateMedicalFile(@PathVariable Integer id, @RequestBody MedicalFileRequestDTO medicalFileRequestDTO){
        return medicalFileService.updateMedicalFile(id, medicalFileRequestDTO);
    }
}
