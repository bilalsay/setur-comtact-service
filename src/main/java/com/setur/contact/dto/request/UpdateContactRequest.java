package com.setur.contact.dto.request;

import com.setur.contact.dto.CommunicationDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateContactRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 3431846885938766006L;

    @NotNull(message = "name should not be null.")
    private String name;

    @NotNull(message = "surname should not be null.")
    private String surname;

    @NotNull(message = "company should not be null.")
    private String company;

    private List<CommunicationDto> communications;
}
