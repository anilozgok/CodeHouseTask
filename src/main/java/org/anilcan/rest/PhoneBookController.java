package org.anilcan.rest;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.anilcan.model.domain.ContactRecord;
import org.anilcan.model.dto.request.EditContactRequest;
import org.anilcan.model.dto.request.NewContactRequest;
import org.anilcan.model.dto.response.ContactEditedResponse;
import org.anilcan.model.dto.response.ContactSavedResponse;
import org.anilcan.model.entity.Contact;
import org.anilcan.service.PhoneBookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/phone-book/contact/")
public class PhoneBookController {

    private final PhoneBookService phoneBookService;

    @PostMapping("/add/")
    public ResponseEntity<ContactSavedResponse> addContact(@RequestBody NewContactRequest newContactRequest) {
        log.info("PBC - addContact service caught with new contact request.");

        var contactAdded = phoneBookService.addContact(newContactRequest.info());

        return new ResponseEntity<>(new ContactSavedResponse(contactAdded.getLeft(), contactAdded.getRight()), HttpStatus.OK);

    }

    @PutMapping("/edit/{phoneNumber}/")
    public ResponseEntity<ContactEditedResponse> editContact(@RequestBody EditContactRequest editContactRequest,
                                                             @PathVariable("phoneNumber") @NotBlank String phoneNumber) {

        //TODO: phoneNumber from header

        log.info("PBC - editContact service caught with edit contact request.");

        var contactEdited = phoneBookService.editContact(editContactRequest, phoneNumber);

        return new ResponseEntity<>(new ContactEditedResponse(contactEdited.getLeft(), contactEdited.getRight()), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{phoneNumber}/")
    public ResponseEntity<Void> deleteContact(@PathVariable("phoneNumber") @NotBlank String phoneNumber) {

        //TODO: phoneNumber from header

        log.info("PBC - deleteContact service caught with delete contact request.");

        phoneBookService.deleteContact(phoneNumber);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/by-phone-number/{phoneNumber}")
    public ResponseEntity<Void> getContactByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber) {
        //TODO:logic will be implemented
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
