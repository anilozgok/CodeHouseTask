package org.anilcan.exception;

public class InvalidPhoneNumberException extends RuntimeException{

    public InvalidPhoneNumberException(String message){
        super(message);
    }
}
