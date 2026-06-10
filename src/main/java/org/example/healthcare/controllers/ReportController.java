package org.example.healthcare.controllers;

import lombok.RequiredArgsConstructor;
import org.example.healthcare.repositories.AppointmentRepository;
import org.example.healthcare.repositories.DoctorRepository;
import org.example.healthcare.repositories.PatientRepository;
import org.example.healthcare.services.PdfGenerationService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final PdfGenerationService pdfGenerationService;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;

    @GetMapping("/simple/download")
    @PreAuthorize("hasRole('ADMIN')") // Souvent, seuls les admins tirent des rapports globaux
    public ResponseEntity<byte[]> downloadSimpleReport() {

        // Récupération des statistiques rapides via les repositories
        long totalPatients = patientRepository.count();
        long totalDoctors = doctorRepository.count();
        long totalAppointments = appointmentRepository.count();

        byte[] pdfBytes = pdfGenerationService.generateSimpleReportPdf(totalPatients, totalDoctors, totalAppointments);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "rapport_global_healthcare.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }
}