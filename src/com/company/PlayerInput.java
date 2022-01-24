package com.company;

import custom.exceptions.*;
import javafx.util.Pair;
import java.util.Scanner;

// Zarządza danymi wejściowymi gracza
public class PlayerInput {
    private Pair<Character, Integer> position;
    private Pair<Integer, Integer> getNumberPosition() {
        return Converter.ChessToNumberCoordinates(position);
    }

    private Pair<Character, Integer> destination;
    private Pair<Integer, Integer> getNumberDestination() {
        return Converter.ChessToNumberCoordinates(destination);
    }

    public static boolean WhitesTurn = true;
    private boolean WrongMove = false;

    private final Scanner scanner = new Scanner(System.in);
    private final ChessBoard board;
    private final BoardPiecesPosition piecesPosition;
    private final Judge judge;
    private final FileAlgebNotation notation;

    public PlayerInput(ChessBoard board) {
        this.board = board;
        this.piecesPosition = board.boardPieces;
        this.judge = new Judge(this.piecesPosition);
        this.notation = new FileAlgebNotation();
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
        try {
            String input = scanner.nextLine();
            if(input.length() != 2) { throw new StringIndexOutOfBoundsException(); }
            position = new Pair<>(Character.toUpperCase(input.charAt(0)), input.charAt(1) - 48);
            if(!IsInputValid(position)) { throw new StringIndexOutOfBoundsException(); }

            BoardPiecesPosition.ChessField chessField = piecesPosition.getField(Converter.ChessToNumberCoordinates(position));
            if(chessField.piece == 32) { throw new EmptyFieldException(); }
            if(chessField.isBlack == WhitesTurn) { throw new WrongColorException(); }
        }
        catch (StringIndexOutOfBoundsException e) {
            ConsoleCommands.printStringRedLn("Wykryto złą pozycję.");
            TakePositionInput();
        }
        catch (WrongColorException wce) {
            ConsoleCommands.printStringRedLn(wce.getMessage());
            TakePositionInput();
        }
        catch (EmptyFieldException efe) {
            ConsoleCommands.printStringRedLn(efe.getMessage());
            TakePositionInput();
        }
    }

    // Przyjmuje pozycję, na którą chcemy przesunąć figurę
    private void TakeDestinationInput() {
        System.out.print("Wpisz współrzędne pozycji, na którą chcesz przesunąć figurę np. {E4}: ");
        try {
            String input = scanner.nextLine();
            if(input.length() != 2) { throw new StringIndexOutOfBoundsException(); }
            destination = new Pair<>(Character.toUpperCase(input.charAt(0)), input.charAt(1) - 48);
            if(!IsInputValid(destination)) { throw new StringIndexOutOfBoundsException(); }
        }
        catch (StringIndexOutOfBoundsException e) {
            ConsoleCommands.printStringRedLn("Wykryto złą pozycję.");
            TakeDestinationInput();
        }
    }

    private boolean IsInputValid(Pair<Character, Integer> input) {
        Pair<Integer, Integer> numberCoords = Converter.ChessToNumberCoordinates(input);

        return (numberCoords.getKey() >= 0 && numberCoords.getKey() <= 7) &&
                (numberCoords.getValue() >= 0 && numberCoords.getValue() <= 7);
    }

    private void Move()
    {
        if(judge.CanMove(getNumberPosition(), getNumberDestination())) {
            board.UpdateBoard(getNumberPosition(), getNumberDestination());
            WhitesTurn = !WhitesTurn;
            board.MoveNumber++;
        }
        else
            WrongMove = true;
    }

    private void DisplayMoveDetails() {
        System.out.println();

        if(!WrongMove) {
            notation.WriteToFile(board.MoveNumber, piecesPosition.getField(getNumberDestination()).piece, position, destination);
            System.out.println(
                    "Przesunięto " +
                            ChessDictionary.TranslateSymbolToName(piecesPosition.getField(getNumberDestination()).piece, PolishCases.biernik) +
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
