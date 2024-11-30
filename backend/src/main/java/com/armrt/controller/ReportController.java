package com.armrt.controller;

import com.armrt.service.ReportGenerationService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    private final ReportGenerationService reportService;

    public ReportController(ReportGenerationService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/comprehensive")
    public ResponseEntity<byte[]> generateComprehensiveReport() {
        ByteArrayOutputStream report = reportService.generateComprehensiveReport();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(report.toByteArray());
    }
}
