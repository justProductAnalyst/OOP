package ru.nsu.vetrov;

import org.apache.commons.cli.*;

public class CommandLineInterface {

    public static void executeCommand(String[] args, Notebook notebook, NotebookSerializer serializer) {
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
