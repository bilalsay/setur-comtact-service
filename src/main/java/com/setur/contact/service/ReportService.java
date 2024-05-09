package com.setur.contact.service;

import com.setur.contact.domain.entity.Report;
import com.setur.contact.domain.events.ReportGenerateEvent;
import com.setur.contact.domain.repository.ReportRepository;
import com.setur.contact.enums.ReportStatus;
import com.setur.contact.infrastructure.configuration.EnvironmentConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportService {

    private final ReportRepository reportRepository;

    private final ApplicationEventPublisher publisher;

    @Transactional
    public void generateReport() {
        var date = LocalDateTime.now();
        var report = Report.builder()
                .date(date)
                .status(ReportStatus.IN_PROGRESS)
                .build();
        reportRepository.save(report);

        var event = ReportGenerateEvent.builder()
                .reportId(report.getId())
                .build();
        publisher.publishEvent(event);
    }
}
