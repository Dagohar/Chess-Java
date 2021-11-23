package com.company;

import java.io.IOException;

public class ConsoleCommands {
    public static void ClearConsole() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    public static void printBlack(char character) {
        System.out.print("\u001B[90m" + character + "\u001B[0m");
    }
}
