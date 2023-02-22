package org.anilcan.model.request;

import org.anilcan.model.request.base.ContactRequest;
import org.anilcan.utility.enums.Gender;

public class NewContactRequest extends ContactRequest {
    public NewContactRequest(String firstName, String lastName, String phoneNumber, Gender gender) {
        super(firstName, lastName, phoneNumber, gender);
    }
}
