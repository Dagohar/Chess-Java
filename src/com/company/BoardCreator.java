package com.company;

public class BoardCreator {
    public void CreateStartingBoard() {
        int horCoordsIndex = 65;

        System.out.print((char)horCoordsIndex++ + " |");

        CreateField('w', true);
        CreateField('s', true);
        CreateField('g', true);
        CreateField('h', true);
        CreateField('k', true);
        CreateField('g', true);
        CreateField('s', true);
        CreateField('w', true);

        System.out.println();
        System.out.print((char)horCoordsIndex++ + " |");

        for(int i = 0; i < 8; i++) {
            CreateField('p', true);
        }

        System.out.println();
        System.out.print((char)horCoordsIndex++ + " |");

        for(int i = 0; i < 4; i++) {
            for(int p = 0; p < 8; p++) {
                CreateField(' ', false);
            }

            System.out.println();
            System.out.print((char)horCoordsIndex++ + " |");
        }

        for(int i = 0; i < 8; i++) {
            CreateField('p', false);
        }

        System.out.println();
        System.out.print((char)horCoordsIndex++ + " |");

        CreateField('w', false);
        CreateField('s', false);
        CreateField('g', false);
        CreateField('h', false);
        CreateField('k', false);
        CreateField('g', false);
        CreateField('s', false);
        CreateField('w', false);

        System.out.println();
        System.out.print("    1   2   3   4   5   6   7   8");
    }

    public void UpdateBoard() {
        
    }

    private void CreateField(char piece, boolean black) {
        System.out.print(" ");

        if(black) {
            ConsoleCommands.printBlack(piece);
        }
        else {
            System.out.print(piece);
        }

        System.out.print(" |");
    }
}
