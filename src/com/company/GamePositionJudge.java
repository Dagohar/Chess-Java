package com.company;

import javafx.util.Pair;

public class GamePositionJudge {
    private final BoardPiecesPosition piecesPosition;

    public GamePositionJudge(BoardPiecesPosition piecesPosition) { this.piecesPosition = piecesPosition; }

    // Szach
    public boolean IsChecked(Pair<Integer, Integer> kingPosition , boolean isKingBlack) {


        return false;
    }


    // Mat
    public boolean IsMated(boolean isKingBlack) {


        return false;
    }


    // Pat
    public boolean IsStalemated(boolean isKingBlack) {


        return false;
    }


    // Roszada
    public boolean CanCastle() {


        return false;
    }
}
