package org.anilcan.service;

import org.anilcan.exception.ContactNotFoundException;
import org.anilcan.exception.InvalidPhoneNumberException;
import org.anilcan.model.entity.Contact;
import org.anilcan.model.request.EditContactRequest;
import org.anilcan.model.request.NewContactRequest;
import org.anilcan.repository.PhoneBookRepository;
import org.anilcan.utility.enums.ExceptionMessages;
import org.anilcan.utility.helpers.PhoneBookHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PhoneBookService {

    private final Logger logger = LoggerFactory.getLogger(PhoneBookService.class);
    private PhoneBookRepository phoneBookRepository;
    private PhoneBookHelper phoneBookHelper;

    public PhoneBookService(PhoneBookRepository phoneBookRepository, PhoneBookHelper phoneBookHelper) {
        this.phoneBookRepository = phoneBookRepository;
        this.phoneBookHelper = phoneBookHelper;
    }

    public Contact addContact(NewContactRequest newContactRequest) throws InvalidPhoneNumberException {

        logger.info("PBS - processing new contact request.");

        if (!phoneBookHelper.isPhoneNumberValid(newContactRequest.getPhoneNumber()))
            throw new InvalidPhoneNumberException(ExceptionMessages.INVALID_PHONE_NUMBER.message);

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

        logger.info("PBS - processing edit contact request.");

        if (!phoneBookHelper.isPhoneNumberValid(editContactRequest.getPhoneNumber()) ||
                !phoneBookHelper.isPhoneNumberValid(phoneNumberToSearch))
            throw new InvalidPhoneNumberException(ExceptionMessages.INVALID_PHONE_NUMBER.message);

        //TODO: throw contact not found if below method doesn't work
        Contact contactToUpdate = phoneBookRepository.getContactByPhoneNumber(phoneNumberToSearch);

        contactToUpdate.setFirstName(editContactRequest.getFirstName());
        contactToUpdate.setLastName(editContactRequest.getLastName());
        contactToUpdate.setPhoneNumber(editContactRequest.getPhoneNumber());
        contactToUpdate.setGender(editContactRequest.getGender());

        return phoneBookRepository.save(contactToUpdate);
    }

    public void deleteContact(String phoneNumber) throws InvalidPhoneNumberException, ContactNotFoundException {

        logger.info("PBS - processing delete contact request.");

        if (!phoneBookHelper.isPhoneNumberValid(phoneNumber))
            throw new InvalidPhoneNumberException(ExceptionMessages.INVALID_PHONE_NUMBER.message);

        //TODO: throw contact not found if below method doesn't work
        Contact contactToDelete = phoneBookRepository.getContactByPhoneNumber(phoneNumber);

        phoneBookRepository.delete(contactToDelete);
    }

}
