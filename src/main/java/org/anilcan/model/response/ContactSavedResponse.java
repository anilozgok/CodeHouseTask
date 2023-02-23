package org.anilcan.model.response;

import org.anilcan.model.response.base.ContactResponse;
import org.anilcan.utility.Gender;

public class ContactSavedResponse extends ContactResponse {
    public ContactSavedResponse(Long id, String firstName, String lastName, String phoneNumber, Gender gender) {
        super(id, firstName, lastName, phoneNumber, gender);
    }
}
