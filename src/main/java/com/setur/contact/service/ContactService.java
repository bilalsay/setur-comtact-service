package com.setur.contact.service;

import com.setur.contact.dto.request.CreateContactRequest;
import com.setur.contact.dto.request.UpdateContactRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactService {


    @Transactional
    public void createContact(CreateContactRequest createContactRequest) {

    }

    @Transactional
    public void updateContact(UUID contactId, UpdateContactRequest updateContactRequest) {

    }

    @Transactional
    public void deleteContact(Long contactId) {

    }
}
