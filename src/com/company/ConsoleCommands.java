package com.company;

import java.io.IOException;

// Klasa dotyczy różnych działań w konsoli
public class ConsoleCommands {
    // Works only for Windows Console
    public static void ClearWindowsConsole() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    // Wyświetla znak w czarnym kolorze
    public static void printBlack(char character) {
        System.out.print("\u001B[90m" + character + "\u001B[0m");
    }
}
