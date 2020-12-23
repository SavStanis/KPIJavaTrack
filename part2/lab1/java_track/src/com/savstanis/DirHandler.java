package com.savstanis;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.ExecutorService;

public class DirHandler implements Runnable {

    private File dir;

    private ExecutorService executorService;

    private int m;
    private int n;

    public DirHandler(File dir, ExecutorService executorService, int m, int n) {
        this.dir = dir;
        this.executorService = executorService;
        this.m = m;
        this.n = n;
    }

    boolean isTextFile(File f) throws IOException {
        String type = Files.probeContentType(f.toPath());m  
        if (type == null) {
            // System.out.println("File " + f.getName() + " is not text file: ");
            return false;
        } else if (type.startsWith("text")) {
            // System.out.println("File " + f.getName() + " is text file " + type);
            return true;
        } else {
            // System.out.println("File " + f.getName() + " is not text file " + type);
            return false;
        }
    }

    @Override
    public void run() {
        try {
            File[] files = dir.listFiles();

            for (File f : files) {
                if (f.isDirectory()) {
                    System.out.println("Dir ->" + f.getPath());
                    executorService.submit(new DirHandler(f, executorService, m, n));
                } else if (isTextFile(f)) {
                    executorService.submit(new FileWriterThread(f, m, n));
                    System.out.println("File ->" + f.getPath());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
