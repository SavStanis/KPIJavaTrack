package com.savstanis.lab1.model;

import java.util.Date;

public class DBService {

    AirTour[] airTours;

    public DBService() {
        airTours = DataSource.getAirTours();
    }

    public AirTour[] getAirTourByOperator(String nameOfOperator) {

        int counter = 0;
        for (AirTour airTour: airTours) {
            if(airTour.getTourOperator().equals(nameOfOperator)) {
                counter++;
            }
        }

        AirTour[] appropriateTours = new AirTour[counter];

        counter = 0;
        for (AirTour airTour: airTours) {
            if(airTour.getTourOperator().equals(nameOfOperator)) {
                appropriateTours[counter] = airTour;
                counter++;
            }
        }

        return appropriateTours;
    }

    public AirTour[] getAirTourByVisitingPoint(String nameOfPoint) {

        int counter = 0;
        for (AirTour airTour : airTours) {
            for (String point: airTour.visitingPoints) {
                if(point.equals(nameOfPoint))  {
                    counter++;
                    break;
                }
            }
        }

        AirTour[] appropriateTours = new AirTour[counter];

        counter = 0;
        for (AirTour airTour : airTours) {
            for (String point: airTour.visitingPoints) {
                if(point.equals(nameOfPoint))  {
                    appropriateTours[counter] = airTour;
                    counter++;
                    break;
                }
            }
        }

        return appropriateTours;
    }

    public AirTour[] getAirTourByTitleAndDate(String tourName, Date endingDate) {

        int counter = 0;
        for (AirTour airTour : airTours) {
            if(airTour.getTourTitle().equals(tourName) && airTour.getDateOfDeparture().before(endingDate) && airTour.getNumberOfFreePlaces() > 0)  {
                counter++;
            }
        }

        AirTour[] appropriateTours = new AirTour[counter];

        counter = 0;
        for (AirTour airTour : airTours) {
            if(airTour.getTourTitle().equals(tourName) && airTour.getDateOfDeparture().before(endingDate) && airTour.getNumberOfFreePlaces() > 0)  {
                appropriateTours[counter] = airTour;
                counter++;
            }
        }

        return appropriateTours;
    }
}
