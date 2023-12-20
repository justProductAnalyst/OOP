package ru.nsu.vetrov;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
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

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
            "dd.MM.yyyy HH:mm");

    /**
     * Executes a command based on the provided command line arguments.
     * It allows adding, removing, and showing notes in a Notebook.
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
                    // Parse date range and keywords from the command line arguments
                    LocalDateTime start = parseDateTime(values[0]);
                    LocalDateTime end = parseDateTime(values[1]);
                    List<String> keywords = new ArrayList<>();
                    for (int i = 2; i < values.length; i++) {
                        keywords.add(values[i]);
                    }

                    List<Note> filteredNotes = notebook.getNotesInRangeWithKeywords(
                            start,
                            end,
                            keywords
                    );
                    filteredNotes.forEach(System.out::println);
                }
            }
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use the format dd.MM.yyyy HH:mm.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    /**
     * Parses a string to a LocalDateTime object using the predefined formatter.
     *
     * @param dateTimeStr The string to parse.
     * @return The LocalDateTime object.
     * @throws DateTimeParseException if the string cannot be parsed.
     */
    private static LocalDateTime parseDateTime(String dateTimeStr) throws DateTimeParseException {
        return LocalDateTime.parse(dateTimeStr, formatter);
    }
}
