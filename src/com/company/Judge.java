package com.company;

import custom.math.MathExtended;
import javafx.util.Pair;

// Klasa sprawdza legalność ruchów obu graczy i zwraca odpowiednią odpowiedź
public class Judge {
    private final BoardPiecesPosition piecesPosition;
    private final AttackingJudge attackJudge;
    private final GamePositionJudge gamePositionJudge;

    private BoardPiecesPosition.ChessField chessField;
    private Pair<Integer, Integer> piecePosition;
    private Pair<Integer, Integer> pieceDestination;

    public Judge(BoardPiecesPosition piecesPosition) {
        this.piecesPosition = piecesPosition;
        attackJudge = new AttackingJudge(piecesPosition);
        gamePositionJudge = new GamePositionJudge(piecesPosition);
    }

    public boolean CanMove(Pair<Integer, Integer> piecePosition, Pair<Integer, Integer> pieceDestination) {
        boolean isMoveValid = false;

        this.piecePosition = piecePosition;
        this.pieceDestination = pieceDestination;
        this.chessField = piecesPosition.getField(piecePosition);

        switch(chessField.piece) {
            case 'K': isMoveValid = CanKingMove() && !IsKingInDangerOnMove(); break;
            case 'H': isMoveValid = CanQueenMove() && !IsAvoidingPiecesOnPath(); break;
            case 'W': isMoveValid = CanRookMove() && !IsAvoidingPiecesOnPath(); break;
            case 'G': isMoveValid = CanBishopMove() && !IsAvoidingPiecesOnPath(); break;
            case 'S': isMoveValid = CanKnightMove(); break;
            case 'P': isMoveValid = CanPawnMove() && !IsAvoidingPiecesOnPath(); break;
        }

        if(isMoveValid && piecesPosition.getField(pieceDestination).piece != ' ') {
            isMoveValid = attackJudge.CanAttack(piecePosition, pieceDestination);
        }

        return isMoveValid;
    }

    public boolean CheckForCheck(boolean WhitesTurn)
    {
        if(WhitesTurn)
            return gamePositionJudge.IsChecked(GamePositionJudge.KingPositionBlack, true);
        else
            return gamePositionJudge.IsChecked(GamePositionJudge.KingPositionWhite, false);
    }

    // Sprawdza czy na trasie figury nie znajduje się inna figura (wyjątkiem jest skoczek)
    private boolean IsAvoidingPiecesOnPath() {
        int minKey, maxKey;
        int minVal, maxVal;

        minKey = Math.min(piecePosition.getKey(), pieceDestination.getKey());
        maxKey = Math.max(piecePosition.getKey(), pieceDestination.getKey());

        minVal = Math.min(piecePosition.getValue(), pieceDestination.getValue());
        maxVal = Math.max(piecePosition.getValue(), pieceDestination.getValue());

        if(MoveConditions.IsVerticalLine(piecePosition, pieceDestination)) {
            for(int val = minVal + 1; val < maxVal; val++) {
                if(piecesPosition.getField(new Pair<>(piecePosition.getKey(), val)).piece != ' ') {
                   return true;
                }
            }
        }
        else if(MoveConditions.IsHorizontalLine(piecePosition, pieceDestination)) {
            for(int key = minKey + 1; key < maxKey; key++) {
                if(piecesPosition.getField(new Pair<>(key, piecePosition.getValue())).piece != ' ') {
                    return true;
                }
            }
        }
        else if(MoveConditions.IsDiagonalLine(piecePosition, pieceDestination)) {
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

    // Sprawdza czy poruszenie figurą nie spowoduje odsłonięcia króla na szach
    private boolean IsKingInDangerOnMove() {
        if(PlayerInput.WhitesTurn) { return gamePositionJudge.IsChecked(pieceDestination, false); }
        else { return gamePositionJudge.IsChecked(pieceDestination, true); }
    }

    private boolean CanKingMove() {
        return (MoveConditions.IsVerticalLine(piecePosition, pieceDestination) ||
                MoveConditions.IsHorizontalLine(piecePosition, pieceDestination) ||
                MoveConditions.IsDiagonalLine(piecePosition, pieceDestination)) &&
                MoveConditions.IsOneFieldAway(piecePosition, pieceDestination);
    }

    private boolean CanQueenMove() {
        return MoveConditions.IsVerticalLine(piecePosition, pieceDestination) ||
                MoveConditions.IsHorizontalLine(piecePosition, pieceDestination) ||
                MoveConditions.IsDiagonalLine(piecePosition, pieceDestination);
    }

    private boolean CanRookMove() {
        return MoveConditions.IsVerticalLine(piecePosition, pieceDestination) ||
                MoveConditions.IsHorizontalLine(piecePosition, pieceDestination);
    }

    private boolean CanBishopMove() {
        return MoveConditions.IsDiagonalLine(piecePosition, pieceDestination);
    }

    private boolean CanKnightMove() {
        return MathExtended.RoundToDecimalPlaces(MathExtended.Magnitude(piecePosition, pieceDestination), 2) == 2.24;
    }

    private boolean CanPawnMove() {
        if(piecesPosition.getField(pieceDestination).piece != ' ') {
            return true;
        }

        if(MoveConditions.IsVerticalLine(piecePosition, pieceDestination)) {
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
}
