package org.example.healthcare.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.healthcare.DTO.request.PatientRequestDTO;
import org.example.healthcare.DTO.response.PatientResponseDTO;
import org.example.healthcare.mapper.PatientMapper;
import org.example.healthcare.model.Patient;
import org.example.healthcare.repositories.PatientRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;


    public boolean isPatientOwner(Integer patientId, String currentUserEmail) {
        return patientRepository.findById(patientId)
                .map(patient -> patient.getEmail().equalsIgnoreCase(currentUserEmail))
                .orElse(false);
    }

    @Transactional
    @CacheEvict(value = "patients",allEntries = true)
    public PatientResponseDTO addPatient(PatientRequestDTO patientRequestDTO){
        Patient patient = patientMapper.toEntity(patientRequestDTO);
        Patient savedPatient = patientRepository.save(patient);
        return patientMapper.toDTO(savedPatient);
    }

    @Transactional
    public List<PatientResponseDTO> showAllPatients(){
        return patientRepository.findAll().stream()
                .map(patient -> patientMapper.toDTO(patient))
                .toList();
    }

    @Transactional
    @CacheEvict(value = "patients",allEntries = true)
    public void deletePatient(Integer id){
        patientRepository.deleteById(id);
    }

    @Transactional
    @Cacheable(value = "patients",key = "'patient_' + #id")
    public PatientResponseDTO showPatientById(Integer id){
        Patient patient = patientRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("ce patient avec ce "+id+" n'existe pas"));
        return patientMapper.toDTO(patient);
    }

    @Transactional
    @CacheEvict(value = "patients",allEntries = true)
    public PatientResponseDTO updatePatient(Integer id,PatientRequestDTO patientRequestDTO){
        Patient patient = patientRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("ce patient avec ce "+id+" n'existe pas"));

        patientMapper.updateEntityFromDTO(patientRequestDTO,patient);

        return patientMapper.toDTO(patient);

    }
    @Cacheable(value = "patients", key = "'page_' + #pageable.pageNumber + '_' + #pageable.pageSize")
    public Page<PatientResponseDTO> getPatients(Pageable pageable){
        return patientRepository.findAll(pageable)
                .map(patientMapper::toDTO);
    }

    @Transactional
    @Cacheable(value = "patients", key = "'search_' + #name + '_' + #pageable.pageNumber")
    public Page<PatientResponseDTO> searchPatients(String name, Pageable pageable){
        return patientRepository.findByFirstNameContainingIgnoreCase(name, pageable)
                .map(patientMapper::toDTO);
    }

}
