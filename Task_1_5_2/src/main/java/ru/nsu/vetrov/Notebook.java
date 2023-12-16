package ru.nsu.vetrov;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The Notebook class represents a collection of notes.
 * It allows adding, removing, and retrieving notes based on different criteria.
 */
public class Notebook {
    private List<Note> notes;

    /**
     * Constructs a new Notebook instance with an empty list of notes.
     */
    public Notebook() {
        notes = new ArrayList<>();
    }

    /**
     * Adds a new note to the notebook.
     *
     * @param title   The title of the note.
     * @param content The content of the note.
     */
    public void addNote(String title, String content) {
        notes.add(new Note(title, content));
    }

    /**
     * Removes a note from the notebook based on its title.
     * The removal is case-insensitive.
     *
     * @param title The title of the note to be removed.
     */
    public void removeNote(String title) {
        notes.removeIf(note -> note.getTitle().equalsIgnoreCase(title));
    }

    /**
     * Retrieves all notes in the notebook, sorted by their timestamp.
     *
     * @return A list of all notes sorted by timestamp in ascending order.
     */
    public List<Note> getAllNotes() {
        return notes.stream()
                .sorted(Comparator.comparing(Note::getTimestamp))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves notes within a specified date range that contain any of the given keywords.
     * The search for keywords in note titles is case-insensitive.
     *
     * @param start    The start of the date range.
     * @param end      The end of the date range.
     * @param keywords A list of keywords to look for in the note titles.
     * @return A list of notes within the specified date range that
     * contain any of the keywords, sorted by timestamp.
     */
    public List<Note> getNotesInRangeWithKeywords(
            LocalDateTime start,
            LocalDateTime end,
            List<String> keywords
    ) {
        return notes.stream()
                .filter(note ->
                        note.getTimestamp().isAfter(start) && note.getTimestamp().isBefore(end))
                .filter(note ->
                        keywords.stream().anyMatch(keyword ->
                                note.getTitle().toLowerCase().contains(keyword.toLowerCase())
                        )
                )
                .sorted(Comparator.comparing(Note::getTimestamp))
                .collect(Collectors.toList());
    }
}
