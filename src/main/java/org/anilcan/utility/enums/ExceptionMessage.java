package org.anilcan.utility.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {
    INVALID_PHONE_NUMBER("Invalid Phone Number.", "invalid.phone", HttpStatus.BAD_REQUEST),
    CONTACT_NOT_FOUND("Contact Not Found.", "contact.not.found", HttpStatus.NOT_FOUND),
    UNKNOWN_EXCEPTION("Unknown Error Occurred", "unknown.exception", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;

    private final String errorName;

    private final HttpStatus errorCode;



}
