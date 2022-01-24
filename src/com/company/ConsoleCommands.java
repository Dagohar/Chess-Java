package com.company;

import java.io.IOException;

// Klasa dotyczy różnych działań w konsoli
public class ConsoleCommands {
    // Works only for Windows Console
    public static void ClearWindowsConsole() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    public static void printCharBlack(char character) {
        System.out.print("\u001B[90m" + character + "\u001B[0m");
    }
    public static void printStringBlack(String string) { System.out.println("\u001B[90m" + string + "\u001B[0m"); }
    public static void printStringRed(String string) { System.out.print("\u001B[31m" + string + "\u001B[0m"); }
    public static void printStringRedLn(String string) { System.out.println("\u001B[31m" + string + "\u001B[0m"); }
}
