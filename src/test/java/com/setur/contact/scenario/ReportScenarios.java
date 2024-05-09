package com.setur.contact.scenario;

import com.setur.contact.domain.entity.Report;
import com.setur.contact.domain.entity.ReportDetail;
import com.setur.contact.domain.events.ReportGenerateEvent;
import com.setur.contact.dto.ReportDetailDto;
import com.setur.contact.dto.ReportDto;
import com.setur.contact.enums.ReportStatus;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@UtilityClass
public class ReportScenarios {

    public static ReportDetailDto getReportDetailDto() {
        return ReportDetailDto.builder()
                .location("1234, 1223")
                .contactCount(3)
                .contactPhoneCount(5)
                .build();
    }

    public static Report getReport(UUID id, LocalDateTime date) {
        return Report.builder()
                .id(id)
                .date(date)
                .status(ReportStatus.IN_PROGRESS)
                .build();
    }

    public static List<ReportDto> getReportDtoList(UUID id, LocalDateTime date) {
        return List.of(ReportDto.builder()
                .id(id)
                .date(date)
                .status(ReportStatus.IN_PROGRESS)
                .build(),
                ReportDto.builder()
                        .id(id)
                        .date(date)
                        .status(ReportStatus.COMPLETED)
                        .build());
    }



    public static ReportGenerateEvent getReportGenerateEvent(UUID id) {
        return ReportGenerateEvent.builder()
                .reportId(id)
                .build();
    }

    public static List<ReportDetail> getReportDetailList() {
        return List.of(ReportDetail.builder()
                .location("555, 6666")
                .contactCount(3)
                .contactPhoneCount(5)
                .build(),
                ReportDetail.builder()
                        .location("1234, 1223")
                        .contactCount(3)
                        .contactPhoneCount(5)
                        .build());
    }

}
