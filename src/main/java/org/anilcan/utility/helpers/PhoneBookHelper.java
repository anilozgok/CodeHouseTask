package org.anilcan.utility.helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBookHelper {

    public boolean isPhoneNumberValid(String phoneNumber){
        Pattern pattern = Pattern.compile("^(05)([0-9]{2})\\s?([0-9]{3})\\s?([0-9]{2})\\s?([0-9]{2})$");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }


}
