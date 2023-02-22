package org.anilcan.repository;

import org.anilcan.model.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PhoneBookRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> findByPhoneNumber(String phoneNumber);
}
