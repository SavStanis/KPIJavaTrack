package com.savstanis.exhibitionservice.validator;

import com.savstanis.exhibitionservice.exception.InvalidDateException;
import com.savstanis.exhibitionservice.exception.InvalidEmailException;
import com.savstanis.exhibitionservice.exception.InvalidPasswordException;
import com.savstanis.exhibitionservice.exception.InvalidPriceException;

import java.util.Date;

public class Validator {
    public static String emailRegex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";

    public static void validateEmail(String email) {
        if (!email.matches(emailRegex)) {
            throw new InvalidEmailException();
        }
    }

    public static void validatePassword(String password) {
        if (password.length() < 8) {
            throw new InvalidPasswordException();
        }
    }

    public static void validateOpeningClosingDates(Date openingDate, Date closingDate) {
        Date now = new Date();

        if (now.after(closingDate) || openingDate.after(closingDate)) {
            throw new InvalidDateException();
        }
    }
    public static void validatePrice(double price) {
        if (price < 0) {
            throw new InvalidPriceException();
        }
    }
}
