package com.company;

// Klasa odpowiada za budowanie szachownicy i jej odświeżanie
public class ChessBoard {
    public BoardPiecesPosition boardPieces;

    // Tworzy szachownicę
    public void CreateBoard() {
        boardPieces = new BoardPiecesPosition();

        int horCoordsIndex = 56;
        System.out.print((char)horCoordsIndex-- + " |");

        // Tworzy czarne figury na szczycie szachownicy
        CreateField('W', true, 0, 7);
        CreateField('S', true, 1, 7);
        CreateField('G', true, 2, 7);
        CreateField('H', true, 3, 7);
        CreateField('K', true, 4, 7);
        CreateField('G', true, 5, 7);
        CreateField('S', true, 6, 7);
        CreateField('W', true, 7, 7);

        System.out.println();
        System.out.print((char)horCoordsIndex-- + " |");

        // Tworzy czarne pionki
        for(int x = 0; x < 8; x++) {
            CreateField('P', true, x, 6);
        }

        System.out.println();
        System.out.print((char)horCoordsIndex-- + " |");

        // Tworzy odstępy między białymi a czarnymi figurami
        for(int y = 5; y > 1; y--) {
            for(int x = 0; x < 8; x++) {
                CreateField(' ', false, x, y);
            }

            System.out.println();
            System.out.print((char)horCoordsIndex-- + " |");
        }

        // Tworzy białe pionki
        for(int x = 0; x < 8; x++) {
            CreateField('P', false, x, 1);
        }

        System.out.println();
        System.out.print((char)horCoordsIndex-- + " |");

        // Tworzy białe figury na dole szachownicy
        CreateField('W', false, 0, 0);
        CreateField('S', false, 1, 0);
        CreateField('G', false, 2, 0);
        CreateField('H', false, 3, 0);
        CreateField('K', false, 4, 0);
        CreateField('G', false, 5, 0);
        CreateField('S', false, 6, 0);
        CreateField('W', false, 7, 0);

        System.out.println();
        System.out.println("    a   b   c   d   e   f   g   h");
        System.out.println();
    }

    // Odświeża szachownicę po każdym ruchu
    public void UpdateBoard() {
        
    }

    // Wypisuje pole w konsoli i przydziela figurę do tablicy pól
    private void CreateField(char piece, boolean isBlack, int x, int y) {
        System.out.print(" ");

        if(isBlack) {
            ConsoleCommands.printBlack(piece);
        }
        else {
            System.out.print(piece);
        }

        boardPieces.setField(x, y, piece);

        System.out.print(" |");
    }
}
