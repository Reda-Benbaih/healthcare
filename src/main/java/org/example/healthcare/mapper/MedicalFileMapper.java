package org.example.healthcare.mapper;

import org.example.healthcare.DTO.request.MedicalFileRequestDTO;
import org.example.healthcare.DTO.response.MedicalFileResponseDTO;
import org.example.healthcare.model.MedicalFile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MedicalFileMapper {
    MedicalFileResponseDTO toDTO(MedicalFile medicalFile);

    @Mapping(source = "patientId", target = "patient.id")
    MedicalFile toEntity(MedicalFileRequestDTO medicalFileRequestDTO);

    @Mapping(source = "patientId", target = "patient.id")
    void updateEntityFromDTO(MedicalFileRequestDTO medicalFileRequestDTO, @MappingTarget MedicalFile medicalFile);
}
