package com.company;

import javafx.util.Pair;

public class MathExtended {
    public static double Magnitude(Pair<Integer, Integer> initialPoint, Pair<Integer, Integer> finalPoint) {
        Pair<Integer, Integer> vectorLength = new Pair<> (
                finalPoint.getKey() - initialPoint.getKey(), finalPoint.getValue() - initialPoint.getValue()
        );

        return Math.sqrt(Math.pow(vectorLength.getKey(), 2) + Math.pow(vectorLength.getValue(), 2));
    }

    public static boolean IsSquareDiagonal(Pair<Integer, Integer> firstPoint, Pair<Integer, Integer> lastPoint) {
        double length1, length2;

        length1 = Magnitude(firstPoint, new Pair<>(firstPoint.getKey(), lastPoint.getValue()));
        length2 = Magnitude(firstPoint, new Pair<>(lastPoint.getKey(), firstPoint.getValue()));

        System.out.println(firstPoint.getKey() + ", " + firstPoint.getValue() + " : " + firstPoint.getKey() + ", " + lastPoint.getValue());
        System.out.println(firstPoint.getKey() + ", " + firstPoint.getValue() + " : " + lastPoint.getKey() + ", " + firstPoint.getValue());

        return length1 == length2;
    }
}
