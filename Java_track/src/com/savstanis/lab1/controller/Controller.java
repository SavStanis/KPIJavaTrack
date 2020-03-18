package com.savstanis.lab1.controller;


import com.savstanis.lab1.model.DBService;
import com.savstanis.lab1.view.View;

import java.text.ParseException;

public class Controller {

    public void run() throws ParseException {

        View view = new View();
        DBService dbservice = new DBService();

        boolean quit = false;

        while(!quit) {
            switch (view.getMainMenu()) {
                case "1":
                    view.viewData(
                            dbservice.getAirTourByOperator(view.getOperatorName()),
                            "Tours by name of operator:"
                    );
                    break;
                case "2":
                    view.viewData(
                            dbservice.getAirTourByVisitingPoint(view.getVisitingPoint()),
                            "Tours by visiting points:"
                    );
                    break;
                case "3":
                    view.viewData(
                            dbservice.getAirTourByTitleAndDate(view.getTourName(), view.getDate()),
                            "Tours by visiting points:"
                    );
                    break;
                case "4":
                    quit = true;
                    break;
                default:
                    view.outputErrorMessage();
                    break;
            }
        }
    }
}
