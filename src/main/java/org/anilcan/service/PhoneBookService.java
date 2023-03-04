package org.anilcan.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.anilcan.exception.ContactNotFoundException;
import org.anilcan.exception.InvalidPhoneNumberException;
import org.anilcan.model.domain.ContactRecord;
import org.anilcan.model.domain.Phone;
import org.anilcan.model.dto.request.EditContactRequest;
import org.anilcan.model.entity.Contact;
import org.anilcan.repository.PhoneBookRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PhoneBookService {
    private final PhoneBookRepository phoneBookRepository;

    public ImmutablePair<Long, ContactRecord> addContact(ContactRecord newContact) {

        log.info("PBS - processing new contact request.");

        if (!newContact.phone().isValid())
            throw new InvalidPhoneNumberException();

        var contact = Contact.builder()
                .firstName(newContact.firstName())
                .lastName(newContact.lastName())
                .phoneNumber(newContact.phone().number())
                .gender(newContact.gender())
                .build();

        var savedContact = phoneBookRepository.save(contact);
        var retVal = new ContactRecord(savedContact.getFirstName(), savedContact.getLastName(), new Phone(savedContact.getPhoneNumber()), savedContact.getGender());

        return ImmutablePair.of(savedContact.getId(), retVal);
    }

    @Transactional
    public ImmutablePair<Long, ContactRecord> editContact(EditContactRequest editContactRequest, String phoneNumberToSearch) {

        log.info("PBS - processing edit contact request.");

        var oldPhoneNumber = new Phone(phoneNumberToSearch);

        if (!bothPhoneNumbersValid(oldPhoneNumber, editContactRequest.info().phone()))
            throw new InvalidPhoneNumberException();

        var optionalContact = phoneBookRepository.findByPhoneNumber(phoneNumberToSearch);
        var contactToUpdate = optionalContact.orElseThrow(ContactNotFoundException::new);

        contactToUpdate.setFirstName(editContactRequest.info().firstName());
        contactToUpdate.setLastName(editContactRequest.info().lastName());
        contactToUpdate.setPhoneNumber(editContactRequest.info().phone().number());
        contactToUpdate.setGender(editContactRequest.info().gender());

        var updatedContact = phoneBookRepository.save(contactToUpdate);
        var retVal = new ContactRecord(updatedContact.getFirstName(), updatedContact.getLastName(), new Phone(updatedContact.getPhoneNumber()), updatedContact.getGender());

        return ImmutablePair.of(updatedContact.getId(), retVal);
    }

    @Transactional
    public void deleteContact(String phoneNumber) {

        log.info("PBS - processing delete contact request.");

        var phone = new Phone(phoneNumber);

        if (!phone.isValid())
            throw new InvalidPhoneNumberException();

        var optionalContact = phoneBookRepository.findByPhoneNumber(phoneNumber);
        var contactToDelete = optionalContact.orElseThrow(ContactNotFoundException::new);

        phoneBookRepository.delete(contactToDelete);
    }

    private boolean bothPhoneNumbersValid(Phone oldPhone, Phone newPhone) {
        return oldPhone.isValid() || newPhone.isValid();
    }
}