package com.company;

import java.io.*;
import java.util.*;
import java.text.*;
import java.lang.Runtime;

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
            ConsoleCommands.printStringRedLn("Exception: " + e.getMessage());
        }
        Thread FileSave = new Thread(() -> FilePrint.close());
        Runtime.getRuntime().addShutdownHook(FileSave);
    }

    public void WriteToFile(String content)
    {
        FilePrint.println(content);
    }
}
