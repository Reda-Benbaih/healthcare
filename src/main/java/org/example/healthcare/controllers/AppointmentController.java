package org.example.healthcare.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.healthcare.DTO.request.AppointmentRequestDTO;
import org.example.healthcare.DTO.response.AppointmentResponseDTO;
import org.example.healthcare.services.AppointmentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointment")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> createAppointment(@Valid @RequestBody AppointmentRequestDTO appointmentRequestDTO){
        AppointmentResponseDTO response = appointmentService.createAppointment(appointmentRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> updateAppointment(@PathVariable Integer id, @Valid @RequestBody AppointmentRequestDTO appointmentRequestDTO){
        AppointmentResponseDTO response = appointmentService.updateAppointment(id, appointmentRequestDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Integer id){
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResponseDTO>> showAllAppointment(){
        return ResponseEntity.ok(appointmentService.showAllAppointment());
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<List<AppointmentResponseDTO>> showAllAppointmentByPatientId(@PathVariable Integer id){
        return ResponseEntity.ok(appointmentService.showAllAppointmentByPatientId(id));
    }

    @GetMapping("/doctor/{id}")
    public ResponseEntity<List<AppointmentResponseDTO>> showAllAppointmentByDoctorId(@PathVariable Integer id){
        return ResponseEntity.ok(appointmentService.showAllAppointmentByDoctorId(id));
    }

    @GetMapping
    public ResponseEntity<Page<AppointmentResponseDTO>> showAllAppointments(Pageable pageable){
        return ResponseEntity.ok(appointmentService.getAppointments(pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<AppointmentResponseDTO>> searchAppointments(@RequestParam String status, Pageable pageable){
        return ResponseEntity.ok(appointmentService.searchAppointments(status, pageable));
    }
}