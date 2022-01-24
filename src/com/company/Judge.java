package com.company;

import custom.math.MathExtended;
import javafx.util.Pair;

// Klasa sprawdza legalność ruchów obu graczy i zwraca odpowiednią odpowiedź
public class Judge {
    private final BoardPiecesPosition piecesPosition;
    private final AttackingJudge attackJudge;

    private BoardPiecesPosition.ChessField chessField;
    private Pair<Integer, Integer> piecePosition;
    private Pair<Integer, Integer> pieceDestination;

    public Judge(BoardPiecesPosition piecesPosition) {
        this.piecesPosition = piecesPosition;
        attackJudge = new AttackingJudge(piecesPosition);
    }

    public boolean CanMove(Pair<Integer, Integer> piecePosition, Pair<Integer, Integer> pieceDestination) {
        boolean isMoveValid = false;

        this.piecePosition = piecePosition;
        this.pieceDestination = pieceDestination;

        if(IsNotEmpty() && !IsSamePlace()) {
            switch(chessField.piece) {
                case 'K': isMoveValid = CanKingMove(); break;
                case 'H': isMoveValid = !IsKingInDangerOnMove() && CanQueenMove() && !IsAvoidingPiecesOnPath(); break;
                case 'W': isMoveValid = !IsKingInDangerOnMove() && CanRookMove() && !IsAvoidingPiecesOnPath(); break;
                case 'G': isMoveValid = !IsKingInDangerOnMove() && CanBishopMove() && !IsAvoidingPiecesOnPath(); break;
                case 'S': isMoveValid = !IsKingInDangerOnMove() && CanKnightMove(); break;
                case 'P': isMoveValid = !IsKingInDangerOnMove() && CanPawnMove() && !IsAvoidingPiecesOnPath(); break;
            }
        }

        if(isMoveValid && piecesPosition.getField(pieceDestination).piece != ' ') {
            isMoveValid = attackJudge.CanAttack(piecePosition, pieceDestination);
        }

        return isMoveValid;
    }

    private boolean IsNotEmpty() {
        return (chessField = piecesPosition.getField(piecePosition)).piece != 32;
    }

    private boolean IsSamePlace() {
        return (piecePosition.getKey().equals(pieceDestination.getKey()) && piecePosition.getValue().equals(pieceDestination.getValue()));
    }

    private boolean IsAvoidingPiecesOnPath() {
        int minKey, maxKey;
        int minVal, maxVal;

        minKey = Math.min(piecePosition.getKey(), pieceDestination.getKey());
        maxKey = Math.max(piecePosition.getKey(), pieceDestination.getKey());

        minVal = Math.min(piecePosition.getValue(), pieceDestination.getValue());
        maxVal = Math.max(piecePosition.getValue(), pieceDestination.getValue());

        if(IsVerticalLine()) {
            for(int val = minVal + 1; val < maxVal; val++) {
                if(piecesPosition.getField(new Pair<>(piecePosition.getKey(), val)).piece != ' ') {
                   return true;
                }
            }
        }
        else if(IsHorizontalLine()) {
            for(int key = minKey + 1; key < maxKey; key++) {
                if(piecesPosition.getField(new Pair<>(key, piecePosition.getValue())).piece != ' ') {
                    return true;
                }
            }
        }
        else if(IsDiagonalLine()) {
            int x1 = piecePosition.getKey(), x2 = pieceDestination.getKey();
            int y1 = piecePosition.getValue(), y2 = pieceDestination.getValue();

            if(x2 < x1) {
                x1 = pieceDestination.getKey();
                x2 = piecePosition.getKey();
                y1 = pieceDestination.getValue();
                y2 = piecePosition.getValue();
            }

            for(int x = x1 + 1; x < x2; x++) {
                y1 += y2 > y1 ? 1 : -1;

                if(piecesPosition.getField(new Pair<>(x, y1)).piece != ' ') {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean IsKingInDangerOnMove() {
        //TODO:
        return false;
    }

    private boolean CanKingMove() { return (IsVerticalLine() || IsHorizontalLine() || IsDiagonalLine()) && IsOneFieldAway(); }

    private boolean CanQueenMove() {
        return IsVerticalLine() || IsHorizontalLine() || IsDiagonalLine();
    }

    private boolean CanRookMove() {
        return IsVerticalLine() || IsHorizontalLine();
    }

    private boolean CanBishopMove() {
        return IsDiagonalLine();
    }

    private boolean CanKnightMove() {
        return MathExtended.RoundToDecimalPlaces(MathExtended.Magnitude(piecePosition, pieceDestination), 2) == 2.24;
    }

    private boolean CanPawnMove() {
        if(piecesPosition.getField(pieceDestination).piece != ' ') {
            return true;
        }

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

    private boolean IsOneFieldAway() {
        if(Math.abs(piecePosition.getKey() - pieceDestination.getKey()) > 1)
            return false;
        if(Math.abs(piecePosition.getValue() - pieceDestination.getValue()) > 1)
            return false;
        return true;
    }
}
