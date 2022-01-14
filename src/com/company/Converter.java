package com.company;

import javafx.util.Pair;

// Klasa zajmuje się konwertowaniem poszczególnych danych
public class Converter {
    public static Pair<Integer, Integer> ChessToNumberCoordinates(Pair<Character, Integer> coords) {
        return new Pair<>(coords.getKey() - 65, coords.getValue() - 1);
    }

    public static Pair<Character, Integer> NumberToChessCoordinates(Pair<Integer, Integer> coords) {
        return new Pair<>(String.valueOf(coords.getKey() + 65).charAt(0), coords.getValue() + 1);
    }
}
