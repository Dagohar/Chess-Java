package com.company;

import custom.math.MathExtended;
import javafx.util.Pair;

public class MoveConditions {
    public static boolean IsVerticalLine(Pair<Integer, Integer> pos, Pair<Integer, Integer> des) {
        return pos.getKey() - des.getKey() == 0;
    }

    public static boolean IsHorizontalLine(Pair<Integer, Integer> pos, Pair<Integer, Integer> des) {
        return pos.getValue() - des.getValue() == 0;
    }

    public static boolean IsDiagonalLine(Pair<Integer, Integer> pos, Pair<Integer, Integer> des) {
        return MathExtended.IsSquareDiagonal(pos, des);
    }

    public static boolean IsOneFieldAway(Pair<Integer, Integer> pos, Pair<Integer, Integer> des) {
        if(Math.abs(pos.getKey() - des.getKey()) > 1)
            return false;
        if(Math.abs(pos.getValue() - des.getValue()) > 1)
            return false;
        return true;
    }
}
