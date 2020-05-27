package com.savstanis.coursework.model;

import java.util.Arrays;
import java.util.Date;

public class AirTour {

    private final String tourTitle;
    private final String tourOperator;
    private final String[] visitingPoints;
    private final float value;

    private final int numberOfFreePlaces;
    private final int numberOfOccupiedPlaces;

    private final Date dateOfDeparture;

    public AirTour(String tourTitle, String tourOperator, String[] visitingPoints, float value, int numberOfFreePlaces, int numberOfOccupiedPlaces, Date dateOfDeparture) {
        this.tourTitle = tourTitle;
        this.tourOperator = tourOperator;
        this.visitingPoints = visitingPoints;
        this.value = value;
        this.numberOfFreePlaces = numberOfFreePlaces;
        this.numberOfOccupiedPlaces = numberOfOccupiedPlaces;
        this.dateOfDeparture = dateOfDeparture;
    }

    public String getTourTitle() {
        return tourTitle;
    }

    public String getTourOperator() {
        return tourOperator;
    }

    public String[] getVisitingPoints() {
        return visitingPoints;
    }

    public float getValue() {
        return value;
    }

    public int getNumberOfFreePlaces() {
        return numberOfFreePlaces;
    }

    public int getNumberOfOccupiedPlaces() {
        return numberOfOccupiedPlaces;
    }

    public Date getDateOfDeparture() {
        return dateOfDeparture;
    }

    @Override
    public String toString() {
        return  String.format("%15s | %20s | %40s | %10.2f | %25d | %25d | %20s ",
                tourTitle,
                tourOperator,
                Arrays.toString(visitingPoints),
                value,
                numberOfFreePlaces,
                numberOfOccupiedPlaces,
                dateOfDeparture.toString()
        );
    }
}
