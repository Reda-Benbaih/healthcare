package org.example.healthcare.services;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.example.healthcare.DTO.response.MedicalFileResponseDTO;
import org.springframework.stereotype.Service;

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
}