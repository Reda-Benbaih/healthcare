package org.example.healthcare.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.healthcare.DTO.request.DoctorRequestDTO;
import org.example.healthcare.DTO.response.DoctorResponseDTO;
import org.example.healthcare.mapper.DoctorMapper;
import org.example.healthcare.model.Doctor;
import org.example.healthcare.repositories.DoctorRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;

    public boolean isDoctorOwner(Integer doctorId, String currentUserEmail) {
        return doctorRepository.findById(doctorId)
                .map(doctor -> doctor.getEmail().equalsIgnoreCase(currentUserEmail))
                .orElse(false);
    }

    @Transactional
    @CacheEvict(value = "doctors",allEntries = true)
    public DoctorResponseDTO addDoctor(DoctorRequestDTO doctorRequestDTO){
        Doctor doctor = doctorMapper.toEntity(doctorRequestDTO);
        Doctor savedDoctor = doctorRepository.save(doctor);
        return doctorMapper.toDTO(savedDoctor);
    }

    @CacheEvict(value = "doctors",allEntries = true)
    public void deleteDoctor(Integer id){
        doctorRepository.deleteById(id);
    }

    @Transactional
    public List<DoctorResponseDTO> showAllDoctors(){
        return doctorRepository.findAll().stream()
                .map(doctor -> doctorMapper.toDTO(doctor))
                .toList();
    }

    @Transactional
    @CacheEvict(value = "doctors",allEntries = true)
    public DoctorResponseDTO updateDoctor(Integer id , DoctorRequestDTO doctorRequestDTO){
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("medecin avec ce id "+id+" n'existe pas"));

        doctorMapper.updateEntityFromDTO(doctorRequestDTO,doctor);

        return doctorMapper.toDTO(doctor);
    }

    @Cacheable(value = "doctors", key = "'page_' + #pageable.pageNumber + '_' + #pageable.pageSize")
    public Page<DoctorResponseDTO> getDoctors(Pageable pageable){
        return doctorRepository.findAll(pageable)
                .map(doctorMapper::toDTO);
    }

    @Cacheable(value = "doctors", key = "'search_' + #name + '_' + #pageable.pageNumber")
    public Page<DoctorResponseDTO> searchDoctors(String name, Pageable pageable){
        return doctorRepository.findBySpecialityContainingIgnoreCase(name, pageable)
                .map(doctorMapper::toDTO);
    }
}
