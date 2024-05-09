package com.setur.contact.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportDetailDto implements Serializable {

    private static final long serialVersionUID = 7104533194213231182L;

    private UUID id;

    private String location;

    private Integer contactCount;

    private Integer contactPhoneCount;
}
