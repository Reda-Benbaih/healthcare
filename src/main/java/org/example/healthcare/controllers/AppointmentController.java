package org.example.healthcare.controllers;

import lombok.RequiredArgsConstructor;
import org.example.healthcare.DTO.request.AppointmentRequestDTO;
import org.example.healthcare.DTO.response.AppointmentResponseDTO;
import org.example.healthcare.services.AppointmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{id}")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping
    public AppointmentResponseDTO createAppointment(@RequestBody AppointmentRequestDTO appointmentRequestDTO){
        return appointmentService.createAppointment(appointmentRequestDTO);
    }

    @PutMapping("/{id}")
    public AppointmentResponseDTO updateAppointment(@PathVariable Integer id , @RequestBody AppointmentRequestDTO appointmentRequestDTO){
        return appointmentService.updateAppointment(id, appointmentRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(Integer id){
        appointmentService.deleteAppointment(id);
    }

    @GetMapping
    public List<AppointmentResponseDTO> showAllAppointment(){
        return  appointmentService.showAllAppointment();
    }

    @GetMapping("/{id}")
    public List<AppointmentResponseDTO> showAllAppointmentByPatientId(@PathVariable Integer id){
        return appointmentService.showAllAppointmentByPatientId(id);
    }

    @GetMapping("/{id}")
    public List<AppointmentResponseDTO> showAllAppointmentByDoctorId(@PathVariable Integer id){
        return appointmentService.showAllAppointmentByDoctorId(id);
    }
}
