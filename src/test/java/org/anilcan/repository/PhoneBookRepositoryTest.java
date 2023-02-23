package org.anilcan.repository;

import org.anilcan.model.entity.Contact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class PhoneBookRepositoryTest {

    @Autowired
    private PhoneBookRepository phoneBookRepository;

    Contact contact;

    @BeforeEach
    public void setup() {
        contact = Contact.builder()
                .id(1L)
                .firstName("Anıl")
                .lastName("Can")
                .phoneNumber("0555 555 5555")
                .build();
    }

    @Test
    void save() {

    }

    @Test
    void delete() {

    }

    @Test
    void findByPhoneNumber() {

    }
}