package com.setur.contact.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateContactRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 3431846885938766006L;

    @NotNull(message = "zoneId should not be null.")
    private Long zoneId;


}
