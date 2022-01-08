package com.company;

import javafx.util.Pair;

public class MathExtended {
    public static double Magnitude(Pair<Integer, Integer> initialPoint, Pair<Integer, Integer> finalPoint) {
        Pair<Integer, Integer> vectorLength = new Pair<> (
                finalPoint.getKey() - initialPoint.getKey(), finalPoint.getValue() - initialPoint.getValue()
        );

        return Math.sqrt(Math.pow(vectorLength.getKey(), 2) + Math.pow(vectorLength.getValue(), 2));
    }
}
