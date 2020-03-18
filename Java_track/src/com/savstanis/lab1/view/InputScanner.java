package com.savstanis.lab1.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class InputScanner {

    private Scanner scan;
    private Validator valid;

    public InputScanner() {
        scan = new Scanner(System.in);
        valid = new Validator();
    }

    public String mainMenuInput() {
        String choice = "";
        return scan.nextLine();
    }

    public String inputOperatorName() {
        String choice = "";

        while (true) {
            choice = scan.nextLine();
            if (valid.validOperatorName(choice)) {
                return choice;
            }
            System.out.println("Try again\n");
        }
    }

    public String inputVisitingPoint() {
        String choice = "";

        while (true) {
            choice = scan.nextLine();
            if (valid.validVisitingPointName(choice)) {
                return choice;
            }
            System.out.println("Try again!\n");
        }
    }

    public String inputTourName() {
        String name = "";

        while (true) {
            name = scan.nextLine();
            if (valid.validTourName(name)) {
                return name;
            }
            System.out.println("Try again!\n");
        }
    }

    public Date inputDate() throws ParseException {
        String date = "";

        while (true) {
            date = scan.nextLine();
            if (valid.validDate(date)) {
                return new SimpleDateFormat("dd-MM-yyyy").parse(date);
            }
            System.out.println("Try again!\n");
        }
    }

}
