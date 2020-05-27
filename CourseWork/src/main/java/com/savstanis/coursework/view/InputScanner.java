package com.savstanis.coursework.view;

import com.savstanis.coursework.view.exception.InvalidDateException;
import com.savstanis.coursework.view.exception.NameFormatException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class InputScanner {

    private final Scanner scan;
    private static final Logger logger = LogManager.getLogger(View.class);
    public InputScanner() {
        scan = new Scanner(System.in);
    }

    public String menuInput() {
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
                logger.error(e.toString(), e);
                View.outputExceptionMessage(e.toString());
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
                logger.error(e.toString(), e);
                View.outputExceptionMessage(e.toString());
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
                logger.error(e.toString(), e);
                View.outputExceptionMessage(e.toString());
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
                logger.error(e.toString(), e);
                View.outputExceptionMessage(e.toString());
            }
        }
    }

    public String inputFilePath() {
        String date = "";
        date = scan.nextLine();
        return date;
    }

}
