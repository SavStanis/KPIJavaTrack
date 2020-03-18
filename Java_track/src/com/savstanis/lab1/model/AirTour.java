package com.savstanis.lab1.model;

import java.util.Arrays;
import java.util.Date;

public class AirTour {

    public final String tourTitle;
    public final String tourOperator;
    public final String[] visitingPoints;
    public final float value;

    public final int numberOfFreePlaces;
    public final int numberOfOccupiedPlaces;

    public final Date dateOfDeparture;

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
        return  String.format(
                "\nTitle:\t %-10s \n" +
                        "Tour operator:\t %s \n" +
                        "Visiting points:\t %s \n" +
                        "Value:\t %.2f \n" +
                        "Number of free places:\t %d \n" +
                        "Number of occupied places:\t %d \n" +
                        "Date of departure:\t %s \n",
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
