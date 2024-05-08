package com.setur.contact.domain.repository;

import com.setur.contact.domain.entity.Communication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommunicationRepository extends JpaRepository<Communication, UUID> {

    List<Communication> findAllByContactId(UUID contactId);
}
