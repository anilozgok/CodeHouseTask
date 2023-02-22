package org.anilcan.utility.enums;

public enum ExceptionMessages {
    INVALID_PHONE_NUMBER("Invalid Phone Number."),
    CONTACT_NOT_FOUND("Contact Not Found.");

    public final String message;

    ExceptionMessages(String message) {
        this.message = message;
    }

}
