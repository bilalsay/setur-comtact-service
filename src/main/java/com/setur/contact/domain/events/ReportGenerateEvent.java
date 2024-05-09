package com.setur.contact.domain.events;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode
public class ReportGenerateEvent implements Serializable {

    @Serial
    private static final long serialVersionUID = -5362690614335988349L;

    private UUID reportId;
   
}