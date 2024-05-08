package com.setur.contact.service;

import com.setur.contact.domain.entity.Communication;
import com.setur.contact.domain.entity.Contact;
import com.setur.contact.domain.repository.CommunicationRepository;
import com.setur.contact.domain.repository.ContactRepository;
import com.setur.contact.dto.CommunicationDto;
import com.setur.contact.dto.ContactDto;
import com.setur.contact.dto.request.CreateContactRequest;
import com.setur.contact.dto.request.UpdateContactRequest;
import com.setur.contact.dto.response.ContactResponse;
import com.setur.contact.exception.ContactNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactService {

    private final ContactRepository contactRepository;

    private final CommunicationRepository communicationRepository;

    @Transactional
    public void createContact(CreateContactRequest request) {
        var contact = buildContact(request);
        contactRepository.save(contact);
        request.getCommunications().forEach(communicationDto -> {
            var communication = buildCommunication(contact, communicationDto);
            communicationRepository.save(communication);
        });
    }

    @Transactional
    public void updateContact(UUID contactId, UpdateContactRequest request) {
        var contact = getContactById(contactId);
        contact.setName(request.getName());
        contact.setSurname(request.getSurname());
        contact.setCompany(request.getCompany());

        request.getCommunications().forEach(communicationDto -> {
            if (Objects.isNull(communicationDto.getId())) {
                var communication = buildCommunication(contact, communicationDto);
                communicationRepository.save(communication);
                return;
            }

            communicationRepository.findById(communicationDto.getId())
                    .ifPresent(communication -> updateCommunication(communication, communicationDto));
        });
    }

    private Contact getContactById(UUID contactId) {
        return contactRepository.findById(contactId)
                .orElseThrow(() -> new ContactNotFoundException(contactId + " id li kişi bulunamadı."));
    }

    @Transactional
    public void deleteContact(UUID contactId) {
        communicationRepository.findAllByContactId(contactId).stream()
                .map(Communication::getId)
                .forEach(communicationRepository::deleteById);
        contactRepository.deleteById(contactId);
    }

    public List<ContactDto> getContacts() {
        return contactRepository.findAllContact();
    }

    public ContactResponse getContact(UUID id) {
        var contact = getContactById(id);
        var communications = communicationRepository.findAllByContactId(contact.getId())
                .stream()
                .map(communication -> CommunicationDto.builder()
                        .id(communication.getId())
                        .type(communication.getType())
                        .value(communication.getValue())
                        .build())
                .toList();
        return ContactResponse.builder()
                .id(contact.getId())
                .name(contact.getName())
                .surname(contact.getSurname())
                .company(contact.getCompany())
                .communications(communications)
                .build();

    }

    private Communication buildCommunication(Contact contact, CommunicationDto communicationDto) {
        return Communication.builder()
                .type(communicationDto.getType())
                .value(communicationDto.getValue())
                .contact(contact)
                .build();
    }

    private void updateCommunication(Communication communication, CommunicationDto communicationDto) {
        communication.setType(communicationDto.getType());
        communication.setValue(communicationDto.getValue());
    }

    private Contact buildContact(CreateContactRequest request) {
        return Contact.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .company(request.getCompany())
                .build();
    }


}
