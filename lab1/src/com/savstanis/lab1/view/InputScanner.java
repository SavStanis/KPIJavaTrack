package com.savstanis.lab1.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class InputScanner {

    private final Scanner scan;

    public InputScanner() {
        scan = new Scanner(System.in);
    }

    public String mainMenuInput() {
        return scan.nextLine();
    }

    public String inputOperatorName() {
        String choice = "";

        while (true) {
            choice = scan.nextLine();
            if(Validator.validOperatorName(choice)) {
                return choice;
            }
        }
    }

    public String inputVisitingPoint() {
        String choice = "";

        while (true) {
            choice = scan.nextLine();
            if(Validator.validVisitingPointName(choice)) {
                return choice;
            }
        }
    }

    public String inputTourName() {
        String choice = "";

        while (true) {
            choice = scan.nextLine();
            if(Validator.validTourName(choice)) {
                return choice;
            }
        }
    }

    public Date inputDate() {
        String date = "";

        while (true) {
            date = scan.nextLine();
            try {
                if (Validator.validDate(date)) {
                    return new SimpleDateFormat("dd-MM-yyyy").parse(date);
                }
            } catch (ParseException e) {
                View.outputExceptionMessage(e);
            }
        }
    }

}
