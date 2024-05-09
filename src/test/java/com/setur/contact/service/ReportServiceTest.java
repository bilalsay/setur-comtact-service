package com.setur.contact.service;

import com.setur.contact.domain.repository.ReportDetailRepository;
import com.setur.contact.domain.repository.ReportRepository;
import com.setur.contact.enums.ReportStatus;
import com.setur.contact.exception.ReportNotFoundException;
import com.setur.contact.logger.TestLoggerExtension;
import com.setur.contact.mockito.MockitoExtension;
import com.setur.contact.scenario.ReportScenarios;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.context.ApplicationEventPublisher;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(TestLoggerExtension.class)
class ReportServiceTest {

    @InjectMocks
    private ReportService reportService;

    @Mock
    private ReportRepository reportRepository;

    @Mock
    private ReportDetailRepository reportDetailRepository;

    @Mock
    private ApplicationEventPublisher publisher;

    @Test
    void shouldGenerateReportSuccess_whenThereIsNoError() {
        try (MockedStatic<LocalDateTime> localDateTimeUtil = Mockito.mockStatic(LocalDateTime.class)) {
            var date = LocalDateTime.parse("2024-05-09T11:58:13.167874");
            localDateTimeUtil.when(() -> LocalDateTime.now())
                    .thenReturn(date);
            //given
            var id = UUID.fromString("c41f534d-3f9b-49ba-8e84-b35263c962cb");
            var report = ReportScenarios.getReport(null, date);
            var savedReport = ReportScenarios.getReport(id, date);
            var reportGenerateEvent = ReportScenarios.getReportGenerateEvent(id);

            //when
            when(reportRepository.save(report))
                    .thenReturn(savedReport);
            doNothing().when(publisher).publishEvent(reportGenerateEvent);

            //then
            reportService.generateReport();

            verify(reportRepository, times(1)).save(report);
            verify(publisher, times(1)).publishEvent(reportGenerateEvent);
        }
    }

    @Test
    void shouldGetReportsSuccess_whenThereIsNoError() {
            var date = LocalDateTime.parse("2024-05-09T11:58:13.167874");
            //given
            var id = UUID.fromString("c41f534d-3f9b-49ba-8e84-b35263c962cb");
            var reportDtoList = ReportScenarios.getReportDtoList(id, date);

            //when
            when(reportRepository.findAllReport())
                    .thenReturn(reportDtoList);

            //then
            var response = reportService.getReports();
            assertEquals(response.get(1).getStatus(), ReportStatus.COMPLETED);

            verify(reportRepository, times(1)).findAllReport();
    }

    @Test
    void shouldGetReportDetailByIdSuccess_whenThereIsNoError() {
        var date = LocalDateTime.parse("2024-05-09T11:58:13.167874");
        //given
        var id = UUID.fromString("c41f534d-3f9b-49ba-8e84-b35263c962cb");
        var reportDetailList = ReportScenarios.getReportDetailList();
        var report = ReportScenarios.getReport(id, date);

        //when
        when(reportRepository.findById(id))
                .thenReturn(Optional.ofNullable(report));
        when(reportDetailRepository.findAllByReportId(id))
                .thenReturn(reportDetailList);

        //then
        var response = reportService.getReport(id);
        assertEquals(response.getDetails().get(0).getLocation(), "555, 6666");

        verify(reportRepository, times(1)).findById(id);
        verify(reportDetailRepository, times(1)).findAllByReportId(id);
    }

    @Test
    void shouldNotGetReportDetailByIdSuccess_whenReportNotFoundException() {
        var date = LocalDateTime.parse("2024-05-09T11:58:13.167874");
        //given
        var id = UUID.fromString("c41f534d-3f9b-49ba-8e84-b35263c962cb");
        var reportDetailList = ReportScenarios.getReportDetailList();
        var report = ReportScenarios.getReport(id, date);

        //when
        when(reportRepository.findById(id))
                .thenReturn(Optional.empty());
        when(reportDetailRepository.findAllByReportId(id))
                .thenReturn(reportDetailList);

        //then
        assertThrows(ReportNotFoundException.class, () -> reportService.getReport(id));

        verify(reportRepository, times(1)).findById(id);
        verify(reportDetailRepository, never()).findAllByReportId(id);
    }

}