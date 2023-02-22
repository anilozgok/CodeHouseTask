package org.anilcan.model.response;

import org.anilcan.model.response.base.ContactResponse;
import org.anilcan.utility.enums.Gender;

public class ContactEditedResponse extends ContactResponse {
    public ContactEditedResponse(Long id, String firstName, String lastName, String phoneNumber, Gender gender) {
        super(id, firstName, lastName, phoneNumber, gender);
    }
}
