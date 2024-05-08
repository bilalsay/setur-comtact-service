package com.setur.contact.domain.repository;

import com.setur.contact.domain.entity.Contact;
import com.setur.contact.dto.ContactDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ContactRepository extends JpaRepository<Contact, UUID> {

    @Query(value = """
            select new com.setur.contact.dto.ContactDto(c.id, c.name, c.surname, c.company)
            from Contact c
            """)
    List<ContactDto> findAllContact();
}
