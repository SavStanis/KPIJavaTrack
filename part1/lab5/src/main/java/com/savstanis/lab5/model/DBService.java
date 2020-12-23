package com.savstanis.lab5.model;

import com.savstanis.lab5.model.fileIO.FileReader;
import com.savstanis.lab5.model.fileIO.FileWriter;

import java.io.IOException;
import java.util.Date;

public class DBService {

    private AirTour[] airTours;

    private AirTour[] searchResult;

    public DBService() throws IOException {
        searchResult = null;
        airTours = FileReader.readAirToursFromFile("airTours.json");
    }

    public AirTour[] getAirTours() {
        searchResult = airTours;
        return airTours;
    }

    public static void writeDataToFile(AirTour[] tours, String filePath) throws IOException {
        FileWriter.writeAirToursInFile(tours, filePath);
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

        searchResult = appropriateTours;
        return appropriateTours;
    }

    public AirTour[] getAirTourByVisitingPoint(String nameOfPoint) {

        int counter = 0;
        for (AirTour airTour : airTours) {
            for (String point: airTour.getVisitingPoints()) {
                if(point.equals(nameOfPoint))  {
                    counter++;
                    break;
                }
            }
        }

        AirTour[] appropriateTours = new AirTour[counter];

        counter = 0;
        for (AirTour airTour : airTours) {
            for (String point: airTour.getVisitingPoints()) {
                if(point.equals(nameOfPoint))  {
                    appropriateTours[counter] = airTour;
                    counter++;
                    break;
                }
            }
        }
        searchResult = appropriateTours;

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

        searchResult = appropriateTours;
        return appropriateTours;
    }

    public AirTour[] getSearchResult() {
        return searchResult;
    }
}
