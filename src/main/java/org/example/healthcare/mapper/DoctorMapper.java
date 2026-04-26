package org.example.healthcare.mapper;

import org.example.healthcare.DTO.request.DoctorRequestDTO;
import org.example.healthcare.DTO.response.DoctorResponseDTO;
import org.example.healthcare.model.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DoctorMapper {
    DoctorResponseDTO toDTO(Doctor doctor);

    Doctor toEntity(DoctorRequestDTO doctorRequestDTO);

    void updateEntityFromDTO(DoctorRequestDTO doctorRequestDTO, @MappingTarget Doctor doctor);
}
