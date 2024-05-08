package com.setur.contact.service;

import com.setur.contact.domain.repository.ContactRepository;
import com.setur.contact.infrastructure.configuration.EnvironmentConfig;
import com.setur.contact.logger.TestLoggerExtension;
import com.setur.contact.mockito.MockitoExtension;
import com.setur.contact.scenario.ContactScenarios;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@ExtendWith(TestLoggerExtension.class)
class ContactServiceTest {

    @InjectMocks
    private ContactService slotService;

    @Mock
    private ContactRepository contactRepository;

    @Mock
    private EnvironmentConfig environmentConfig;


    @Test
    void shouldCreateSuccess() {
        //given
        var createContactRequest = ContactScenarios.getCreateContactRequest();

        //when


        //then
        slotService.createContact(createContactRequest);
    }

    @Test
    void shouldUpdateSuccess() {
        //given
        var updateContactRequest = ContactScenarios.getUpdateContactRequest();
        var contactId = UUID.fromString("c41f534d-3f9b-49ba-8e84-b35263c962cb");

        //when


        //then
        slotService.updateContact(contactId, updateContactRequest);
    }
}