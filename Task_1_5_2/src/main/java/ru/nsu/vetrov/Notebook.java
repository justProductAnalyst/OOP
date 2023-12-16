package ru.nsu.vetrov;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Notebook {
    private List<Note> notes;

    public Notebook() {
        notes = new ArrayList<>();
    }

    public void addNote(String title, String content) {
        notes.add(new Note(title, content));
    }

    public void removeNote(String title) {
        notes.removeIf(note -> note.getTitle().equalsIgnoreCase(title));
    }

    public List<Note> getAllNotes() {
        return notes.stream()
                .sorted(Comparator.comparing(Note::getTimestamp))
                .collect(Collectors.toList());
    }

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
