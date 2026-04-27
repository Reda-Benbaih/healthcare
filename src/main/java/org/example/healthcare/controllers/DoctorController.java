package org.example.healthcare.controllers;

import lombok.RequiredArgsConstructor;
import org.example.healthcare.DTO.request.DoctorRequestDTO;
import org.example.healthcare.DTO.response.DoctorResponseDTO;
import org.example.healthcare.services.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/doctor")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;

    @PostMapping
    public DoctorResponseDTO addDoctor(@RequestBody DoctorRequestDTO doctorRequestDTO){
        return doctorService.addDoctor(doctorRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable Integer id){
        doctorService.deleteDoctor(id);
    }

    @GetMapping
    public List<DoctorResponseDTO> showAllDoctors(){
        return doctorService.showAllDoctors();
    }

    @PutMapping("/{id}")
    public DoctorResponseDTO updateDoctor(@PathVariable Integer id ,@RequestBody DoctorRequestDTO doctorRequestDTO){
        return  doctorService.updateDoctor(id, doctorRequestDTO);
    }

}
