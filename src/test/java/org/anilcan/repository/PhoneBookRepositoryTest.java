package org.anilcan.repository;

import org.anilcan.model.entity.Contact;
import org.anilcan.utility.Gender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class PhoneBookRepositoryTest {

    @Autowired
    private PhoneBookRepository phoneBookRepository;

    @Test
    void save() {
        // Given
        var contact = Contact.builder()
                .id(1L)
                .firstName("Anıl")
                .lastName("Can")
                .phoneNumber("0555 555 5555")
                .gender(Gender.MALE)
                .build();

        // When
        var result=phoneBookRepository.save(contact);

        // Then
        assertEquals(contact.getPhoneNumber(), result.getPhoneNumber());
    }

    @Test
    void delete() {
        // Given
        var contact = Contact.builder()
                .id(1L)
                .firstName("Anıl")
                .lastName("Can")
                .phoneNumber("0555 555 5555")
                .gender(Gender.MALE)
                .build();

        phoneBookRepository.save(contact);

        // When
        phoneBookRepository.delete(contact);
        var result = phoneBookRepository.findById(contact.getId());

        // Then
        assertTrue(result.isEmpty());

    }

    @Test
    void findByPhoneNumber() {
        // Given
        var contact = Contact.builder()
                .id(1L)
                .firstName("Anıl")
                .lastName("Can")
                .phoneNumber("0555 555 5555")
                .gender(Gender.MALE)
                .build();

        phoneBookRepository.save(contact);

        // When
        var result = phoneBookRepository.findByPhoneNumber(contact.getPhoneNumber());

        // Then
        assertTrue(result.isPresent());
        assertEquals(contact.getPhoneNumber(), result.get().getPhoneNumber());
    }
}