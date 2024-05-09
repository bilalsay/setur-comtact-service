package com.setur.contact.domain.repository;

import com.setur.contact.domain.entity.ReportDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReportDetailRepository extends JpaRepository<ReportDetail, UUID> {

    List<ReportDetail> findAllByReportId(UUID reportId);
}
