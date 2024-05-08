package com.setur.contact.dto;

import com.setur.contact.enums.CommunicationType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
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
public class CommunicationDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 3431846885938766006L;

    private UUID id;

    @NotNull(message = "type should not be null.")
    private CommunicationType type;

    @NotNull(message = "value should not be null.")
    private String value;
}
