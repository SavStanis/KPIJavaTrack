package com.savstanis.coursework.controller;

import com.savstanis.coursework.model.DBService;
import com.savstanis.coursework.view.View;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Controller {

    private final Logger logger;

    private final View view;
    private DBService dbService;
    private ResourceBundle resourceBundle;

    public Controller() {
        this.logger = LogManager.getLogger(Controller.class);
        this.view = new View();
        resourceBundle = ResourceBundle.getBundle("Messages");

        try {
            this.dbService = new DBService();
        } catch (IOException e) {
            View.outputExceptionMessage(e.getMessage());
            logger.fatal("File is not exists", e);
            System.exit(1);
        }
    }

    public void updateResourceBundle() {
        resourceBundle = ResourceBundle.getBundle("Messages");
    }

    public void run() {

        boolean quit = false;

        while(!quit) {
            switch (view.getMainMenu()) {
                case "1":
                    view.viewData(
                            dbService.getAirTourByOperator(view.getOperatorName()),
                            resourceBundle.getString("tours_by_name_of_operator")
                    );
                    break;
                case "2":
                    view.viewData(
                            dbService.getAirTourByVisitingPoint(view.getVisitingPoint()),
                            resourceBundle.getString("tours_by_visiting_points")
                     );
                    break;
                case "3":
                    view.viewData(
                            dbService.getAirTourByTitleAndDate(view.getTourName(), view.getDate()),
                            resourceBundle.getString("tours_by_title_and_date")
                    );
                    break;
                case "4":
                    view.viewData(
                            dbService.getAirTours(),
                            resourceBundle.getString("all_tours")
                    );
                    break;
                case "5":
                    try {
                        dbService.writeDataToFile(
                                dbService.getSearchResult(),
                                view.getFilePath()
                        );
                    } catch (IOException e) {
                        logger.error("Invalid  filename input!");
                        View.outputExceptionMessage(e.toString());
                    }
                    break;
                case "6":
                    changeLocale();
                    break;
                case "7":
                    quit = true;
                    break;
                default:
                    logger.warn("Invalid menu input!");
                    view.outputErrorMessage();
                    break;
            }
        }
    }

    private void changeLocale() {
        switch (view.changeLanguageMenu()) {
            case "2":
                Locale.setDefault(new Locale("ru", "UA"));
                break;
            default:
                Locale.setDefault(new Locale("en", "US"));
                break;
        }

        view.updateResourceBundle();
        updateResourceBundle();
    }
}
