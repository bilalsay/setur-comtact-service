package com.setur.contact.scenario;

import com.setur.contact.dto.request.CreateContactRequest;
import com.setur.contact.dto.request.UpdateContactRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ContactScenarios {

    public static CreateContactRequest getCreateContactRequest() {
        return CreateContactRequest.builder()
                .build();
    }

    public static UpdateContactRequest getUpdateContactRequest() {
        return UpdateContactRequest.builder()
                .build();
    }
}
