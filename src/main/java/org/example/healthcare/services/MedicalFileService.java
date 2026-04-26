package org.example.healthcare.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.healthcare.DTO.request.MedicalFileRequestDTO;
import org.example.healthcare.DTO.response.MedicalFileResponseDTO;
import org.example.healthcare.mapper.MedicalFileMapper;
import org.example.healthcare.model.MedicalFile;
import org.example.healthcare.repositories.MedicalFileRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicalFileService {
    private final MedicalFileMapper medicalFileMapper;
    private final MedicalFileRepository medicalFileRepository;

    @Transactional
    public MedicalFileResponseDTO addMedicalFile(MedicalFileRequestDTO medicalFileRequestDTO){
        MedicalFile medicalFile = medicalFileMapper.toEntity(medicalFileRequestDTO);
        MedicalFile savedMedicalFile = medicalFileRepository.save(medicalFile);
        return medicalFileMapper.toDTO(savedMedicalFile);
    }

    @Transactional
    public MedicalFileResponseDTO showMedicalFileById(Integer id){
        MedicalFile medicalFile = medicalFileRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("ce dossier n'existe pas"));
        return medicalFileMapper.toDTO(medicalFile);
    }

    @Transactional
    public MedicalFileResponseDTO updateMedicalFile(Integer id , MedicalFileRequestDTO medicalFileRequestDTO){
        MedicalFile medicalFile = medicalFileRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("ce dossier n'existe pas"));

        medicalFileMapper.updateEntityFromDTO(medicalFileRequestDTO,medicalFile);

        return medicalFileMapper.toDTO(medicalFile);
    }
}
