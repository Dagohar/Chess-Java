package com.company;

import java.io.*;
import java.util.*;
import java.text.*;
import java.lang.Runtime;

import custom.text.PrintColoredText;
import javafx.util.Pair;

public class FileAlgebNotation {

    PrintWriter FilePrint;
    String FileName;

    FileAlgebNotation()
    {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
            Date date = new Date();
            FileName = "szachy-" + dateFormat.format(date) + ".txt";
            FilePrint = new PrintWriter(new BufferedWriter(new FileWriter(FileName, true)));
        }
        catch(Exception e)
        {
            PrintColoredText.printStringRedLn("Exception: " + e.getMessage());
        }
        Thread FileSave = new Thread(() -> FilePrint.close());
        Runtime.getRuntime().addShutdownHook(FileSave);
    }

    public void WriteToFile(int RoundNumber, char Piece, Pair<Character, Integer> position, Pair<Character, Integer> destination)
    {
        FilePrint.print(RoundNumber + ". ");
        if(Piece != 'P')
            FilePrint.print(Piece);
        FilePrint.println(position.getKey().toString().toLowerCase() + position.getValue() + "-"
                + destination.getKey().toString().toLowerCase() + destination.getValue());
    }
}
