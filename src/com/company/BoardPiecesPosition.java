package com.company;

import javafx.util.Pair;

public class BoardPiecesPosition {
    public class ChessField {
        public char piece;
        public boolean isBlack;
        public boolean isChecked;

        public ChessField() {
            piece = ' ';
            isBlack = false;
            isChecked = false;
        }

        public ChessField(char piece, boolean isBlack) {
            this.piece = piece;
            this.isBlack = isBlack;
        }

        public ChessField(ChessField cf) {
            piece = cf.piece;
            isBlack = cf.isBlack;
        }
    }

    private ChessField[][] fields;

    public ChessField getField(Pair<Integer, Integer> coords) {
        return fields[coords.getKey()][coords.getValue()];
    }

    public void setField(Pair<Integer, Integer> coords, char value, boolean isBlack) {
        fields[coords.getKey()][coords.getValue()] = new ChessField(value, isBlack);
    }
    public void setField(Pair<Integer, Integer> coords, ChessField field) {
        fields[coords.getKey()][coords.getValue()] = new ChessField(field);
    }

    public BoardPiecesPosition() {
        fields = new ChessField[8][8];

        for(int x = 0; x < 8; x++) {
            for(int y = 0; y < 8; y++) {
                fields[x][y] = new ChessField();
            }
        }
    }
}
