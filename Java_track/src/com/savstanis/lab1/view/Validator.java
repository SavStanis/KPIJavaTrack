package com.savstanis.lab1.view;

public class Validator {

    public Validator() {

    }

    public boolean validOperatorName(String name) {
        if(!name.matches(".*\\d.*")) {
            return true;
        }
        return false;
    }

    public boolean validVisitingPointName(String name) {
        if(!name.matches(".*\\d.*")) {
            return true;
        }
        return false;
    }

    public boolean validDate(String date) {
        if(!date.matches("^\\d{2}-\\d{2}-\\d{4}")) {
            return false;
        }
        return true;
    }

    public boolean validTourName(String name) {
        if(!name.matches(".*\\d.*")) {
            return true;
        }
        return false;
    }
}
