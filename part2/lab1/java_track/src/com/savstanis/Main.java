package com.savstanis;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter directory -> ");
        String dir = sc.next();
        System.out.print("Enter m -> ");
        int m = sc.nextInt();
        System.out.print("Enter n -> ");
        int n = sc.nextInt();

        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.submit(new DirHandler(new File(dir), executorService, m, n));

    }
}
