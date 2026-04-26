package org.example.healthcare.services;

import lombok.RequiredArgsConstructor;
import org.example.healthcare.DTO.request.AppointmentRequestDTO;
import org.example.healthcare.DTO.response.AppointmentResponseDTO;
import org.example.healthcare.mapper.AppointmentMapper;
import org.example.healthcare.model.Appointment;
import org.example.healthcare.repositories.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;

    public AppointmentResponseDTO createAppointment(AppointmentRequestDTO appointmentRequestDTO){
        Appointment appointment = appointmentMapper.toEntity(appointmentRequestDTO);
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return appointmentMapper.toDTO(savedAppointment);
    }

    public AppointmentResponseDTO updateAppointment(Integer id , AppointmentRequestDTO appointmentRequestDTO) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ce rendez-vous avec id " + id + " n'existe pas"));
        appointmentMapper.updateEntityFromDTO(appointmentRequestDTO, appointment);
        return appointmentMapper.toDTO(appointment);
    }

    public void deleteAppointment(Integer id){
        appointmentRepository.deleteById(id);
    }

    public List<AppointmentResponseDTO> showAllAppointment(){
        return appointmentRepository.findAll().stream()
                .map(appointment -> appointmentMapper.toDTO(appointment))
                .toList();
    }

    public List<AppointmentResponseDTO> showAllAppointmentByPatientId(Integer id){
        return appointmentRepository.findByPatientId(id).stream()
                .map(appointment -> appointmentMapper.toDTO(appointment))
                .toList();
    }

    public List<AppointmentResponseDTO> showAllAppointmentByDoctorId(Integer id){
        return appointmentRepository.findByDoctorId(id).stream()
                .map(appointment -> appointmentMapper.toDTO(appointment))
                .toList();
    }
}
