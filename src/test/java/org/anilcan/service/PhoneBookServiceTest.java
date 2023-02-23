package org.anilcan.service;

import org.anilcan.exception.ContactNotFoundException;
import org.anilcan.model.entity.Contact;
import org.anilcan.model.request.EditContactRequest;
import org.anilcan.model.request.NewContactRequest;
import org.anilcan.repository.PhoneBookRepository;
import org.anilcan.utility.Gender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
class PhoneBookServiceTest {

    @InjectMocks
    private PhoneBookService phoneBookService;

    @Mock
    private PhoneBookRepository phoneBookRepository;

    private Contact contact;

    @BeforeEach
    public void setup() {
        contact = Contact.builder()
                .id(1L)
                .firstName("Anıl")
                .lastName("Can")
                .phoneNumber("0555 555 5555")
                .build();
    }


    private final NewContactRequest newContactRequest = new NewContactRequest(
            "Anıl", "Can", "0555 555 5555", Gender.MALE);

    private final EditContactRequest editContactRequest = new EditContactRequest(
            "Anıl Can", "Özgök", "0555 555 5555", Gender.MALE);

    @Test
    void addContact() {

        when(phoneBookRepository.save(Mockito.any()))
                .thenReturn(contact);


        Assertions.assertEquals(contact, phoneBookService.addContact(newContactRequest));
    }

    @Test
    void editContact() {

        contact = Contact.builder()
                .id(1L)
                .firstName("Anıl Can")
                .lastName("Özgök")
                .phoneNumber("0555 555 5555")
                .build();

        when(phoneBookRepository.save(Mockito.any()))
                .thenReturn(contact);

        when(phoneBookRepository.findByPhoneNumber(Mockito.any()))
                .thenReturn(Optional.of(contact));

        Assertions.assertEquals(contact, phoneBookService.editContact(editContactRequest, contact.getPhoneNumber()));

    }

    @Test
    void deleteContact() {

        when(phoneBookRepository.findByPhoneNumber(Mockito.any()))
                .thenReturn(Optional.of(contact));

        phoneBookService.deleteContact(contact.getPhoneNumber());

        verify(phoneBookRepository, times(1)).delete(contact);
    }
}