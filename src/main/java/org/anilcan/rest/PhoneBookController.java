package org.anilcan.rest;

import lombok.extern.slf4j.Slf4j;
import org.anilcan.model.entity.Contact;
import org.anilcan.model.request.EditContactRequest;
import org.anilcan.model.request.NewContactRequest;
import org.anilcan.model.response.ContactEditedResponse;
import org.anilcan.model.response.ContactSavedResponse;
import org.anilcan.service.PhoneBookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@Slf4j
@RestController
@RequestMapping("/api/phone-book/contact/")
public class PhoneBookController {

    private PhoneBookService phoneBookService;

    public PhoneBookController(PhoneBookService phoneBookService) {
        this.phoneBookService = phoneBookService;
    }

    @PostMapping("/add/")
    public ResponseEntity<ContactSavedResponse> addContact(@RequestBody NewContactRequest newContactRequest) {
        log.info("PBC - addContact service caught with new contact request.");

        Contact contactAdded = phoneBookService.addContact(newContactRequest);

        return new ResponseEntity<>(
                new ContactSavedResponse(
                        contactAdded.getId(),
                        contactAdded.getFirstName(),
                        contactAdded.getLastName(),
                        contactAdded.getPhoneNumber(),
                        contactAdded.getGender()
                ), HttpStatus.OK);

    }

    @PutMapping("/edit/{phoneNumber}/")
    public ResponseEntity<ContactEditedResponse> editContact(@RequestBody EditContactRequest editContactRequest,
                                                             @PathVariable("phoneNumber") String phoneNumber) {
        log.info("PBC - editContact service caught with edit contact request.");

        Contact contactEdited = phoneBookService.editContact(editContactRequest, phoneNumber);

        return new ResponseEntity<>(
                new ContactEditedResponse(
                        contactEdited.getId(),
                        contactEdited.getFirstName(),
                        contactEdited.getLastName(),
                        contactEdited.getPhoneNumber(),
                        contactEdited.getGender()
                ), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{phoneNumber}/")
    public ResponseEntity<Void> deleteContact(@PathVariable("phoneNumber") String phoneNumber) {
        log.info("PBC - deleteContact service caught with delete contact request.");

        phoneBookService.deleteContact(phoneNumber);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
