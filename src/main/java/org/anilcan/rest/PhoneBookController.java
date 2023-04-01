package org.anilcan.rest;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.anilcan.model.request.EditContactRequest;
import org.anilcan.model.request.NewContactRequest;
import org.anilcan.model.response.ContactEditedResponse;
import org.anilcan.model.response.ContactListResponse;
import org.anilcan.model.response.ContactResponse;
import org.anilcan.model.response.ContactSavedResponse;
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


    // TODO: bknz: https://stackoverflow.com/questions/53966427/should-service-layer-accept-a-dto-or-a-custom-request-object-from-the-controller
    // TODO: implement getDto and getResponse methods (mappers)

    private final PhoneBookService phoneBookService;

    @PostMapping("/add/")
    public ResponseEntity<ContactSavedResponse> addContact(@RequestBody NewContactRequest newContactRequest) {

        log.info("PBC - addContact service caught with new contact request.");

        var contactAdded = phoneBookService.addContact(newContactRequest);

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


    @GetMapping("/{phoneNumber}")
    public ResponseEntity<ContactResponse> getContactByPhoneNumber(@PathVariable("phoneNumber") @NotBlank String phoneNumber) {
        // TODO: phoneNumber from header

        log.info("getContactByPhoneNumber service caught");

        var contact = phoneBookService.getContactByPhoneNumber(phoneNumber);

        return new ResponseEntity<>(new ContactResponse(contact), HttpStatus.OK);
    }

    @GetMapping("/all/")
    public ResponseEntity<ContactListResponse> getAllContacts() {

        log.info("PBC - service caught get all contact request");

        return new ResponseEntity<>(new ContactListResponse(phoneBookService.getAllContacts()), HttpStatus.OK);
    }

}
