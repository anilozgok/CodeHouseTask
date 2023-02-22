package org.anilcan.model.request.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.anilcan.utility.enums.Gender;

@Data
@AllArgsConstructor
public abstract class ContactRequest {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Gender gender;

}
