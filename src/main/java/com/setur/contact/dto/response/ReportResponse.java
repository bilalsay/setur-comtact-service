package com.setur.contact.dto.response;

import com.setur.contact.dto.ReportDetailDto;
import com.setur.contact.enums.ReportStatus;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
public class ReportResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = -5362690614335988349L;

    private UUID id;

    private LocalDateTime date;

    private ReportStatus status;

    private List<ReportDetailDto> details;

}
