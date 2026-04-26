package org.example.healthcare.mapper;

import org.example.healthcare.DTO.request.PatientRequestDTO;
import org.example.healthcare.DTO.response.PatientResponseDTO;
import org.example.healthcare.model.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    PatientResponseDTO toDTO(Patient patient);

    Patient toEntity(PatientRequestDTO patientRequestDTO);
}
