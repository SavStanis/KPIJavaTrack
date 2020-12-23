package com.savstanis.lab1.model;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class DataSource {

    public static AirTour[] getAirTours() {
        return new AirTour[] {
                new AirTour(
                        "Turkey",
                        "Southern Airlines",
                        new String[]{"Istanbul", "Ankara", "Antalya"},
                        1000,
                        10,
                        60,
                        new GregorianCalendar(2020, Calendar.MAY, 12).getTime()
                ),
                new AirTour(
                        "Egypt",
                        "Southern Airlines",
                        new String[]{"Cairo", "Alexandria", "Sharm El-Sheikh"},
                        1500,
                        30,
                        60,
                        new GregorianCalendar(2020, Calendar.MAY, 16).getTime()
                ),
                new AirTour(
                        "UAE",
                        "Southern Airlines",
                        new String[]{"Dubai", "Abu Dhabi"},
                        1200,
                        18,
                        60,
                        new GregorianCalendar(2020, Calendar.APRIL, 12).getTime()
                ),
                new AirTour(
                        "Sweden",
                        "Northern Airlines",
                        new String[]{"Stockholm", "Uppsala", "Gothenburg"},
                        1300,
                        14,
                        60,
                        new GregorianCalendar(2020, Calendar.JULY, 12).getTime()
                ),
                new AirTour(
                        "Iceland",
                        "Northern Airlines",
                        new String[]{"Reykjavik", "Vik"},
                        2000,
                        14,
                        60,
                        new GregorianCalendar(2020, Calendar.JULY, 16).getTime()
                ),
                new AirTour(
                        "Italy",
                        "Southern Airlines",
                        new String[]{"Rome", "Milan", "Venice"},
                        1000,
                        10,
                        60,
                        new GregorianCalendar(2020, Calendar.JULY, 22).getTime()
                ),
                new AirTour(
                        "India",
                        "Eastern Airlines",
                        new String[]{"Mumbai", "Surat"},
                        1500,
                        30,
                        60,
                        new GregorianCalendar(2020, Calendar.SEPTEMBER, 16).getTime()
                ),
                new AirTour(
                        "China",
                        "Eastern Airlines",
                        new String[]{"Beijing", "Shanghai"},
                        1200,
                        18,
                        60,
                        new GregorianCalendar(2020, Calendar.DECEMBER, 5).getTime()
                ),
                new AirTour(
                        "Australia",
                        "Eastern Airlines",
                        new String[]{"Canberra", "Sydney"},
                        1300,
                        14,
                        60,
                        new GregorianCalendar(2020, Calendar.AUGUST, 18).getTime()
                ),
                new AirTour(
                        "USA",
                        "Western Airlines",
                        new String[]{"New York", "Boston"},
                        2000,
                        14,
                        60,
                        new GregorianCalendar(2020, Calendar.JUNE, 26).getTime()
                )
        };
    };

}
