package com.company;

public class Main {
    public static void main(String[] args) throws Exception {
        ChessBoard bc = new ChessBoard();
        bc.CreateBoard();

        PlayerInput inputManager = new PlayerInput(bc);
        while(true) { //Infinite loop for now
            inputManager.TakeInput();
        }
    }
}
