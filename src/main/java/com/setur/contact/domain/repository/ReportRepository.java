package com.setur.contact.domain.repository;

import com.setur.contact.domain.entity.Report;
import com.setur.contact.dto.ReportDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReportRepository extends JpaRepository<Report, UUID> {

    @Query(value = """
            select new com.setur.contact.dto.ReportDto(r.id, r.date, r.status)
            from Report r
            """)
    List<ReportDto> findAllReport();

}
