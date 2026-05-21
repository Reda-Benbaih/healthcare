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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;

    @PostMapping
    public ResponseEntity<DoctorResponseDTO> addDoctor(@Valid @RequestBody DoctorRequestDTO doctorRequestDTO){
        DoctorResponseDTO response = doctorService.addDoctor(doctorRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Integer id){
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<DoctorResponseDTO>> showAllDoctors(){
        return ResponseEntity.ok(doctorService.showAllDoctors());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> updateDoctor(@PathVariable Integer id, @Valid @RequestBody DoctorRequestDTO doctorRequestDTO){
        DoctorResponseDTO response = doctorService.updateDoctor(id, doctorRequestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<DoctorResponseDTO>> showDoctors(Pageable pageable){
        return ResponseEntity.ok(doctorService.getDoctors(pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<DoctorResponseDTO>>
    searchDoctors(
            @RequestParam String speciality,
            Pageable pageable
    ){
        return ResponseEntity.ok(
                doctorService.searchDoctors(
                        speciality,
                        pageable
                )
        );
    }
}