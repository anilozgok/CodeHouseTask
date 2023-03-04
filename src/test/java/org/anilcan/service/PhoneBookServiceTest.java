package org.anilcan.service;

import org.anilcan.model.domain.ContactRecord;
import org.anilcan.model.domain.Phone;
import org.anilcan.model.dto.request.EditContactRequest;
import org.anilcan.model.entity.Contact;
import org.anilcan.repository.PhoneBookRepository;
import org.anilcan.utility.Gender;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.Assertions;
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

    private final ContactRecord contact1 = new ContactRecord("Anıl", "Can", Phone.valueOf("0555 555 5555"), Gender.MALE);
    private final ContactRecord contact2 = new ContactRecord("Anıl Can", "Özgök", Phone.valueOf("0555 555 5555"), Gender.MALE);

    @Test
    void addContact() {

        // Given
        var contact = Contact.builder()
                .id(1L)
                .firstName("Anıl")
                .lastName("Can")
                .phoneNumber("0555 555 5555")
                .gender(Gender.MALE)
                .build();

        when(phoneBookRepository.save(Mockito.any()))
                .thenReturn(contact);

        var expected = new ImmutablePair<>(contact.getId(), new ContactRecord(contact.getFirstName(), contact.getLastName(), Phone.valueOf(contact.getPhoneNumber()), contact.getGender()));

        // When
        var result = phoneBookService.addContact(contact1);

        // Then
        Assertions.assertEquals(expected, result);
    }

    @Test
    void editContact() {

        // Given
        var contact = Contact.builder()
                .id(1L)
                .firstName("Anıl Can")
                .lastName("Özgök")
                .phoneNumber("0555 555 5555")
                .gender(Gender.MALE)
                .build();

        when(phoneBookRepository.save(Mockito.any()))
                .thenReturn(contact);

        when(phoneBookRepository.findByPhoneNumber(Mockito.any()))
                .thenReturn(Optional.of(contact));

        var expected = new ImmutablePair<>(contact.getId(), new ContactRecord(contact.getFirstName(), contact.getLastName(), Phone.valueOf(contact.getPhoneNumber()), contact.getGender()));

        // When
        var result = phoneBookService.editContact(new EditContactRequest(contact2), contact.getPhoneNumber());

        // Then
        Assertions.assertEquals(expected, result);

    }

    @Test
    void deleteContact() {

        // Given
        var contact = Contact.builder()
                .id(1L)
                .firstName("Anıl")
                .lastName("Can")
                .phoneNumber("0555 555 5555")
                .gender(Gender.MALE)
                .build();

        when(phoneBookRepository.findByPhoneNumber(Mockito.any()))
                .thenReturn(Optional.of(contact));

        // When
        phoneBookService.deleteContact(contact.getPhoneNumber());

        // Then
        verify(phoneBookRepository, times(1)).delete(contact);
    }
}