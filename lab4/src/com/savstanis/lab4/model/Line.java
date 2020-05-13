package com.savstanis.lab4.model;

public class Line {
    final Point point1;
    final Point point2;

    public Line(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    public Point getPoint1() {
        return point1;
    }

    public Point getPoint2() {
        return point2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Line line = (Line) o;

        if (!point1.equals(line.point1)) return false;
        return point2.equals(line.point2);
    }

    @Override
    public int hashCode() {
        int result = point1.hashCode();
        result = 31 * result + point2.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Line: { " +
                "point1=" + point1 +
                ", point2=" + point2 +
                " }\n";
    }
}
