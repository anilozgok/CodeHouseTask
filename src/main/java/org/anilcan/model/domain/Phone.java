package org.anilcan.model.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Phone(String number) {

    public String phoneNumber() {
        //TODO: format phone number
        return this.number;
    }

    public boolean isValid() {
        Pattern pattern = Pattern.compile("^(05)([0-9]{2})\\s?([0-9]{3})\\s?([0-9]{2})\\s?([0-9]{2})$");
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

    public static Phone valueOf(String phoneNumber) {
        return new Phone(phoneNumber);
    }
}
