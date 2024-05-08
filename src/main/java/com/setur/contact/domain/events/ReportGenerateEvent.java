package com.setur.contact.domain.events;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@SuperBuilder
public class ReportGenerateEvent implements Serializable {

    @Serial
    private static final long serialVersionUID = -5362690614335988349L;

   
}