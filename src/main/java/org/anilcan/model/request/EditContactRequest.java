package org.anilcan.model.request;

import org.anilcan.model.request.base.ContactRequest;
import org.anilcan.utility.enums.Gender;

public class EditContactRequest extends ContactRequest {
    public EditContactRequest(String firstName, String lastName, String phoneNumber, Gender gender) {
        super(firstName, lastName, phoneNumber, gender);
    }
}
