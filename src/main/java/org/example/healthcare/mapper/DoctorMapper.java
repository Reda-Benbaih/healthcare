package org.example.healthcare.mapper;

import org.example.healthcare.DTO.request.DoctorRequestDTO;
import org.example.healthcare.DTO.response.DoctorResponseDTO;
import org.example.healthcare.model.Doctor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DoctorMapper {
    DoctorResponseDTO toDTO(Doctor doctor);
    Doctor toEntity(DoctorRequestDTO doctorRequestDTO);
}
