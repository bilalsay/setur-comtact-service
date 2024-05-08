package com.setur.contact.scenario;

import com.setur.contact.domain.entity.Communication;
import com.setur.contact.domain.entity.Contact;
import com.setur.contact.dto.CommunicationDto;
import com.setur.contact.dto.ContactDto;
import com.setur.contact.dto.request.CreateCommunicationRequest;
import com.setur.contact.dto.request.CreateContactRequest;
import com.setur.contact.dto.request.UpdateCommunicationRequest;
import com.setur.contact.dto.request.UpdateContactRequest;
import com.setur.contact.dto.response.ContactResponse;
import com.setur.contact.enums.CommunicationType;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.UUID;

@UtilityClass
public class CommunicationScenarios {

    public static CreateCommunicationRequest getCreateCommunicationRequest(UUID contactId) {
        return CreateCommunicationRequest.builder()
                .contactId(contactId)
                .type(CommunicationType.EMAIL)
                .value(CommunicationType.EMAIL.name())
                .build();
    }

    public static UpdateCommunicationRequest getUpdateCommunicationRequest() {
        return UpdateCommunicationRequest.builder()
                .type(CommunicationType.EMAIL)
                .value(CommunicationType.EMAIL.name())
                .build();
    }

}
