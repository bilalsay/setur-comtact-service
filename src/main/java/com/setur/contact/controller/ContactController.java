package com.setur.contact.controller;

import com.setur.contact.dto.ContactDto;
import com.setur.contact.dto.request.CreateContactRequest;
import com.setur.contact.dto.request.UpdateContactRequest;
import com.setur.contact.dto.response.ContactResponse;
import com.setur.contact.service.ContactService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping
    public void createContact(@Valid @RequestBody CreateContactRequest request) {
        contactService.createContact(request);
    }

    @PutMapping("/{id}")
    public void updateContact(@PathVariable("id") @NotNull UUID id, @Valid @RequestBody UpdateContactRequest request) {
        contactService.updateContact(id, request);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteContact(@PathVariable("id") UUID id) {
        contactService.deleteContact(id);
    }

    @GetMapping
    public List<ContactDto> getContacts() {
        return contactService.getContacts();
    }

    @GetMapping(path = "/{id}")
    public ContactResponse getContact(@PathVariable("id") UUID id) {
        return contactService.getContact(id);
    }

}
