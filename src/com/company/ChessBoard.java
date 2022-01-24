package com.company;

import custom.text.PrintColoredText;
import javafx.util.Pair;

// Klasa odpowiada za budowanie szachownicy i jej odświeżanie
public class ChessBoard {
    public BoardPiecesPosition boardPieces;
    public int MoveNumber = 0;

    // Tworzy szachownicę
    public void CreateBoard() {
        boardPieces = new BoardPiecesPosition();

        if(PlayerInput.WhitesTurn) {
            System.out.println("RUCH BIAŁYCH");
        }
        else {
            PrintColoredText.printStringBlackLn("RUCH CZARNYCH");
        }

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
    public void UpdateBoard(Pair<Integer, Integer> position, Pair<Integer, Integer> destination) {
        boardPieces.setField(destination, boardPieces.getField(position));
        boardPieces.setField(position, ' ', false);
    }

    public void ShowBoard()
    {
        if(PlayerInput.WhitesTurn) {
            System.out.println("RUCH BIAŁYCH");
        }
        else {
            PrintColoredText.printStringBlackLn("RUCH CZARNYCH");
        }

        int horCoordsIndex = 56;

        for(int i = 0; i < 8; i++) {
            System.out.print((char) horCoordsIndex-- + " |");
            for(int j = 0; j < 8; j++) {
                System.out.print(" ");
                BoardPiecesPosition.ChessField field = boardPieces.getField(new Pair<>(j, 7 - i));
                PrintColor(field.piece, field.isBlack);
                System.out.print(" |");
            }
            System.out.println();
        }
        System.out.println("    a   b   c   d   e   f   g   h");
        System.out.println();
    }

    // Wypisuje pole w konsoli i przydziela figurę do tablicy pól
    private void CreateField(char piece, boolean isBlack, int x, int y) {
        System.out.print(" ");
        PrintColor(piece, isBlack);
        boardPieces.setField(new Pair<>(x, y), piece, isBlack);
        System.out.print(" |");
    }

    private void PrintColor(char piece, boolean isBlack)
    {
        if(isBlack) {
            PrintColoredText.printCharBlack(piece);
        }
        else {
            System.out.print(piece);
        }
    }
}
