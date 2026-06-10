package org.example.healthcare.services;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.example.healthcare.DTO.response.MedicalFileResponseDTO;
import org.springframework.stereotype.Service;
import com.itextpdf.text.pdf.PdfPTable;
import org.example.healthcare.DTO.response.PatientResponseDTO;
import org.example.healthcare.DTO.response.AppointmentResponseDTO;
import java.util.List;

import java.io.ByteArrayOutputStream;

@Service
public class PdfGenerationService {

    public byte[] generateMedicalFilePdf(MedicalFileResponseDTO medicalFile) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            document.add(new Paragraph("Dossier Médical N°: " + medicalFile.getId()));
            document.add(new Paragraph("Date de création: " + medicalFile.getCreationDate()));
            document.add(new Paragraph("\nDiagnostic:"));
            document.add(new Paragraph(medicalFile.getDiagnosis()));
            document.add(new Paragraph("\nObservations:"));
            document.add(new Paragraph(medicalFile.getObservation()));

            document.close();
        } catch (DocumentException e) {
            throw new RuntimeException("Erreur lors de la génération du PDF", e);
        }

        return out.toByteArray();
    }

    public byte[] generatePatientAppointmentsPdf(PatientResponseDTO patient, List<AppointmentResponseDTO> appointments) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            document.add(new Paragraph("Historique des Rendez-vous"));
            document.add(new Paragraph("Patient : " + patient.getFirstName() + " " + patient.getLastName()));
            document.add(new Paragraph("Email : " + patient.getPhone()));
            document.add(new Paragraph("\n")); // Espacement

            // Création d'un tableau à 3 colonnes
            PdfPTable table = new PdfPTable(3);
            table.addCell("ID Rendez-vous");
            table.addCell("Date");
            table.addCell("Statut");

            // Remplissage du tableau
            for (AppointmentResponseDTO app : appointments) {
                table.addCell(String.valueOf(app.getId()));
                table.addCell(app.getAppointmentDate() != null ? app.getAppointmentDate().toString() : "N/A");
                table.addCell(app.getStatus());
            }

            document.add(table);
            document.close();
        } catch (DocumentException e) {
            throw new RuntimeException("Erreur lors de la génération du PDF des rendez-vous", e);
        }

        return out.toByteArray();
    }

    public byte[] generateSimpleReportPdf(long totalPatients, long totalDoctors, long totalAppointments) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            document.add(new Paragraph("Rapport Global - HealthCare+"));
            document.add(new Paragraph("Généré automatiquement par le système."));
            document.add(new Paragraph("\n"));

            document.add(new Paragraph("--- Statistiques Actuelles ---"));
            document.add(new Paragraph("Nombre total de patients : " + totalPatients));
            document.add(new Paragraph("Nombre total de médecins : " + totalDoctors));
            document.add(new Paragraph("Nombre total de rendez-vous : " + totalAppointments));

            document.close();
        } catch (DocumentException e) {
            throw new RuntimeException("Erreur lors de la génération du rapport simple", e);
        }

        return out.toByteArray();
    }
}