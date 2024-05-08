package com.setur.contact.service;

import com.setur.contact.domain.entity.Communication;
import com.setur.contact.domain.entity.Contact;
import com.setur.contact.domain.repository.CommunicationRepository;
import com.setur.contact.domain.repository.ContactRepository;
import com.setur.contact.dto.CommunicationDto;
import com.setur.contact.dto.request.CreateCommunicationRequest;
import com.setur.contact.dto.request.CreateContactRequest;
import com.setur.contact.dto.request.UpdateCommunicationRequest;
import com.setur.contact.exception.CommunicationNotFoundException;
import com.setur.contact.exception.ContactNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommunicationService {

    private final ContactRepository contactRepository;

    private final CommunicationRepository communicationRepository;

    @Transactional
    public void createCommunication(CreateCommunicationRequest request) {
        var contact = contactRepository.findById(request.getContactId())
                .orElseThrow(() -> new ContactNotFoundException(request.getContactId() + " id li kişi bulunamadı."));

        var communication = buildCommunication(contact, request);
        communicationRepository.save(communication);
    }


    @Transactional
    public void updateCommunication(UUID communicationId, UpdateCommunicationRequest request) {
        var communication = communicationRepository.findById(communicationId)
                .orElseThrow(() -> new CommunicationNotFoundException(communicationId + " id li iletişim bilgisi bulunamadı."));
        communication.setType(request.getType());
        communication.setValue(request.getValue());
    }

    @Transactional
    public void deleteCommunication(UUID communicationId) {
        communicationRepository.deleteById(communicationId);
    }

    private Communication buildCommunication(Contact contact, CreateCommunicationRequest request) {
        return Communication.builder()
                .type(request.getType())
                .value(request.getValue())
                .contact(contact)
                .build();
    }
}
