package org.example.healthcare.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.healthcare.DTO.request.AppointmentRequestDTO;
import org.example.healthcare.DTO.response.AppointmentResponseDTO;
import org.example.healthcare.services.AppointmentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointment")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'PATIENT')")
    public ResponseEntity<AppointmentResponseDTO> createAppointment(@Valid @RequestBody AppointmentRequestDTO appointmentRequestDTO){
        AppointmentResponseDTO response = appointmentService.createAppointment(appointmentRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') " +
            "or (hasRole('PATIENT') and @appointmentService.isPatientAppointmentOwner(#id, authentication.name)) " +
            "or (hasRole('DOCTOR') and @appointmentService.isDoctorAppointmentOwner(#id, authentication.name))")
    public ResponseEntity<AppointmentResponseDTO> updateAppointment(@PathVariable Integer id, @Valid @RequestBody AppointmentRequestDTO appointmentRequestDTO){
        AppointmentResponseDTO response = appointmentService.updateAppointment(id, appointmentRequestDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Integer id){
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/patient/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR') or (hasRole('PATIENT') and @patientService.isPatientOwner(#id, authentication.name))")
    public ResponseEntity<List<AppointmentResponseDTO>> showAllAppointmentByPatientId(@PathVariable Integer id){
        return ResponseEntity.ok(appointmentService.showAllAppointmentByPatientId(id));
    }

    @GetMapping("/doctor/{id}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('DOCTOR') and @doctorService.isDoctorOwner(#id, authentication.name))")
    public ResponseEntity<List<AppointmentResponseDTO>> showAllAppointmentByDoctorId(@PathVariable Integer id){
        return ResponseEntity.ok(appointmentService.showAllAppointmentByDoctorId(id));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<AppointmentResponseDTO>> showAllAppointments(
            @PageableDefault(sort = "appointmentDate", direction = org.springframework.data.domain.Sort.Direction.DESC)
            Pageable pageable){
        return ResponseEntity.ok(appointmentService.getAppointments(pageable));
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<AppointmentResponseDTO>> searchAppointments(@RequestParam String status, Pageable pageable){
        return ResponseEntity.ok(appointmentService.searchAppointments(status, pageable));
    }
}