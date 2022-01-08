package com.company;

import com.sun.xml.internal.bind.v2.TODO;
import javafx.util.Pair;

// Klasa sprawdza legalność ruchów obu graczy i zwraca odpowiednią odpowiedź
public class Judge {
    private BoardPiecesPosition piecesPosition;

    private Character piece;
    private Pair<Integer, Integer> piecePosition;
    private Pair<Integer, Integer> pieceDestination;

    public Judge(BoardPiecesPosition piecesPosition) {
        this.piecesPosition = piecesPosition;
    }

    public boolean CanMove(Pair<Integer, Integer> piecePosition, Pair<Integer, Integer> pieceDestination) {
        this.piecePosition = piecePosition;
        this.pieceDestination = pieceDestination;

        if(IsNotEmpty() && !IsSamePlace()) {
            switch(piece) {
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
        return (piece = piecesPosition.getField(piecePosition).piece) != 32;
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
        return true;
    }

    private boolean CanKnightMove() {
        //TODO:
        return true;
    }

    private boolean CanPawnMove() {
        //TODO:
        return true;
    }
}
