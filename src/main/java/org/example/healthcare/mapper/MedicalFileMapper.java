package org.example.healthcare.mapper;

import org.example.healthcare.DTO.request.MedicalFileRequestDTO;
import org.example.healthcare.DTO.response.MedicalFileResponseDTO;
import org.example.healthcare.model.MedicalFile;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MedicalFileMapper {
    MedicalFileResponseDTO toDTO(MedicalFile medicalFile);

    MedicalFile toEntity(MedicalFileRequestDTO medicalFileRequestDTO);

    void updateEntityFromDTO(MedicalFileRequestDTO medicalFileRequestDTO, @MappingTarget MedicalFile medicalFile);
}
