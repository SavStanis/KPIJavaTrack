package com.savstanis.coursework.view;

import com.savstanis.coursework.model.AirTour;
import com.savstanis.coursework.model.DBService;

import java.io.IOException;
import java.util.Date;
import java.util.ResourceBundle;

public class View {

    private final InputScanner scan;
    private ResourceBundle resourceBundle;

    public View() {
        scan = new InputScanner();
        resourceBundle = ResourceBundle.getBundle("Messages");
    }

    public void updateResourceBundle() {
        this.resourceBundle = ResourceBundle.getBundle("Messages");;
    }

    public String getMainMenu() {
        System.out.println(resourceBundle.getString("main_menu"));
        return scan.menuInput();
    }

    public void outputErrorMessage() {
        System.err.println(resourceBundle.getString("error_message"));
    }

    public String getOperatorName() {
        System.out.println(resourceBundle.getString("input_operator_message"));
        return scan.inputOperatorName();
    }

    public String getVisitingPoint() {
        System.out.println(resourceBundle.getString("input_visiting_points"));
        return  scan.inputVisitingPoint();
    }

    public String getTourName() {
        System.out.println(resourceBundle.getString("input_tour_name"));
        return  scan.inputTourName();
    }

    public Date getDate() {
        System.out.println(resourceBundle.getString("input_date"));
        return  scan.inputDate();
    }

    public String getFilePath() {
        System.out.println(resourceBundle.getString("input_path"));
        return  scan.inputFilePath();
    }

    public String changeLanguageMenu() {
        System.out.println(resourceBundle.getString("change_locale"));
        return scan.menuInput();
    }

    public void viewData(AirTour[] tourList, String message) {

        System.out.println(message);
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(
            String.format("%15s | %20s | %40s | %10s | %25s | %25s | %20s ",
                    resourceBundle.getString("tour_title"),
                    resourceBundle.getString("tour_operator"),
                    resourceBundle.getString("visiting_points"),
                    resourceBundle.getString("value"),
                    resourceBundle.getString("number_of_free_places"),
                    resourceBundle.getString("number_of_occupied_places"),
                    resourceBundle.getString("date_of_departure")
            )
        );
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        if(tourList.length == 0) {
            System.out.println(resourceBundle.getString("no_appropriate_tours"));
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
