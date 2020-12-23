package com.savstanis.coursework.model.fileIO;


import com.google.gson.Gson;
import com.savstanis.coursework.model.AirTour;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class FileReader {
    public static AirTour[] readAirToursFromFile(String filePath) throws IOException {
        Gson gson = new Gson();
        AirTour[] airTours = null;
        FileInputStream fileInputStream = new FileInputStream(filePath);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream, 200);
        StringBuilder stringBuilder = new StringBuilder();

        int i;
        while((i = bufferedInputStream.read())!= -1){
            stringBuilder.append((char) i);
        }

        airTours =  gson.fromJson(stringBuilder.toString(), AirTour[].class);
        fileInputStream.close();

        return airTours;
    }
}
