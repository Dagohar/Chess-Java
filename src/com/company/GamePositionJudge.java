package com.company;

import javafx.util.Pair;

public class GamePositionJudge {
    private final BoardPiecesPosition piecesPosition;

    public static Pair<Integer, Integer> KingPositionWhite = new Pair<>(4, 0);
    public static Pair<Integer, Integer> KingPositionBlack = new Pair<>(4, 7);

    public GamePositionJudge(BoardPiecesPosition piecesPosition)
    {
        this.piecesPosition = piecesPosition;
    }

    // Szach
    public boolean IsChecked(Pair<Integer, Integer> kingPosition , boolean isKingBlack) {
        // FIXME: Ten kod został napisany w 20 minut na kolanie. Musi Pan doktor wybaczyć, że taki szajs, ale przynajmniej działa... chyba...
        // FIXME: O matko boska częstochowska
        if(HorizontalCheck(kingPosition,isKingBlack, false))
            return true;
        if(HorizontalCheck(kingPosition,isKingBlack, true))
            return true;
        if(VerticalCheck(kingPosition,isKingBlack, false))
            return true;
        if(VerticalCheck(kingPosition,isKingBlack, true))
            return true;

        // FIXME: POMÓŻCIE MI PROSZĘ
        if(DiagonalCheck(kingPosition, isKingBlack, 1, 1))
            return true;
        if(DiagonalCheck(kingPosition, isKingBlack, 1, -1))
            return true;
        if(DiagonalCheck(kingPosition, isKingBlack, -1, 1))
            return true;
        if(DiagonalCheck(kingPosition, isKingBlack, -1, -1))
            return true;

        //FIXME: No comment; I agree
        // region Checked by Knight
        if(IsCheckedByKnight(kingPosition, isKingBlack, 2 , 1))
            return true;
        if(IsCheckedByKnight(kingPosition, isKingBlack, 1 , 2))
            return true;
        if(IsCheckedByKnight(kingPosition, isKingBlack, 2 , -1))
            return true;
        if(IsCheckedByKnight(kingPosition, isKingBlack, 1 , -2))
            return true;
        if(IsCheckedByKnight(kingPosition, isKingBlack, -2 , 1))
            return true;
        if(IsCheckedByKnight(kingPosition, isKingBlack, -1 , 2))
            return true;
        if(IsCheckedByKnight(kingPosition, isKingBlack, -2 , -1))
            return true;
        if(IsCheckedByKnight(kingPosition, isKingBlack, -1 , -2))
            return true;
        // endregion

        return false;
    }

    private boolean DiagonalCheck(Pair<Integer, Integer> kingPosition, boolean isKingBlack, int x, int y)
    {
        boolean pieceCheck = false;
        for(int i = 1; kingPosition.getKey() + i * x <= 7 && kingPosition.getKey() + i * x >= 0 && kingPosition.getValue() + i * y <= 7 && kingPosition.getValue() + i * y >= 0; i++)
        {
            BoardPiecesPosition.ChessField field = piecesPosition.getField(new Pair<>(kingPosition.getKey().intValue() + i * x, kingPosition.getValue().intValue() + i * y));
            System.out.print(field.piece);
            if ((field.piece == 'H' || field.piece == 'G') && field.isBlack != isKingBlack)
            {
                return true;
            } else if (field.piece == 'P' && field.isBlack != isKingBlack && !pieceCheck)
            {
                return true;
            } else if (field.piece != ' ')
            {
                break;
            }
            pieceCheck = true;
        }
        return false;
    }

    private boolean HorizontalCheck(Pair<Integer, Integer> kingPosition, boolean isKingBlack, boolean checkingRight) {
        BoardPiecesPosition.ChessField field;
        for(int i = kingPosition.getKey() + (checkingRight ? 1 : -1) ; checkingRight ? i <= 7 : i >= 0; i += (checkingRight ? 1 : -1))
        {
            field = piecesPosition.getField(new Pair<>(i, kingPosition.getValue()));
            if((field.piece == 'H' || field.piece == 'W') && field.isBlack != isKingBlack)
            {
                return true;
            }
            else if(field.piece != ' ')
            {
                break;
            }
        }
        return false;
    }

    private boolean VerticalCheck(Pair<Integer, Integer> kingPosition, boolean isKingBlack, boolean checkingUp) {
        BoardPiecesPosition.ChessField field;
        for(int i = kingPosition.getValue() + (checkingUp ? 1 : -1) ; checkingUp ? i <= 7 : i >= 0; i += (checkingUp ? 1 : -1))
        {
            field = piecesPosition.getField(new Pair<>(kingPosition.getKey(), i));
            if((field.piece == 'H' || field.piece == 'W') && field.isBlack != isKingBlack)
            {
                return true;
            }
            else if(field.piece != ' ')
            {
                break;
            }
        }
        return false;
    }

    private boolean IsCheckedByKnight(Pair<Integer, Integer> kingPosition, boolean isKingBlack, int i, int j) {
        if(kingPosition.getKey()+i <= 7 && kingPosition.getKey()+i >= 0 && kingPosition.getValue()+j <= 7 && kingPosition.getValue()+j >= 0)
        {
            BoardPiecesPosition.ChessField field = piecesPosition.getField(new Pair<>(kingPosition.getKey() + i, kingPosition.getValue() + j));
            if (field.piece == 'S' && field.isBlack != isKingBlack)
                return true;
        }
        return false;
    }

    // TODO: Mat
    public boolean IsMated(boolean isKingBlack) {


        return false;
    }


    // TODO: Pat
    public boolean IsStalemated(boolean isKingBlack) {


        return false;
    }


    // TODO: Roszada
    public boolean CanCastle() {


        return false;
    }
}
