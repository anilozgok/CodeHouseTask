package org.anilcan.exception;

import org.anilcan.exception.base.BaseException;
import org.anilcan.utility.ExceptionMessage;

public class InvalidPhoneNumberException extends BaseException {

    public InvalidPhoneNumberException() {
        super(ExceptionMessage.INVALID_PHONE_NUMBER);
    }
}
