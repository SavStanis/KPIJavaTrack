package com.savstanis.lab5.controller;

import com.savstanis.lab5.model.DBService;
import com.savstanis.lab5.view.View;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class Controller {

    private final Logger logger;

    private final View view;
    private DBService dbService;

    public Controller() {
        this.logger = LogManager.getLogger(Controller.class);
        this.view = new View();
        try {
            this.dbService = new DBService();
        } catch (IOException e) {
            View.outputExceptionMessage(e.getMessage());
            logger.fatal("File is not exists", e);
            System.exit(1);
        }
    }

    public void run() {

        boolean quit = false;

        while(!quit) {
            switch (view.getMainMenu()) {
                case "1":
                    view.viewData(
                            dbService.getAirTourByOperator(view.getOperatorName()),
                            "Tours by name of operator:"
                    );
                    break;
                case "2":
                    view.viewData(
                            dbService.getAirTourByVisitingPoint(view.getVisitingPoint()),
                            "Tours by visiting points:"
                    );
                    break;
                case "3":
                    view.viewData(
                            dbService.getAirTourByTitleAndDate(view.getTourName(), view.getDate()),
                            "Tours by visiting points:"
                    );
                    break;
                case "4":
                    view.viewData(
                            dbService.getAirTours(),
                            "All available tours"
                    );
                    break;
                case "5":
                    try {
                        dbService.writeDataToFile(
                                dbService.getSearchResult(),
                                view.getFilePath()
                        );
                    } catch (IOException e) {
                        logger.warn("Invalid  filename input!");
                        View.outputExceptionMessage(e.toString());
                    }
                    break;
                case "6":
                    quit = true;
                    break;
                default:
                    logger.warn("Invalid menu input!");
                    view.outputErrorMessage();
                    break;
            }
        }
    }
}
