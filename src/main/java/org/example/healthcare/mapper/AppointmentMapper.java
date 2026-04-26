package org.example.healthcare.mapper;

import org.example.healthcare.DTO.request.AppointmentRequestDTO;
import org.example.healthcare.DTO.response.AppointmentResponseDTO;
import org.example.healthcare.model.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
    @Mapping(source = "doctor.id", target = "doctorId")
    @Mapping(source = "patient.id", target = "patientId")
    AppointmentResponseDTO toDTO(Appointment appointment);

    @Mapping(target = "doctor",ignore = true)
    @Mapping(target = "patient",ignore = true)
    Appointment toEntity(AppointmentRequestDTO appointmentRequestDTO);

    void updateEntityFromDTO(AppointmentRequestDTO appointmentRequestDTO, @MappingTarget Appointment appointment);
}
