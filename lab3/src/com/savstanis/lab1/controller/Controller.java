package com.savstanis.lab1.controller;


import com.savstanis.lab1.model.DBService;
import com.savstanis.lab1.view.View;

import java.io.IOException;

public class Controller {

    private View view;
    private DBService dbService;

    public Controller() {
        this.view = new View();
        try {
            this.dbService = new DBService();
        } catch (IOException e) {
            View.outputExceptionMessage(e.getMessage());
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
                        View.outputExceptionMessage(e.toString());
                    }
                    break;
                case "6":
                    quit = true;
                    break;
                default:
                    View.outputErrorMessage();
                    break;
            }
        }
    }
}
