package com.savstanis;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileWriterThread implements Runnable {

    private final File file;
    private int m;
    private int n;

    private final String PATH = "./result_files/";

    public FileWriterThread(File file, int m, int n) {
        this.file = file;
        this.m = m;
        this.n = n;
    }

    private void writeToFile(List<String> fileLines) throws IOException {

        FileOutputStream outputStream = new FileOutputStream(PATH + file.getName());

        if (n > fileLines.size()) {
            n = fileLines.size();
        }

        for (int i = fileLines.size() - n; i < fileLines.size(); i++) {
            List<String> splitLine = Arrays.asList(fileLines.get(i).split(" "));

            StringBuffer stringBuffer = new StringBuffer();

            if (m > splitLine.size()) {
                m = splitLine.size();
            }

            for (int j = splitLine.size() - m; j < splitLine.size(); j++) {
                stringBuffer.append(splitLine.get(j));
            }

            String resultString = stringBuffer.toString();

            System.out.println("From file: " + file.getName() + " line: " + resultString);

            outputStream.write(resultString.getBytes());
            outputStream.write("\n".getBytes());
        }

        outputStream.close();
    }

    @Override
    public void run() {
        try {
            Scanner sc = new Scanner(new FileInputStream(file));
            List<String> fileLines = new ArrayList<>();

            while (sc.hasNextLine()) {
                fileLines.add(sc.nextLine());
            }

            writeToFile(fileLines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
