package com.setur.contact.service;

import com.setur.contact.domain.repository.CommunicationRepository;
import com.setur.contact.domain.repository.ContactRepository;
import com.setur.contact.exception.CommunicationNotFoundException;
import com.setur.contact.exception.ContactNotFoundException;
import com.setur.contact.logger.TestLoggerExtension;
import com.setur.contact.mockito.MockitoExtension;
import com.setur.contact.scenario.CommunicationScenarios;
import com.setur.contact.scenario.ContactScenarios;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(TestLoggerExtension.class)
class CommunicationServiceTest {

    @InjectMocks
    private CommunicationService communicationService;

    @Mock
    private ContactRepository contactRepository;

    @Mock
    private CommunicationRepository communicationRepository;


    @Test
    void shouldCreateCommunicationSuccess_whenThereIsNoError() {
        //given
        var contactId = UUID.fromString("c41f534d-3f9b-49ba-8e84-b35263c962cb");
        var createCommunicationRequest = CommunicationScenarios.getCreateCommunicationRequest(contactId);
        var contact = ContactScenarios.getContact(contactId);
        var communications = ContactScenarios.getCommunicationList();

        //when
        when(contactRepository.findById(contactId))
                .thenReturn(Optional.ofNullable(contact));
        when(communicationRepository.save(communications.get(0)))
                .thenReturn(communications.get(0));

        //then
        communicationService.createCommunication(createCommunicationRequest);

        verify(contactRepository, times(1)).findById(contactId);
        verify(communicationRepository, times(1)).save(communications.get(0));
    }

    @Test
    void shouldUpdateCommunicationSuccess_whenThereIsNoError() {
        //given
        var updateCommunicationRequest = CommunicationScenarios.getUpdateCommunicationRequest();
        var communicationId = UUID.fromString("c41f534d-3f9b-49ba-8e84-b35263c962cb");
        var communications = ContactScenarios.getCommunicationList();
        var communicationDtos = ContactScenarios.getCommunicationDtoList();
        communicationDtos.get(1).setId(null);

        //when
        when(communicationRepository.findById(communicationId))
                .thenReturn(Optional.ofNullable(communications.get(0)));

        //then
        communicationService.updateCommunication(communicationId, updateCommunicationRequest);

        verify(communicationRepository, times(1)).findById(communicationId);
    }

    @Test
    void shouldNotUpdateCommunicationSuccess_whenCommunicationNotFoundException() {
        //given
        var updateCommunicationRequest = CommunicationScenarios.getUpdateCommunicationRequest();
        var communicationId = UUID.fromString("c41f534d-3f9b-49ba-8e84-b35263c962cb");
        var communications = ContactScenarios.getCommunicationList();
        var communicationDtos = ContactScenarios.getCommunicationDtoList();
        communicationDtos.get(1).setId(null);

        //when
        when(communicationRepository.findById(communicationId))
                .thenReturn(Optional.empty());

        //then
        assertThrows(CommunicationNotFoundException.class, () -> communicationService.updateCommunication(communicationId, updateCommunicationRequest));

        verify(communicationRepository, times(1)).findById(communicationId);
    }

    @Test
    void shouldDeleteCommunicationSuccess_whenThereIsNoError() {
        //given
        var communicationId = UUID.fromString("c41f534d-3f9b-49ba-8e84-b35263c962cb");

        //when
        doNothing().when(communicationRepository).deleteById(communicationId);

        //then
        communicationService.deleteCommunication(communicationId);

        verify(communicationRepository, times(1)).deleteById(communicationId);
    }
}