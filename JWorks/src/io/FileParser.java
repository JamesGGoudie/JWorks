package io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileParser {

    private static final char DELIMITER = '|';

    /**
     * Generates a list of interpreter commands from the given file. Uses the default delimiter.
     * @param file the file to read from
     * @param command the command to use as the first argument
     * @return a list of String arrays that are valid input into the interpreter
     */
    public static List<String[]> generateArgStringFromFile(File file, String command) throws IOException {
        return generateArgStringFromFile(file, command, DELIMITER);
    }

    /**
     * Generates a list of interpreter commands from the given file.
     * @param file the file to read from
     * @param command the command to use as the first argument
     * @param delimiter the delimiter to split each line of the file into tokens
     * @return a list of String arrays that are valid input into the interpreter
     */
    public static List<String[]> generateArgStringFromFile(File file, String command, char delimiter) throws IOException {
        List<String[]> argsList = new ArrayList<>();

        // Setup input streams
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        // Loop through file
        String currentLine;

        while((currentLine = bufferedReader.readLine()) != null) {
            String[] rawArgs = currentLine.split(String.valueOf(delimiter));
            String[] args = new String[rawArgs.length + 1];

            // Add command argument and copy remaining
            args[0] = command;
            for (int i = 1; i < args.length; i++) {
                args[i] = rawArgs[i-1];
            }

            argsList.add(args);
        }

        return argsList;
    }
}