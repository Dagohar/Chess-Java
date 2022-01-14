package com.company;

import javafx.util.Pair;

// Klasa sprawdza legalność ruchów obu graczy i zwraca odpowiednią odpowiedź
public class Judge {
    private final BoardPiecesPosition piecesPosition;

    private BoardPiecesPosition.ChessField chessField;
    private Pair<Integer, Integer> piecePosition;
    private Pair<Integer, Integer> pieceDestination;

    public Judge(BoardPiecesPosition piecesPosition) {
        this.piecesPosition = piecesPosition;
    }

    public boolean CanMove(Pair<Integer, Integer> piecePosition, Pair<Integer, Integer> pieceDestination) {
        this.piecePosition = piecePosition;
        this.pieceDestination = pieceDestination;

        if(IsNotEmpty() && !IsSamePlace()) {
            switch(chessField.piece) {
                case 'K': return CanKingMove();
                case 'H': return !IsKingInDangerOnMove() && CanQueenMove() && !IsAvoidingPiecesOnPath();
                case 'W': return !IsKingInDangerOnMove() && CanRookMove() && !IsAvoidingPiecesOnPath();
                case 'G': return !IsKingInDangerOnMove() && CanBishopMove() && !IsAvoidingPiecesOnPath();
                case 'S': return !IsKingInDangerOnMove() && CanKnightMove() && !IsAvoidingPiecesOnPath();
                case 'P': return !IsKingInDangerOnMove() && CanPawnMove() && !IsAvoidingPiecesOnPath();
            }
        }

        return false;
    }

    private boolean IsNotEmpty() {
        return (chessField = piecesPosition.getField(piecePosition)).piece != 32;
    }

    private boolean IsSamePlace() {
        return (piecePosition.getKey().equals(pieceDestination.getKey()) && piecePosition.getValue().equals(pieceDestination.getValue()));
    }

    private boolean IsAvoidingPiecesOnPath() {
        //TODO:
        return false;
    }

    private boolean IsKingInDangerOnMove() {
        //TODO:
        return false;
    }

    private boolean CanKingMove() {
        //TODO:
        return true;
    }

    private boolean CanQueenMove() {
        //TODO:
        return true;
    }

    private boolean CanRookMove() {
        //TODO:
        return true;
    }

    private boolean CanBishopMove() {
        //TODO:
        return IsDiagonalLine();
    }

    private boolean CanKnightMove() {
        //TODO:
        return true;
    }

    private boolean CanPawnMove() {
        if(IsVerticalLine()) {
            if(chessField.isBlack) {
                if(piecePosition.getValue() == 6) {
                    return piecePosition.getValue() - pieceDestination.getValue() <= 2;
                }
                else {
                    return piecePosition.getValue() - pieceDestination.getValue() == 1;
                }
            }
            else {
                if(piecePosition.getValue() == 1) {
                    return pieceDestination.getValue() - piecePosition.getValue() <= 2;
                }
                else {
                    return pieceDestination.getValue() - piecePosition.getValue() == 1;
                }
            }
        }

        return false;
    }

    private boolean IsVerticalLine() {
        return piecePosition.getKey() - pieceDestination.getKey() == 0;
    }

    private boolean IsHorizontalLine() {
        return piecePosition.getValue() - pieceDestination.getValue() == 0;
    }

    private boolean IsDiagonalLine() {
        return MathExtended.IsSquareDiagonal(piecePosition, pieceDestination);
    }
}
