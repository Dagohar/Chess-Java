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
        boolean pieceCheck = false;
        BoardPiecesPosition.ChessField field;
        for(int i = kingPosition.getValue() + 1; i <= 7; i++) //Vertical check - down
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
        for(int i = kingPosition.getValue() - 1; i >= 0; i--) //Vertical check - up
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
        for(int i = kingPosition.getKey() + 1; i <= 7; i++) //Horizontal check - down
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
        for(int i = kingPosition.getKey() - 1; i >= 0; i--) //Horizontal check - up
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
        for(int i = kingPosition.getKey() + 1; i <= 7; i++) //Diagonal check \
        {
            field = piecesPosition.getField(new Pair<>(i, i));
            if((field.piece == 'H' || field.piece == 'G') && field.isBlack != isKingBlack)
            {
                return true;
            }
            else if(field.piece != ' ')
            {
                break;
            }
            field = piecesPosition.getField(new Pair<>(7 - i, i));
            if((field.piece == 'H' || field.piece == 'G') && field.isBlack != isKingBlack)
            {
                return true;
            }
            else if(field.piece != ' ')
            {
                break;
            }
        }
        for(int i = kingPosition.getKey() - 1; i >= 0; i--) //Diagonal check \
        {
            field = piecesPosition.getField(new Pair<>(i, i));
            if((field.piece == 'H' || field.piece == 'G') && field.isBlack != isKingBlack)
            {
                return true;
            }
            else if(field.piece == 'P' && field.isBlack != isKingBlack && !pieceCheck)
            {
                return true;
            }
            else if(field.piece != ' ')
            {
                break;
            }
            field = piecesPosition.getField(new Pair<>(7 - i, i));
            if((field.piece == 'H' || field.piece == 'G') && field.isBlack != isKingBlack)
            {
                return true;
            }
            else if(field.piece == 'P' && field.isBlack != isKingBlack && !pieceCheck)
            {
                return true;
            }
            else if(field.piece != ' ')
            {
                break;
            }
        }

        //FIXME: No comment; I agree
        field = piecesPosition.getField(new Pair<>(kingPosition.getKey()-1, kingPosition.getValue()-2));
        if(field.piece == 'K' && field.isBlack != isKingBlack)
            return true;
        field = piecesPosition.getField(new Pair<>(kingPosition.getKey()-2, kingPosition.getValue()-1));
        if(field.piece == 'K' && field.isBlack != isKingBlack)
            return true;
        field = piecesPosition.getField(new Pair<>(kingPosition.getKey()+1, kingPosition.getValue()-2));
        if(field.piece == 'K' && field.isBlack != isKingBlack)
            return true;
        field = piecesPosition.getField(new Pair<>(kingPosition.getKey()+2, kingPosition.getValue()-1));
        if(field.piece == 'K' && field.isBlack != isKingBlack)
            return true;
        field = piecesPosition.getField(new Pair<>(kingPosition.getKey()-1, kingPosition.getValue()+2));
        if(field.piece == 'K' && field.isBlack != isKingBlack)
            return true;
        field = piecesPosition.getField(new Pair<>(kingPosition.getKey()-2, kingPosition.getValue()+1));
        if(field.piece == 'K' && field.isBlack != isKingBlack)
            return true;
        field = piecesPosition.getField(new Pair<>(kingPosition.getKey()+1, kingPosition.getValue()+2));
        if(field.piece == 'K' && field.isBlack != isKingBlack)
            return true;
        field = piecesPosition.getField(new Pair<>(kingPosition.getKey()+2, kingPosition.getValue()+1));
        if(field.piece == 'K' && field.isBlack != isKingBlack)
            return true;
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
