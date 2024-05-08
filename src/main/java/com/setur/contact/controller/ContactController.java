package com.setur.contact.controller;

import com.setur.contact.dto.request.CreateContactRequest;
import com.setur.contact.dto.request.UpdateContactRequest;
import com.setur.contact.service.ContactService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping
    public void createContact(@Valid @RequestBody CreateContactRequest createContactRequest) {
        contactService.createContact(createContactRequest);
    }

    @PutMapping("/{id}")
    public void updateContact(@PathVariable("id") @NotNull UUID id, @Valid @RequestBody UpdateContactRequest updateContactRequest) {
        contactService.updateContact(id, updateContactRequest);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") Long id) {

    }

}
