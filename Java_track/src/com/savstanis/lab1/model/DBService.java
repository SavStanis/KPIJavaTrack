package com.savstanis.lab1.model;

import java.util.ArrayList;
import java.util.Date;

public class Service {

    public static ArrayList<AirTour> getAirTourByOperator(String nameOfOperator) {

        AirTour[] airTours = DataSource.getAirTours();

        ArrayList<AirTour> appropriateTours = new ArrayList<AirTour>();

        for (AirTour airTour: airTours) {
            if(airTour.getTourOperator().equals(nameOfOperator))  {
                appropriateTours.add(airTour);
            }
        }

        return appropriateTours;
    }

    public static ArrayList<AirTour> getAirTourByVisitingPoint(String nameOfPoint) {

        AirTour[] airTours = DataSource.getAirTours();

        ArrayList<AirTour> appropriateTours = new ArrayList<AirTour>();

        for (AirTour airTour : airTours) {
            for (String point: airTour.visitingPoints) {
                if(point.equals(nameOfPoint))  {
                    appropriateTours.add(airTour);
                    break;
                }
            }
        }

        return appropriateTours;
    }

    public static ArrayList<AirTour> getAirTourByTitleAndDate(String titleOfTour, Date endingDate) {

        AirTour[] airTours = DataSource.getAirTours();

        ArrayList<AirTour> appropriateTours = new ArrayList<AirTour>();

        for (AirTour airTour : airTours) {
            if(airTour.getTourTitle().equals(titleOfTour) && airTour.getDateOfDeparture().before(endingDate))  {
                appropriateTours.add(airTour);
            }
        }
        return appropriateTours;
    }
}
