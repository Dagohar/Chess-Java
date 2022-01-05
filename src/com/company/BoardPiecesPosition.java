package com.company;

import javafx.util.Pair;

public class BoardPiecesPosition {
    private final char[][] fields;

    public char getField(int x, int y) {
        return fields[x][y];
    }
    public char getField(Pair<Integer, Integer> coords) {
        return fields[coords.getKey()][coords.getValue()];
    }

    public void setField(int x, int y, char value) {
        fields[x][y] = value;
    }
    public void setField(Pair<Integer, Integer> coords, char value) {
        fields[coords.getKey()][coords.getValue()] = value;
    }

    public BoardPiecesPosition() {
        fields = new char[8][8];
        //fieldsBlack = new char[8][8]; TODO: We need to recognise which piece is black and which is white

        for(int x = 0; x < 8; x++) {
            for(int y = 0; y < 8; y++) {
                setField(x, y, ' ');
            }
        }
    }
}
