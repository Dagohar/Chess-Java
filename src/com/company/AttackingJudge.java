package com.company;

import javafx.util.Pair;

// Klasa zajmuje sprawdzaniem atakowania innych figur
public class AttackingJudge {
    private final BoardPiecesPosition piecesPosition;

    private BoardPiecesPosition.ChessField chessField;
    private Pair<Integer, Integer> piecePosition;
    private Pair<Integer, Integer> pieceDestination;

    public AttackingJudge(BoardPiecesPosition piecesPosition) {
        this.piecesPosition = piecesPosition;
    }

    public boolean CanAttack(Pair<Integer, Integer> piecePosition, Pair<Integer, Integer> pieceDestination) {
        chessField = piecesPosition.getField(piecePosition);
        this.piecePosition = piecePosition;
        this.pieceDestination = pieceDestination;

        if(piecesPosition.getField(pieceDestination).piece == 'K' || IsAttackingSameColor()) {
            return false;
        }

        switch(chessField.piece) {
            case 'K':
            case 'H':
            case 'W':
            case 'G':
            case 'S': return true;
            case 'P': return CanPawnAttack();
        }

        return false;
    }

    private boolean IsAttackingSameColor() {
        return chessField.isBlack == piecesPosition.getField(pieceDestination).isBlack;
    }

    private boolean CanPawnAttack() {
        if(chessField.isBlack) {
            return (Math.abs(piecePosition.getKey() - pieceDestination.getKey()) == 1) &&
                    (piecePosition.getValue() - pieceDestination.getValue() == 1);
        }
        else {
            return (Math.abs(piecePosition.getKey() - pieceDestination.getKey()) == 1) &&
                    (piecePosition.getValue() - pieceDestination.getValue() == -1);
        }
    }
}
