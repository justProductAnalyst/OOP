package ru.nsu.vetrov;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        NotebookSerializer serializer = new NotebookSerializer();
        Notebook notebook;
        try {
            notebook = serializer.deserialize();
        } catch (IOException e) {
            System.out.println("Error reading notebook data. Starting with an empty notebook.");
            notebook = new Notebook();
        }

        CommandLineInterface.executeCommand(args, notebook, serializer);
    }
}
