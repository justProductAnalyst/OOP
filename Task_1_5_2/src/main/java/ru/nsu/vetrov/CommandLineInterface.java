package ru.nsu.vetrov;

import org.apache.commons.cli.*;

/**
 * This class provides a command-line interface for interacting with a Notebook.
 */
public class CommandLineInterface {

    /**
     * Executes a command based on the provided command line arguments.
     * It allows adding, removing, and showing notes in a Notebook.
     *
     * @param args The command line arguments. It supports the following options:
     *             - add <title> <note>: to add a new note with the specified title and note.
     *             - rm <title>: to remove a note with the specified title.
     *             - show [options]: to show notes. If no options are provided, it shows all notes.
     *             Options can be a date range, a keyword for filtering, etc. (implementation needed).
     * @param notebook The Notebook instance where notes are stored and managed.
     * @param serializer The NotebookSerializer instance used for persisting changes to the notebook.
     */
    public static void executeCommand(
            String[] args,
            Notebook notebook,
            NotebookSerializer serializer
    ) {
        Options options = new Options();

        Option add = new Option("add", true, "Add a note");
        add.setArgs(2);
        options.addOption(add);

        Option remove = new Option("rm", true, "Remove a note");
        options.addOption(remove);

        Option show = new Option("show", true, "Show notes");
        show.setArgs(Option.UNLIMITED_VALUES);
        options.addOption(show);

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("add")) {
                String[] values = cmd.getOptionValues("add");
                notebook.addNote(values[0], values[1]);
                serializer.serialize(notebook);
                System.out.println("Note added successfully.");
            } else if (cmd.hasOption("rm")) {
                String title = cmd.getOptionValue("rm");
                notebook.removeNote(title);
                serializer.serialize(notebook);
                System.out.println("Note removed successfully.");
            } else if (cmd.hasOption("show")) {
                String[] values = cmd.getOptionValues("show");
                if (values == null || values.length == 0) {
                    notebook.getAllNotes().forEach(System.out::println);
                } else {
                    // Further implementation for the date range and keyword filtering
                }
            }
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
