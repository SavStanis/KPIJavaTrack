package com.savstanis.coursework.model.fileIO;


import com.google.gson.Gson;
import com.savstanis.coursework.model.AirTour;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileWriter {

    public static void writeAirToursInFile(AirTour[] tours, String filePath) throws IOException {
        Gson gson = new Gson();
        FileOutputStream outputStream = new FileOutputStream(filePath);
        String toursString = gson.toJson(tours);
        outputStream.write(toursString.getBytes());
        outputStream.close();
    }
}
