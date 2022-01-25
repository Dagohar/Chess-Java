package custom.text;

import java.io.IOException;

// Klasa dotyczy różnych działań w konsoli
public class PrintColoredText {
    public static void printCharBlack(char character) {
        System.out.print("\u001B[90m" + character + "\u001B[0m");
    }
    public static void printStringBlack(String string) { System.out.print("\u001B[90m" + string + "\u001B[0m"); }
    public static void printStringBlackLn(String string) { System.out.println("\u001B[90m" + string + "\u001B[0m"); }

    public static void printCharRed(char character) {
        System.out.print("\u001B[31m" + character + "\u001B[0m");
    }
    public static void printStringRed(String string) { System.out.print("\u001B[31m" + string + "\u001B[0m"); }
    public static void printStringRedLn(String string) { System.out.println("\u001B[31m" + string + "\u001B[0m"); }

    public static void printStringBlueLn(String string) { System.out.println("\u001b[34m" + string + "\u001B[0m"); }
}
