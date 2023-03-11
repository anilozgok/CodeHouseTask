package org.anilcan.model.domain;

import jakarta.validation.constraints.NotBlank;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Phone(@NotBlank String number) {
    public boolean isValid() {
        Pattern pattern = Pattern.compile("^(05)([0-9]{2})\\s?([0-9]{3})\\s?([0-9]{2})\\s?([0-9]{2})$");
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

    public static Phone valueOf(String phoneNumber) {
        return new Phone(phoneNumber.replaceAll("\\s",""));
    }
}
