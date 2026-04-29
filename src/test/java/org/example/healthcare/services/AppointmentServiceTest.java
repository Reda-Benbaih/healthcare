package org.example.healthcare.services;

import lombok.RequiredArgsConstructor;
import org.example.healthcare.DTO.response.AppointmentResponseDTO;
import org.example.healthcare.mapper.AppointmentMapper;
import org.example.healthcare.model.Appointment;
import org.example.healthcare.model.Doctor;
import org.example.healthcare.model.Patient;
import org.example.healthcare.repositories.AppointmentRepository;
import org.example.healthcare.repositories.DoctorRepository;
import org.example.healthcare.repositories.PatientRepository;
import org.example.healthcare.DTO.request.AppointmentRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {

    @Mock
    AppointmentRepository appointmentRepository;
    @Mock
    AppointmentMapper appointmentMapper;
    @Mock
    DoctorRepository doctorRepository;
    @Mock
    PatientRepository patientRepository;

    @InjectMocks
    AppointmentService appointmentService;

    Appointment appointment;
    AppointmentRequestDTO appointmentRequestDTO;
    AppointmentResponseDTO appointmentResponseDTO;
    Doctor doctor;
    Patient patient;
    List<AppointmentResponseDTO> lista = new ArrayList<>();


    @BeforeEach
    void setUp() {
        doctor = Doctor.builder()
                .id(1)
                .name("doctor1")
                .speciality("dentist")
                .email("doctor1@gmail.com")
                .phone(12345678L)
                .build();

        patient = Patient.builder()
                .id(1)
                .firstName("patient1")
                .lastName("test")
                .email("patient1@gmail.com")
                .phone(12345678L)
                .birthDay(LocalDate.of(2004, 11, 4))
                .build();

        appointmentRequestDTO = AppointmentRequestDTO.builder()
                .appointmentDate(LocalDate.of(2026, 11, 6))
                .status("pending")
                .doctorId(1)
                .patientId(1)
                .build();

        appointmentResponseDTO = AppointmentResponseDTO.builder()
                .id(1)
                .appointmentDate(LocalDate.of(2026, 11, 6))
                .status("pending")
                .doctorId(1).
                patientId(1)
                .build();

        appointment = Appointment.builder()
                .id(1)
                .appointmentDate(LocalDate.of(2026, 11, 6))
                .status("pending")
                .build();


    }

    @Test
    void createAppointment() {
        when(doctorRepository.findById(1)).thenReturn(Optional.ofNullable(doctor));
        when(patientRepository.findById(1)).thenReturn(Optional.ofNullable(patient));
        when(appointmentMapper.toEntity(appointmentRequestDTO)).thenReturn(appointment);
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        when(appointmentRepository.save(appointment)).thenReturn(appointment);
        when(appointmentMapper.toDTO(appointment)).thenReturn(appointmentResponseDTO);

        AppointmentResponseDTO result = appointmentService.createAppointment(appointmentRequestDTO);

        assertEquals(result, appointmentResponseDTO);
    }

    @Test
    void updateAppointment() {
        when(appointmentRepository.findById(1)).thenReturn(Optional.ofNullable(appointment));
        appointmentMapper.updateEntityFromDTO(appointmentRequestDTO, appointment);
        appointmentMapper.toDTO(appointment);

        AppointmentResponseDTO resulta = appointmentService.updateAppointment(1, appointmentRequestDTO);

        assertEquals(resulta, appointmentResponseDTO);

    }

    @Test
    void deleteAppointment() {

    }

    @Test
    void showAllAppointment() {
        lista.add(appointmentResponseDTO);

        List<Appointment> appointments = List.of(appointment);

        when(appointmentRepository.findAll()).thenReturn(appointments);
        when((appointmentMapper.toDTO(appointment))).thenReturn(appointmentResponseDTO);

        List<AppointmentResponseDTO> result = appointmentService.showAllAppointment();


        assertEquals(1,result.size());
        assertEquals(lista,result);
    }

    @Test
    void showAllAppointmentByPatientId() {
        List<Appointment> appointments = List.of(appointment);

        when(appointmentRepository.findByPatientId(1)).thenReturn(appointments);
        when(appointmentMapper.toDTO(appointment)).thenReturn(appointmentResponseDTO);

        List<AppointmentResponseDTO> result =
                appointmentService.showAllAppointmentByPatientId(1);

        assertEquals(1, result.size());
        assertEquals(appointmentResponseDTO, result.get(0));
    }
}