package com.setur.contact.domain.entity;

import com.setur.contact.enums.ReportStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "report_detail")
public class ReportDetail implements Serializable {

    private static final long serialVersionUID = 7104533194213231182L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "contact_count", nullable = false)
    private Integer contactCount;

    @Column(name = "contact_phone_count", nullable = false)
    private Integer contactPhoneCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id", referencedColumnName = "id", nullable = false)
    private Report report;
}
