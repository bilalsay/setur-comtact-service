package com.setur.contact.scenario;

import com.setur.contact.domain.entity.Communication;
import com.setur.contact.domain.entity.Contact;
import com.setur.contact.dto.CommunicationDto;
import com.setur.contact.dto.ContactDto;
import com.setur.contact.dto.request.CreateContactRequest;
import com.setur.contact.dto.request.UpdateContactRequest;
import com.setur.contact.dto.response.ContactResponse;
import com.setur.contact.enums.CommunicationType;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.UUID;

@UtilityClass
public class ContactScenarios {

    public static ContactResponse getContactResponse(UUID contactId) {
        var contact = getContact(contactId);
        return ContactResponse.builder()
                .id(contact.getId())
                .name(contact.getName())
                .surname(contact.getSurname())
                .company(contact.getCompany())
                .communications(getCommunicationDtoList())
                .build();
    }

    public static CreateContactRequest getCreateContactRequest() {
        return CreateContactRequest.builder()
                .name("name")
                .surname("surname")
                .company("company")
                .communications(getCommunicationDtoList())
                .build();
    }

    public static Contact getContact(UUID id) {
        return Contact.builder()
                .id(id)
                .name("name")
                .surname("surname")
                .company("company")
                .build();
    }

    public static List<Contact> getContactList(UUID id) {
        return List.of(getContact(id), getContact(id));
    }

    public static ContactDto getContactDto(UUID id) {
        return ContactDto.builder()
                .id(id)
                .name("name")
                .surname("surname")
                .company("company")
                .build();
    }

    public static List<ContactDto> getContactDtoList(UUID id) {
        return List.of(getContactDto(id), getContactDto(id));
    }


    public static Communication getCommunication(CommunicationType communicationType, UUID id) {
        var contactId = UUID.fromString("c41f534d-3f9b-49ba-8e84-b35263c962cb");
        return Communication.builder()
                .type(communicationType)
                .value(communicationType.name())
                .contact(getContact(contactId))
                .build();
    }

    public static List<Communication> getCommunicationList() {
        var id = UUID.fromString("b9e5dbea-ed06-4c6b-9150-19779f57c3af");
        var id2 = UUID.fromString("b9e5dbea-ed06-4c6b-9150-19779f57c3ad");
        return List.of(getCommunication(CommunicationType.EMAIL, id), getCommunication(CommunicationType.PHONE, id2));
    }

    public static CommunicationDto getCommunicationDto(CommunicationType communicationType, UUID id) {
        return CommunicationDto.builder()
                .id(id)
                .type(communicationType)
                .value(communicationType.name())
                .build();
    }

    public static List<CommunicationDto> getCommunicationDtoList() {
        var id = UUID.fromString("b9e5dbea-ed06-4c6b-9150-19779f57c3af");
        var id2 = UUID.fromString("b9e5dbea-ed06-4c6b-9150-19779f57c3ad");
        return List.of(getCommunicationDto(CommunicationType.EMAIL, id), getCommunicationDto(CommunicationType.PHONE, id2));
    }

    public static UpdateContactRequest getUpdateContactRequest() {
        return UpdateContactRequest.builder()
                .name("name")
                .surname("surname")
                .company("company")
                .communications(getCommunicationDtoList())
                .build();
    }
}
