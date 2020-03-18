package com.savstanis.lab1.model;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class DataSource {

    public static AirTour[] getAirTours() {
        return new AirTour[] {
                new AirTour(
                        "Turkey",
                        "South Airlines",
                        new String[]{"Istanbul", "Ankara", "Antalya"},
                        1000,
                        10,
                        60,
                        new GregorianCalendar(2020, Calendar.MAY, 12).getTime()
                ),
                new AirTour(
                        "Egypt",
                        "South Airlines",
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
                )
        };
    };

}
