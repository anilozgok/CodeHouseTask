package org.anilcan.repository;

import org.anilcan.model.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneBookRepository extends JpaRepository<Contact, Long> {
    @Query(value = "SELECT * FROM contacts WHERE phoneNumber=?1", nativeQuery = true)
    Contact getContactByPhoneNumber(String phoneNumber);
}
