package com.setur.contact.controller;

import com.setur.contact.dto.request.CreateCommunicationRequest;
import com.setur.contact.dto.request.UpdateCommunicationRequest;
import com.setur.contact.service.CommunicationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/communications")
@RequiredArgsConstructor
@Validated
public class CommunicationController {

    private final CommunicationService communicationService;

    @PostMapping
    public void createCommunication(@Valid @RequestBody CreateCommunicationRequest request) {
        communicationService.createCommunication(request);
    }

    @PutMapping("/{id}")
    public void updateCommunication(@PathVariable("id") @NotNull UUID id, @Valid @RequestBody UpdateCommunicationRequest request) {
        communicationService.updateCommunication(id, request);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteCommunication(@PathVariable("id") UUID id) {
        communicationService.deleteCommunication(id);
    }

}
