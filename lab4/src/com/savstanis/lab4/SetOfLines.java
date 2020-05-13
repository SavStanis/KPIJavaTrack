package com.savstanis.lab4;

import com.savstanis.lab4.model.Line;
import com.savstanis.lab4.model.Point;

import java.util.HashSet;
import java.util.Set;

public class SetOfLines {

    Set<Line> setOfLines;
    final double THRESHOLD = 0.0001;

    public SetOfLines() {
        setOfLines = new HashSet<>();

        setOfLines.add(new Line(new Point(-1000, -2000), new Point(1, 2)));
        setOfLines.add(new Line(new Point(-1000, -100000), new Point(2, 2)));
        setOfLines.add(new Line(new Point(-90, -4), new Point(43, 2)));
        setOfLines.add(new Line(new Point(10, 20), new Point(26, -2)));
        setOfLines.add(new Line(new Point(1022, -2), new Point(333, -2)));
        setOfLines.add(new Line(new Point(-123, 135), new Point(-144, -2222)));
        setOfLines.add(new Line(new Point(1222, -45), new Point(-222, 900)));
        setOfLines.add(new Line(new Point(345, 90), new Point(2, 2)));
        setOfLines.add(new Line(new Point(124, -129), new Point(-244, 12)));
        setOfLines.add(new Line(new Point(9993, 1), new Point(99, 9)));
        setOfLines.add(new Line(new Point(923, 1), new Point(92, 79)));
        setOfLines.add(new Line(new Point(-244, 1), new Point(28, 25)));
        setOfLines.add(new Line(new Point(-21, 1), new Point(2, 65)));
        setOfLines.add(new Line(new Point(0, 90), new Point(60, 10)));
        setOfLines.add(new Line(new Point(1, 1), new Point(46, 71)));
    }

    public Set<Line> getSetOfLines() {
        return setOfLines;
    }

    private Line getLineWithTheSmallestX() {

        Line smallest = null;

        for(Line l : setOfLines) {
            if (smallest == null) {
                smallest = l;
                continue;
            }
            if((l.getPoint1().getX() < smallest.getPoint1().getX() && l.getPoint1().getX() < smallest.getPoint2().getX()) ||
                    (l.getPoint2().getX() < smallest.getPoint1().getX() && l.getPoint2().getX() < smallest.getPoint2().getX())) {
                smallest = l;
            }
        }

        return smallest;
    }

    private boolean deleteLine(Line line) {
        return setOfLines.remove(line);
    }

    @Override
    public String toString() {
        return "SetOfLines{" +
                "setOfLines=" + setOfLines +
                '}';
    }

    public Point getCrossPointOfTwoSLinesWithTheSmallestX() {
        Line line1 = getLineWithTheSmallestX();
        deleteLine(line1);
        Line line2 = getLineWithTheSmallestX();
        deleteLine(line2);


        double x1 = line1.getPoint1().getX();
        double x2 = line1.getPoint2().getX();
        double x3 = line2.getPoint1().getX();
        double x4 = line2.getPoint2().getX();

        double y1 = line1.getPoint1().getY();
        double y2 = line1.getPoint2().getY();
        double y3 = line2.getPoint1().getY();
        double y4 = line2.getPoint2().getY();

        if(x1 > x2) {
            double tmp = x1;
            x1 = x2;
            x2 = tmp;

            tmp = y1;
            y1 = y2;
            y2 = y1;
        }
        if(x3 > x4) {
            double tmp = x3;
            x3 = x4;
            x4 = tmp;

            tmp = y3;
            y3 = y4;
            y4 = y3;
        }

        double k1 = (Math.abs(y1 - y2) < THRESHOLD) ? 0 : (y2 - y1)/(x2 - x1);
        double k2 = (Math.abs(y3 - y4) < THRESHOLD) ? 0 : (y4 - y3)/(x4 - x3);
        if (Math.abs(k1 - k2) < THRESHOLD) return null;

        double b1 = y1 - (k1 * x1);
        double b2 = y3 - (k2 * x3);

        double x = (b2 - b1)/(k1 - k2);
        double y = k1 * x + b1;

        return ((x1 <= x4 && x4 <= x2) || x1 <= x3 && x3 <= x2) ? new Point(x, y) : null;
    }
}
