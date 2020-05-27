package com.savstanis.lab5.view;

import com.savstanis.lab5.model.AirTour;
import com.savstanis.lab5.model.DBService;

import java.io.IOException;
import java.util.Date;

public class View {

    private final InputScanner scan;

    public View() {
        scan = new InputScanner();
    }

    public String getMainMenu() {
        System.out.println(" --------------\nMenu:\nWhat do you want to do?\n\t" +
                "1. Get list of operators by name of operator\n\t" +
                "2. Get list of operators by visiting points\n\t" +
                "3. Get list of operators by name of tour, date, empty places\n\t" +
                "4. Get all available tours\n\t" +
                "5. Save result to file\n\t" +
                "6. Quit");
        return scan.mainMenuInput();
    }

    public void outputErrorMessage() {
        System.err.println("Invalid input! Try again!");
    }

    public String getOperatorName() {
        System.out.println("Input operator's name: ");
        return scan.inputOperatorName();
    }

    public String getVisitingPoint() {
        System.out.println("Input name of visiting point: ");
        return  scan.inputVisitingPoint();
    }

    public String getTourName() {
        System.out.println("Input name of tour: ");
        return  scan.inputTourName();
    }

    public Date getDate() {
        System.out.println("Input finite date of tour(dd-mm-yyy): ");
        return  scan.inputDate();
    }

    public String getFilePath() {
        System.out.println("Input path:");
        return  scan.inputFilePath();
    }

    public void viewData(AirTour[] tourList, String message) {

        System.out.println(message);
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(
            String.format("%15s | %20s | %40s | %10s | %20s | %25s | %20s ",
                    "TourTitle",
                    "TourOperator",
                    "VisitingPoints",
                    "Value",
                    "NumberOfFreePlaces",
                    "NumberOfOccupiedPlaces",
                    "DateOfDeparture"
            )
        );
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        if(tourList.length == 0) {
            System.out.println("Unfortunately there is no appropriate tours for you!\n");
            return;
        }
        for (AirTour airTour: tourList) {
            System.out.println(airTour.toString());
        }
        try {
            DBService.writeDataToFile(tourList, "airTours.json");
        } catch (IOException e) {
            outputExceptionMessage(e.toString());
        }
    }

    public static void outputExceptionMessage(String message) {
        System.err.println(message);
        System.err.println("Try again!");
    }

}
