package org.anilcan.model.response.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.anilcan.utility.enums.Gender;

@Data
@AllArgsConstructor
public abstract class ContactResponse {

    private Long id;
    private String  firstName;
    private String lastName;
    private String phoneNumber;
    private Gender gender;
}
