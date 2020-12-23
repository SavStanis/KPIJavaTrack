package com.savstanis.lab1.view;

import com.savstanis.lab1.view.exception.InvalidDateException;
import com.savstanis.lab1.view.exception.NameFormatException;

public class Validator {

    public static boolean validOperatorName(String name) {
        if(name.matches(".*\\d.*")) {
            return false;
        }
        return true;
    }

    public static boolean validVisitingPointName(String name) {
        if(name.matches(".*\\d.*")) {
            return false;
        }
        return true;
    }

    public static boolean validDate(String date) {
        if(!date.matches("^\\d{2}-\\d{2}-\\d{4}")) {
            return false;
        }
        return true;
    }

    public static boolean validTourName(String name) {
        if(name.matches(".*\\d.*")) {
            return false;
        }
        return true;
    }
}
