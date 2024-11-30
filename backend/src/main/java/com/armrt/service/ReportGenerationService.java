package com.armrt.service;

import com.armrt.exception.ReportGenerationException;
import com.armrt.model.RoleRecommendation;
import com.armrt.repository.RoleRecommendationRepository;
import com.armrt.exception.ReportGenerationException;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import com.itextpdf.layout.properties.TextAlignment;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ReportGenerationService {
    private final RoleRecommendationRepository repository;

    public ReportGenerationService(RoleRecommendationRepository repository) {
        this.repository = repository;
    }

    public ByteArrayOutputStream generateComprehensiveReport() {
        List<RoleRecommendation> recommendations = repository.findAll();
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            addReportHeader(document);
            addRecommendationSection(document, recommendations);

            document.close();
            return baos;
        } catch (IOException e) {
            throw new ReportGenerationException("Failed to generate report", e);
        }
    }

    private void addReportHeader(Document document) {
        Paragraph title = new Paragraph("Role Optimization Report")
                .setFontSize(18)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER);
        document.add(title);
    }

    private void addRecommendationSection(Document document, List<RoleRecommendation> recommendations) {
        document.add(new Paragraph("Role Recommendations:").setBold());
        for (RoleRecommendation recommendation : recommendations) {
            document.add(new Paragraph(recommendation.toString()));
        }
    }
}
