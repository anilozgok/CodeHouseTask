package org.anilcan.exception;

import org.anilcan.exception.base.BaseException;
import org.anilcan.utility.enums.ExceptionMessage;

public class ContactNotFoundException extends BaseException {

    public ContactNotFoundException() {
        super(ExceptionMessage.CONTACT_NOT_FOUND);
    }
}
