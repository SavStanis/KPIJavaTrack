package com.savstanis.lab1.view;

import com.savstanis.lab1.model.AirTour;

import java.util.Date;

public class View {

    InputScanner scan;

    public View() {
        scan = new InputScanner();
    }

    public String getMainMenu() {
        System.out.println(
                "--------------\n" +
                "Menu:\n"+
                "What do you want to do?\n\t" +
                "1. Get list of operators by name of operator\n\t" +
                "2. Get list of operators by visiting points\n\t" +
                "3. Get list of operators by name of tour, date, empty places\n\t" +
                "4. Get all available tours\n\t" +
                "5. Quit");
        return scan.mainMenuInput();
    }

    public static void outputErrorMessage() {
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

    public void viewData(AirTour[] tourList, String message) {
        System.out.println("----------------------------------");
        System.out.println(message);
        if(tourList.length == 0) {
            System.out.println("Unfortunately there is no appropriate tours for you!\n");
            return;
        }
        for (AirTour airTour: tourList) {
            System.out.println(airTour.toString());
        }
    }

    public static void outputExceptionMessage(Exception e) {
        System.err.println(e.toString());
        System.err.println("Try again!");
    }

}
