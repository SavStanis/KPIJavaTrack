package com.savstanis.lab5.view;


import com.savstanis.lab5.view.exception.InvalidDateException;
import com.savstanis.lab5.view.exception.NameFormatException;

public class Validator {

    public static void validOperatorName(String name) throws NameFormatException {
        if(name.matches(".*\\d.*")) {
            throw new NameFormatException();
        }
    }

    public static void validVisitingPointName(String name) throws NameFormatException {
        if(name.matches(".*\\d.*")) {
            throw new NameFormatException();
        }
    }

    public static void validDate(String date) throws InvalidDateException {
        if(!date.matches("^\\d{2}-\\d{2}-\\d{4}")) {
            throw new InvalidDateException();
        }
    }

    public static void validTourName(String name) throws NameFormatException {
        if(name.matches(".*\\d.*")) {
            throw new NameFormatException();
        }
    }
}
