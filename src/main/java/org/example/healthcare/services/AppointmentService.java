package org.example.healthcare.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.healthcare.DTO.request.AppointmentRequestDTO;
import org.example.healthcare.DTO.response.AppointmentResponseDTO;
import org.example.healthcare.mapper.AppointmentMapper;
import org.example.healthcare.model.Appointment;
import org.example.healthcare.model.Doctor;
import org.example.healthcare.model.Patient;
import org.example.healthcare.repositories.AppointmentRepository;
import org.example.healthcare.repositories.DoctorRepository;
import org.example.healthcare.repositories.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public boolean isPatientAppointmentOwner(Integer appointmentId, String currentUserEmail) {
        return appointmentRepository.findById(appointmentId)
                .map(app -> app.getPatient() != null && app.getPatient().getEmail().equalsIgnoreCase(currentUserEmail))
                .orElse(false);
    }

    public boolean isDoctorAppointmentOwner(Integer appointmentId, String currentUserEmail) {
        return appointmentRepository.findById(appointmentId)
                .map(app -> app.getDoctor() != null && app.getDoctor().getEmail().equalsIgnoreCase(currentUserEmail))
                .orElse(false);
    }

    @Transactional
    public AppointmentResponseDTO createAppointment(AppointmentRequestDTO appointmentRequestDTO){
        Doctor doctor = doctorRepository.findById(appointmentRequestDTO.getDoctorId())
                .orElseThrow(() -> new RuntimeException("medecin non touve avec l'id : " + appointmentRequestDTO.getDoctorId()));
        Patient patient = patientRepository.findById(appointmentRequestDTO.getPatientId())
                .orElseThrow(() -> new RuntimeException("patient non trouve avec l'id : " + appointmentRequestDTO.getPatientId()));



        Appointment appointment = appointmentMapper.toEntity(appointmentRequestDTO);
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        Appointment savedAppointment = appointmentRepository.save(appointment);

        return appointmentMapper.toDTO(savedAppointment);
    }

    @Transactional
    public AppointmentResponseDTO updateAppointment(Integer id , AppointmentRequestDTO appointmentRequestDTO) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ce rendez-vous avec id " + id + " n'existe pas"));
        appointmentMapper.updateEntityFromDTO(appointmentRequestDTO, appointment);
        return appointmentMapper.toDTO(appointment);
    }

    @Transactional
    public void deleteAppointment(Integer id){
        appointmentRepository.deleteById(id);
    }

    @Transactional
    public List<AppointmentResponseDTO> showAllAppointment(){
        return appointmentRepository.findAll().stream()
                .map(appointment -> appointmentMapper.toDTO(appointment))
                .toList();
    }

    @Transactional
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

    public Page<AppointmentResponseDTO> getAppointments(Pageable pageable){
        return appointmentRepository.findAll(pageable)
                .map(appointmentMapper::toDTO);
    }

    public Page<AppointmentResponseDTO> searchAppointments(String name, Pageable pageable){
        return appointmentRepository
                .findByStatusContainingIgnoreCase(name, pageable)
                .map(appointmentMapper::toDTO);
    }
}
