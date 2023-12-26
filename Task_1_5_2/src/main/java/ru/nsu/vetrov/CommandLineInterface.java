package ru.nsu.vetrov;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * This class provides a command-line interface for interacting with a Notebook.
 */
@ExcludeFromJacocoGeneratedReport
public class CommandLineInterface {

    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    private final Notebook notebook;
    private final NotebookSerializer serializer;

    public CommandLineInterface(Notebook notebook, NotebookSerializer serializer) {
        this.notebook = notebook;
        this.serializer = serializer;
    }

    /**
     * Executes a command based on the provided command line arguments.
     * It allows adding, removing, and showing notes in a Notebook.
     */
    public void executeCommand(String[] args) {
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
            processCommands(cmd);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    /**
     * Function for processing commands
     */
    private void processCommands(CommandLine cmd) throws IOException {
        if (cmd.hasOption("add")) {
            addNoteCommand(cmd);
        }
        if (cmd.hasOption("rm")) {
            removeNoteCommand(cmd);
        }
        if (cmd.hasOption("show")) {
            showNotesCommand(cmd);
        }
    }

    /**
     * Function for adding note
     */
    private void addNoteCommand(CommandLine cmd) throws IOException {
        String[] values = cmd.getOptionValues("add");
        notebook.addNote(values[0], values[1]);
        serializer.serialize(notebook);
        System.out.println("Note added successfully.");
    }

    /**
     * Function for removing note
     */
    private void removeNoteCommand(CommandLine cmd) throws IOException {
        String title = cmd.getOptionValue("rm");
        notebook.removeNote(title);
        serializer.serialize(notebook);
        System.out.println("Note removed successfully.");
    }

    /**
     * Function for showing notes
     */
    private void showNotesCommand(CommandLine cmd) throws IOException {
        String[] values = cmd.getOptionValues("show");
        if (values == null || values.length == 0) {
            notebook.getAllNotes().forEach(System.out::println);
        } else {
            LocalDateTime start = parseDateTime(values[0]);
            LocalDateTime end = parseDateTime(values[1]);
            List<String> keywords = new ArrayList<>
                    (Arrays.asList(values).subList(2, values.length));
            List<Note> filteredNotes = notebook.getNotesInRangeWithKeywords(start, end, keywords);
            filteredNotes.forEach(System.out::println);
        }
    }

    /**
     * Parses a string to a LocalDateTime object using the predefined formatter.
     *
     * @param dateTimeStr The string to parse.
     * @return The LocalDateTime object.
     * @throws DateTimeParseException if the string cannot be parsed.
     */
    private LocalDateTime parseDateTime(String dateTimeStr) {
        return LocalDateTime.parse(dateTimeStr, formatter);
    }
}