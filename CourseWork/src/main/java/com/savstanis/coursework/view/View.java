package com.savstanis.coursework.view;

import com.savstanis.coursework.model.AirTour;
import com.savstanis.coursework.model.DBService;

import java.io.IOException;
import java.util.Date;

public class View {

    private final InputScanner scan;
    private static LocaleManager localeManager = LocaleManager.getInstance();

    public View() {
        scan = new InputScanner();
    }

    public void updateResourceBundle() {
        localeManager.updateLocale();
    }

    public String getMainMenu() {
        System.out.println(localeManager.getString("main_menu"));
        return scan.menuInput();
    }

    public void outputErrorMessage() {
        System.err.println(localeManager.getString("error_message"));
    }

    public String getOperatorName() {
        System.out.println(localeManager.getString("input_operator_message"));
        return scan.inputOperatorName();
    }

    public String getVisitingPoint() {
        System.out.println(localeManager.getString("input_visiting_points"));
        return  scan.inputVisitingPoint();
    }

    public String getTourName() {
        System.out.println(localeManager.getString("input_tour_name"));
        return  scan.inputTourName();
    }

    public Date getDate() {
        System.out.println(localeManager.getString("input_date"));
        return  scan.inputDate();
    }

    public String getFilePath() {
        System.out.println(localeManager.getString("input_path"));
        return  scan.inputFilePath();
    }

    public String changeLanguageMenu() {
        System.out.println(localeManager.getString("change_locale"));
        return scan.menuInput();
    }

    public void viewData(AirTour[] tourList, String message) {

        System.out.println(message);
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(
            String.format("%15s | %20s | %40s | %10s | %25s | %25s | %20s ",
                    localeManager.getString("tour_title"),
                    localeManager.getString("tour_operator"),
                    localeManager.getString("visiting_points"),
                    localeManager.getString("value"),
                    localeManager.getString("number_of_free_places"),
                    localeManager.getString("number_of_occupied_places"),
                    localeManager.getString("date_of_departure")
            )
        );
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        if(tourList.length == 0) {
            System.out.println(localeManager.getString("no_appropriate_tours"));
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
        System.err.println(localeManager.getString(message));
    }

}
