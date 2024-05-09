package com.setur.contact.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 3431846885938766006L;

    private UUID id;

    private String name;

    private String surname;

    private String company;
}
