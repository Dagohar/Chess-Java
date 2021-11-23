package com.company;

public class BoardPosition {
    private char[][] fields;
    
    public void OnStart() {
        fields = new char[8][8];

        for(int i = 0; i < 8; i++) {
            for(int p = 0; p < 8; p++) {
                fields[i][p] = ' ';
            }
        }
    }
}
