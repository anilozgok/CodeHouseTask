package org.anilcan.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.anilcan.exception.ContactNotFoundException;
import org.anilcan.exception.InvalidPhoneNumberException;
import org.anilcan.model.domain.ContactRecord;
import org.anilcan.model.domain.Phone;
import org.anilcan.model.entity.Contact;
import org.anilcan.model.request.EditContactRequest;
import org.anilcan.model.request.NewContactRequest;
import org.anilcan.repository.PhoneBookRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PhoneBookService {

    // TODO: bknz: https://stackoverflow.com/questions/53966427/should-service-layer-accept-a-dto-or-a-custom-request-object-from-the-controller
    // TODO: implement getDto and getResponse methods (mappers)
    private final PhoneBookRepository phoneBookRepository;

    public ImmutablePair<Long, ContactRecord> addContact(NewContactRequest newContactRequest) {

        log.info("PBS - processing new contact request.");

        var phone = Phone.valueOf(newContactRequest.info().phone().number());

        if (!phone.isValid()) throw new InvalidPhoneNumberException();

        var contact = Contact.builder()
                             .firstName(newContactRequest.info().firstName())
                             .lastName(newContactRequest.info().lastName())
                             .phoneNumber(phone.number())
                             .gender(newContactRequest.info().gender())
                             .build();

        var savedContact = phoneBookRepository.save(contact);
        var retVal = new ContactRecord(savedContact.getFirstName(),
                                       savedContact.getLastName(),
                                       Phone.valueOf(savedContact.getPhoneNumber()),
                                       savedContact.getGender());

        return ImmutablePair.of(savedContact.getId(), retVal);
    }

    @Transactional
    public ImmutablePair<Long, ContactRecord> editContact(EditContactRequest editContactRequest, String phoneNumberToSearch) {

        log.info("PBS - processing edit contact request.");

        var numberToSearch = Phone.valueOf(phoneNumberToSearch);
        var editedPhone = Phone.valueOf(editContactRequest.info().phone().number());

        if (!bothPhoneNumbersValid(numberToSearch, editedPhone)) throw new InvalidPhoneNumberException();

        var contactToUpdate = phoneBookRepository.findByPhoneNumber(numberToSearch.number())
                                                 .orElseThrow(ContactNotFoundException::new);

        contactToUpdate.setFirstName(editContactRequest.info().firstName());
        contactToUpdate.setLastName(editContactRequest.info().lastName());
        contactToUpdate.setPhoneNumber(editedPhone.number());
        contactToUpdate.setGender(editContactRequest.info().gender());

        var updatedContact = phoneBookRepository.save(contactToUpdate);
        var retVal = new ContactRecord(updatedContact.getFirstName(),
                                       updatedContact.getLastName(),
                                       Phone.valueOf(updatedContact.getPhoneNumber()),
                                       updatedContact.getGender());

        return ImmutablePair.of(updatedContact.getId(), retVal);
    }

    @Transactional
    public void deleteContact(String phoneNumber) {

        log.info("PBS - processing delete contact request.");

        var phone = Phone.valueOf(phoneNumber);

        if (!phone.isValid()) throw new InvalidPhoneNumberException();

        var optionalContact = phoneBookRepository.findByPhoneNumber(phone.number());
        var contactToDelete = optionalContact.orElseThrow(ContactNotFoundException::new);

        phoneBookRepository.delete(contactToDelete);
    }

    public ContactRecord getContactByPhoneNumber(String phoneNumber) {

        log.info("PBS - processing get contact by phone number request.");

        var phone = Phone.valueOf(phoneNumber);

        if (!phone.isValid()) throw new InvalidPhoneNumberException();

        var contact = phoneBookRepository.findByPhoneNumber(phone.number()).orElseThrow(ContactNotFoundException::new);

        return new ContactRecord(contact.getFirstName(),
                                 contact.getLastName(),
                                 Phone.valueOf(contact.getPhoneNumber()),
                                 contact.getGender());

    }

    public List<ContactRecord> getAllContacts() {

        log.info("PBS - processing get all contacts request");

        return phoneBookRepository.findAll()
                                  .stream()
                                  .map(contact -> new ContactRecord(contact.getFirstName(),
                                                                    contact.getLastName(),
                                                                    Phone.valueOf(contact.getPhoneNumber()),
                                                                    contact.getGender()))
                                  .collect(Collectors.toList());
    }

    private boolean bothPhoneNumbersValid(Phone oldPhone, Phone newPhone) {

        return oldPhone.isValid() || newPhone.isValid();
    }

}