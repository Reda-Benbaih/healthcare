package org.example.healthcare.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.healthcare.DTO.request.MedicalFileRequestDTO;
import org.example.healthcare.DTO.response.MedicalFileResponseDTO;
import org.example.healthcare.mapper.MedicalFileMapper;
import org.example.healthcare.model.MedicalFile;
import org.example.healthcare.model.Patient;
import org.example.healthcare.repositories.MedicalFileRepository;
import org.example.healthcare.repositories.PatientRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicalFileService {
    private final MedicalFileMapper medicalFileMapper;
    private final MedicalFileRepository medicalFileRepository;
    private final PatientRepository patientRepository;

    public boolean isMedicalFileOwner(Integer fileId, String currentUserEmail) {
        return medicalFileRepository.findById(fileId)
                .map(file -> file.getPatient() != null && file.getPatient().getEmail().equalsIgnoreCase(currentUserEmail))
                .orElse(false);
    }

    @Transactional
    @CacheEvict(value = "medicalFiles", allEntries = true)
    public MedicalFileResponseDTO addMedicalFile(MedicalFileRequestDTO medicalFileRequestDTO){
        Patient patient = patientRepository.findById(medicalFileRequestDTO.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient non trouvé avec l'id : " + medicalFileRequestDTO.getPatientId()));
        MedicalFile medicalFile = medicalFileMapper.toEntity(medicalFileRequestDTO);
        medicalFile.setPatient(patient);
        MedicalFile savedMedicalFile = medicalFileRepository.save(medicalFile);
        return medicalFileMapper.toDTO(savedMedicalFile);
    }

    @Transactional
    @Cacheable(value = "medicalFiles", key = "'file_' + #id")
    public MedicalFileResponseDTO showMedicalFileById(Integer id){
        MedicalFile medicalFile = medicalFileRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("ce dossier n'existe pas"));
        return medicalFileMapper.toDTO(medicalFile);
    }

    @Transactional
    @CacheEvict(value = "medicalFiles", allEntries = true)
    public MedicalFileResponseDTO updateMedicalFile(Integer id , MedicalFileRequestDTO medicalFileRequestDTO){
        MedicalFile medicalFile = medicalFileRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("ce dossier n'existe pas"));
        medicalFileMapper.updateEntityFromDTO(medicalFileRequestDTO,medicalFile);
        return medicalFileMapper.toDTO(medicalFile);
    }

    @Cacheable(value = "medicalFiles", key = "'page_' + #pageable.pageNumber + '_' + #pageable.pageSize")
    public Page<MedicalFileResponseDTO> getMedicalFiles(Pageable pageable){
        return medicalFileRepository.findAll(pageable)
                .map(medicalFileMapper::toDTO);
    }
}