package com.setur.contact.service;

import com.setur.contact.domain.repository.CommunicationRepository;
import com.setur.contact.domain.repository.ContactRepository;
import com.setur.contact.exception.CommunicationNotFoundException;
import com.setur.contact.exception.ContactNotFoundException;
import com.setur.contact.logger.TestLoggerExtension;
import com.setur.contact.mockito.MockitoExtension;
import com.setur.contact.scenario.ContactScenarios;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(TestLoggerExtension.class)
class ContactServiceTest {

    @InjectMocks
    private ContactService contactService;

    @Mock
    private ContactRepository contactRepository;

    @Mock
    private CommunicationRepository communicationRepository;


    @Test
    void shouldCreateContactSuccess_whenThereIsNoError() {
        //given
        var createContactRequest = ContactScenarios.getCreateContactRequest();
        var contactId = UUID.fromString("c41f534d-3f9b-49ba-8e84-b35263c962cb");
        var contact = ContactScenarios.getContact(null);
        var communications = ContactScenarios.getCommunicationList();
        communications.get(0).getContact().setId(null);
        communications.get(1).getContact().setId(null);

        //when
        when(contactRepository.save(contact))
                .thenReturn(contact);
        when(communicationRepository.save(communications.get(0)))
                .thenReturn(communications.get(0));
        when(communicationRepository.save(communications.get(1)))
                .thenReturn(communications.get(1));

        //then
        contactService.createContact(createContactRequest);

        verify(contactRepository, times(1)).save(contact);
        verify(communicationRepository, times(1)).save(communications.get(0));
        verify(communicationRepository, times(1)).save(communications.get(1));
    }

    @Test
    void shouldUpdateContactSuccess_whenThereIsNoError() {
        //given
        var updateContactRequest = ContactScenarios.getUpdateContactRequest();
        var contactId = UUID.fromString("c41f534d-3f9b-49ba-8e84-b35263c962cb");
        var contact = ContactScenarios.getContact(contactId);
        var communications = ContactScenarios.getCommunicationList();
        var communicationDtos = ContactScenarios.getCommunicationDtoList();
        communicationDtos.get(1).setId(null);
        updateContactRequest.getCommunications().get(1).setId(null);

        //when
        when(contactRepository.findById(contactId))
                .thenReturn(Optional.ofNullable(contact));
        when(communicationRepository.findById(communicationDtos.get(0).getId()))
                .thenReturn(Optional.ofNullable(communications.get(0)));

        //then
        contactService.updateContact(contactId, updateContactRequest);

        verify(contactRepository, times(1)).findById(contactId);
        verify(communicationRepository, times(1)).findById(communicationDtos.get(0).getId());
    }

    @Test
    void shouldNotUpdateContactSuccess_whenContactNotFoundException() {
        //given
        var updateContactRequest = ContactScenarios.getUpdateContactRequest();
        var contactId = UUID.fromString("c41f534d-3f9b-49ba-8e84-b35263c962cb");
        var contact = ContactScenarios.getContact(contactId);
        var communications = ContactScenarios.getCommunicationList();
        var communicationDtos = ContactScenarios.getCommunicationDtoList();
        communicationDtos.get(1).setId(null);

        //when
        when(contactRepository.findById(contactId))
                .thenReturn(Optional.empty());
        when(communicationRepository.findById(communicationDtos.get(0).getId()))
                .thenReturn(Optional.ofNullable(communications.get(0)));

        //then
        assertThrows(ContactNotFoundException.class, () -> contactService.updateContact(contactId, updateContactRequest));

        verify(contactRepository, times(1)).findById(contactId);
        verify(communicationRepository, never()).findById(communicationDtos.get(0).getId());
    }

    @Test
    void shouldDeleteContactSuccess_whenThereIsNoError() {
        //given
        var contactId = UUID.fromString("c41f534d-3f9b-49ba-8e84-b35263c962cb");
        var contact = ContactScenarios.getContact(contactId);
        var communications = ContactScenarios.getCommunicationList();

        //when
        when(communicationRepository.findAllByContactId(contactId))
                .thenReturn(communications);
        doNothing().when(communicationRepository).deleteById(communications.get(0).getId());
        doNothing().when(communicationRepository).deleteById(communications.get(1).getId());
        doNothing().when(contactRepository).deleteById(contactId);

        //then
        contactService.deleteContact(contactId);

        verify(communicationRepository, times(1)).findAllByContactId(contactId);
        verify(communicationRepository, times(2)).deleteById(communications.get(0).getId());
        verify(contactRepository, times(1)).deleteById(contactId);
    }

    @Test
    void shouldGetContactListSuccess_whenThereIsNoError() {
        //given
        var contactId = UUID.fromString("c41f534d-3f9b-49ba-8e84-b35263c962cb");
        var contact = ContactScenarios.getContact(contactId);
        var contactDtoList = ContactScenarios.getContactDtoList(contactId);
        var contactList = ContactScenarios.getContactList(contactId);
        var communications = ContactScenarios.getCommunicationList();

        //when
        when(contactRepository.findAllContact())
                .thenReturn(contactDtoList);

        //then
        var response = contactService.getContacts();
        assertEquals(2, response.size());

        verify(contactRepository, times(1)).findAllContact();
    }

    @Test
    void shouldGetContactByIdSuccess_whenThereIsNoError() {
        //given
        var contactId = UUID.fromString("c41f534d-3f9b-49ba-8e84-b35263c962cb");
        var contact = ContactScenarios.getContact(contactId);
        var contactDtoList = ContactScenarios.getContactDtoList(contactId);
        var contactList = ContactScenarios.getContactList(contactId);
        var communications = ContactScenarios.getCommunicationList();
        var contactResponse = ContactScenarios.getContactResponse(contactId);

        //when
        when(contactRepository.findById(contactId))
                .thenReturn(Optional.ofNullable(contact));
        when(communicationRepository.findAllByContactId(contactId))
                .thenReturn(communications);

        //then
        var response = contactService.getContact(contactId);
        assertEquals(response.getName(), contactResponse.getName());

        verify(contactRepository, times(1)).findById(contactId);
        verify(communicationRepository, times(1)).findAllByContactId(contactId);
    }
}