package com.setur.contact.dto.response;

import com.setur.contact.dto.CommunicationDto;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
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

    private String name;

    private String surname;

    private String company;

    private List<CommunicationDto> communications;

}
