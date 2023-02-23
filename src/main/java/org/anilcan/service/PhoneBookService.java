package org.anilcan.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.anilcan.exception.ContactNotFoundException;
import org.anilcan.exception.InvalidPhoneNumberException;
import org.anilcan.model.entity.Contact;
import org.anilcan.model.request.EditContactRequest;
import org.anilcan.model.request.NewContactRequest;
import org.anilcan.repository.PhoneBookRepository;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor
public class PhoneBookService {

    private final PhoneBookRepository phoneBookRepository;

    public Contact addContact(NewContactRequest newContactRequest) throws InvalidPhoneNumberException {

        PhoneBookHelper phoneBookHelper = new PhoneBookHelper();

        log.info("PBS - processing new contact request.");

        if (!phoneBookHelper.isPhoneNumberValid(newContactRequest.getPhoneNumber()))
            throw new InvalidPhoneNumberException();

        Contact contact = Contact.builder()
                .firstName(newContactRequest.getFirstName())
                .lastName(newContactRequest.getLastName())
                .phoneNumber(newContactRequest.getPhoneNumber())
                .gender(newContactRequest.getGender())
                .build();

        return phoneBookRepository.save(contact);
    }

    public Contact editContact(EditContactRequest editContactRequest, String phoneNumberToSearch)
            throws InvalidPhoneNumberException, ContactNotFoundException {

        log.info("PBS - processing edit contact request.");

        PhoneBookHelper phoneBookHelper = new PhoneBookHelper();

        if (!phoneBookHelper.isPhoneNumberValid(editContactRequest.getPhoneNumber()) ||
                !phoneBookHelper.isPhoneNumberValid(phoneNumberToSearch))
            throw new InvalidPhoneNumberException();

        var optionalContact = phoneBookRepository.findByPhoneNumber(phoneNumberToSearch);

        if (optionalContact.isEmpty())
            throw new ContactNotFoundException();

        Contact contactToUpdate = optionalContact.get();

        contactToUpdate.setFirstName(editContactRequest.getFirstName());
        contactToUpdate.setLastName(editContactRequest.getLastName());
        contactToUpdate.setPhoneNumber(editContactRequest.getPhoneNumber());
        contactToUpdate.setGender(editContactRequest.getGender());

        return phoneBookRepository.save(contactToUpdate);
    }

    public void deleteContact(String phoneNumber) throws InvalidPhoneNumberException, ContactNotFoundException {

        log.info("PBS - processing delete contact request.");

        PhoneBookHelper phoneBookHelper = new PhoneBookHelper();

        if (!phoneBookHelper.isPhoneNumberValid(phoneNumber))
            throw new InvalidPhoneNumberException();

        var optionalContact = phoneBookRepository.findByPhoneNumber(phoneNumber);

        if (optionalContact.isEmpty())
            throw new ContactNotFoundException();

        Contact contactToDelete = optionalContact.get();

        phoneBookRepository.delete(contactToDelete);
    }

    private static class PhoneBookHelper {
        public boolean isPhoneNumberValid(String phoneNumber) {
            Pattern pattern = Pattern.compile("^(05)([0-9]{2})\\s?([0-9]{3})\\s?([0-9]{2})\\s?([0-9]{2})$");
            Matcher matcher = pattern.matcher(phoneNumber);
            return matcher.matches();
        }
    }
}
