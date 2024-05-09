package com.setur.contact.service;

import com.setur.contact.domain.entity.Report;
import com.setur.contact.domain.events.ReportGenerateEvent;
import com.setur.contact.domain.repository.ReportDetailRepository;
import com.setur.contact.domain.repository.ReportRepository;
import com.setur.contact.dto.ReportDetailDto;
import com.setur.contact.dto.ReportDto;
import com.setur.contact.dto.response.ReportResponse;
import com.setur.contact.enums.ReportStatus;
import com.setur.contact.exception.ReportNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportService {

    private final ReportRepository reportRepository;

    private final ReportDetailRepository reportDetailRepository;

    private final ApplicationEventPublisher publisher;

    @Transactional
    public void generateReport() {
        var date = LocalDateTime.now();
        var report = Report.builder()
                .date(date)
                .status(ReportStatus.IN_PROGRESS)
                .build();
        var savedReport = reportRepository.save(report);

        var event = ReportGenerateEvent.builder()
                .reportId(savedReport.getId())
                .build();
        publisher.publishEvent(event);
    }

    public List<ReportDto> getReports() {
        return reportRepository.findAllReport();
    }

    public ReportResponse getReport(UUID id) {
        var report = reportRepository.findById(id)
                .orElseThrow(() -> new ReportNotFoundException(id + " id li rapor bulunamadı."));
        var details = reportDetailRepository.findAllByReportId(report.getId())
                .stream()
                .map(reportDetail -> ReportDetailDto.builder()
                        .id(reportDetail.getId())
                        .location(reportDetail.getLocation())
                        .contactCount(reportDetail.getContactCount())
                        .contactPhoneCount(reportDetail.getContactPhoneCount())
                        .build())
                .toList();
        return ReportResponse.builder()
                .id(report.getId())
                .date(report.getDate())
                .status(report.getStatus())
                .details(details)
                .build();

    }
}
