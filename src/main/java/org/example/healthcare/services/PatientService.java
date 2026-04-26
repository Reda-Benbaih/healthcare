package org.example.healthcare.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.healthcare.DTO.request.PatientRequestDTO;
import org.example.healthcare.DTO.response.PatientResponseDTO;
import org.example.healthcare.mapper.PatientMapper;
import org.example.healthcare.model.Patient;
import org.example.healthcare.repositories.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Transactional
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
    public void deletePatient(Integer id){
        patientRepository.deleteById(id);
    }

    @Transactional
    public PatientResponseDTO showPatientById(Integer id){
        Patient patient = patientRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("ce patient avec ce "+id+" n'existe pas"));
        return patientMapper.toDTO(patient);
    }

    public PatientResponseDTO updatePatient(Integer id,PatientRequestDTO patientRequestDTO){
        Patient patient = patientRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("ce patient avec ce "+id+" n'existe pas"));

        patientMapper.updateEntityFromDTO(patientRequestDTO,patient);

        return patientMapper.toDTO(patient);

    }


}
