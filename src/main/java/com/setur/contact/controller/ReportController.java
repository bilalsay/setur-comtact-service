package com.setur.contact.controller;

import com.setur.contact.dto.response.ReportDto;
import com.setur.contact.dto.response.ReportResponse;
import com.setur.contact.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/reports")
@RequiredArgsConstructor
@Validated
public class ReportController {

    private final ReportService reportService;

    @PostMapping
    public void createReport() {
        reportService.generateReport();
    }

    @GetMapping
    public List<ReportDto> getReports() {
        return null;
    }

    @GetMapping(path = "/{id}")
    public ReportResponse getReport(@PathVariable("id") UUID id) {
        return null;
    }

}
