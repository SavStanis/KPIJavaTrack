package com.savstanis.lab4;

import com.savstanis.lab4.model.Point;

public class Main {

    public static void main(String[] args) {
        SetOfLines setOfLines = new SetOfLines();
        Point result = setOfLines.getCrossPointOfTwoSLinesWithTheSmallestX();
        System.out.println(setOfLines.getSetOfLines());
        if (result == null) {
            System.out.println("Cross point is not exist!");
        } else {
            System.out.println("Cross point: "+ result);
        }
    }

}
