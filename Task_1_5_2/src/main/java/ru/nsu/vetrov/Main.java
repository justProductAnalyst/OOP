package ru.nsu.vetrov;

import java.io.IOException;

/**
 * The Main class serves as the entry point for the application.
 * It handles the initialization and setup of the Notebook and its Serializer.
 */
@ExcludeFromJacocoGeneratedReport
public class Main {
    /**
    * The main method is the entry point of the application.
    *
    * @param args The command-line arguments passed to the application.
    */
    public static void main(String[] args) {
        NotebookSerializer serializer = new NotebookSerializer();
        Notebook notebook;
        try {
            notebook = serializer.deserialize();
        } catch (IOException e) {
            System.out.println("Error reading notebook data. Starting with an empty notebook.");
            notebook = new Notebook();
        }

        CommandLineInterface cli = new CommandLineInterface(notebook, serializer);
        cli.executeCommand(args);
    }
}
