package com.savstanis.lab1.view;

import com.savstanis.lab1.view.exception.InvalidDateException;
import com.savstanis.lab1.view.exception.NameFormatException;

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
            try {
                Validator.validOperatorName(choice);
                return choice;
            } catch (NameFormatException e) {
                View.outputExceptionMessage(e);
            }
        }
    }

    public String inputVisitingPoint() {
        String choice = "";

        while (true) {
            choice = scan.nextLine();
            try {
                Validator.validVisitingPointName(choice);
                return choice;
            } catch (NameFormatException e) {
                View.outputExceptionMessage(e);
            }
        }
    }

    public String inputTourName() {
        String name = "";

        while (true) {
            name = scan.nextLine();

            try {
                Validator.validTourName(name);
                return name;

            } catch (NameFormatException e) {
                View.outputExceptionMessage(e);
            }
        }
    }

    public Date inputDate() {
        String date = "";

        while (true) {
            date = scan.nextLine();
            try {
                Validator.validDate(date);
                return new SimpleDateFormat("dd-MM-yyyy").parse(date);
            } catch (InvalidDateException | ParseException e) {
                View.outputExceptionMessage(e);
            }
        }
    }

}
