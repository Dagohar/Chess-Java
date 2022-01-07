package com.company;

import javafx.util.Pair;
import java.util.Scanner;

// Zarządza danymi wejściowymi gracza
public class PlayerInput {
    private Pair<Character, Integer> position;
    public Pair<Character, Integer> getChessPosition() {
        return position;
    }
    public Pair<Integer, Integer> getNumberPosition() {
        return Converter.ChessToNumberCoordinates(position);
    }

    private Pair<Character, Integer> destination;
    public Pair<Character, Integer> getChessDestination() {
        return destination;
    }
    public Pair<Integer, Integer> getNumberDestination() {
        return Converter.ChessToNumberCoordinates(destination);
    }

    private boolean WrongMove = false;

    private final Scanner scanner = new Scanner(System.in);
    private final ChessBoard board;
    private final BoardPiecesPosition piecesPosition;

    public PlayerInput(ChessBoard board) {
        this.board = board;
        this.piecesPosition = board.boardPieces;
    }

    public void TakeInput() {
        TakePositionInput();
        TakeDestinationInput();
        Move();
        DisplayMoveDetails();
    }

    // Przyjmuje pozycję figury, którą chcemy poruszyć
    private void TakePositionInput() {
        System.out.print("Wpisz pozycję figury, którą chcesz poruszyć np. {E2}: ");
        String input = scanner.nextLine();
        position = new Pair<>(Character.toUpperCase(input.charAt(0)), input.charAt(1) - 48);
    }

    // Przyjmuje pozycję, na którą chcemy przesunąć figurę
    private void TakeDestinationInput() {
        System.out.print("Wpisz współrzędne pozycji, na którą chcesz przesunąć figurę np. {E4}: ");
        String input = scanner.nextLine();
        destination = new Pair<>(Character.toUpperCase(input.charAt(0)), input.charAt(1) - 48);
    }

    private void Move()
    {
        if(piecesPosition.getField(getNumberPosition()) != 32)
            board.UpdateBoard(getNumberPosition(), getNumberDestination());
        else
            WrongMove = true;
    }

    private void DisplayMoveDetails() {
        System.out.println();

        if(!WrongMove) {
            System.out.println(
                    "Przesunięto " +
                            ChessDictionary.TranslateSymbolToName(piecesPosition.getField(getNumberDestination()), PolishCases.biernik) +
                            " z pozycji " + position.getKey() + position.getValue() + " na pozycję " + destination.getKey() + destination.getValue()
            );
        }
        else
        {
            ConsoleCommands.printStringRedLn("Wykryto niedozwolony ruch");
            WrongMove = false;
        }
        board.ShowBoard();
    }
}
