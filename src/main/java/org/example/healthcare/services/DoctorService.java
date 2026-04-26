package org.example.healthcare.services;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.healthcare.DTO.request.DoctorRequestDTO;
import org.example.healthcare.DTO.response.DoctorResponseDTO;
import org.example.healthcare.mapper.DoctorMapper;
import org.example.healthcare.model.Doctor;
import org.example.healthcare.repositories.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;

    @Transactional
    public DoctorResponseDTO addDoctor(DoctorRequestDTO doctorRequestDTO){
        Doctor doctor = doctorMapper.toEntity(doctorRequestDTO);
        Doctor savedDoctor = doctorRepository.save(doctor);
        return doctorMapper.toDTO(savedDoctor);
    }

    public void deleteDoctor(Integer id){
        doctorRepository.deleteById(id);
    }

    @Transactional
    public List<DoctorResponseDTO> showAllDoctors(){
        return doctorRepository.findAll().stream()
                .map(doctor -> doctorMapper.toDTO(doctor))
                .toList();
    }

    public DoctorResponseDTO updateDoctor(Integer id , DoctorRequestDTO doctorRequestDTO){
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("medecin avec ce id "+id+" n'existe pas"));

        doctorMapper.updateEntityFromDTO(doctorRequestDTO,doctor);

        return doctorMapper.toDTO(doctor);
    }
}
