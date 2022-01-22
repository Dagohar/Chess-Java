package com.company;

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
                case 'S': isMoveValid = !IsKingInDangerOnMove() && CanKnightMove() && !IsAvoidingPiecesOnPath(); break;
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
}
