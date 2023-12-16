package ru.nsu.vetrov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;

class NotebookTest {

    private Notebook notebook;

    @BeforeEach
    void setUp() {
        notebook = new Notebook();
    }

    @Test
    void testAddNote() {
        notebook.addNote("Test Note", "This is a test note.");
        List<Note> notes = notebook.getAllNotes();
        assertFalse(notes.isEmpty(),
                "Notebook should not be empty after adding a note.");
        assertEquals(1, notes.size(),
                "Notebook should have one note after adding a note.");
        Note note = notes.get(0);
        assertEquals("Test Note", note.getTitle(),
                "Note title should match the added note.");
        assertEquals("This is a test note.", note.getContent(),
                "Note content should match the added note.");
    }

    @Test
    void testRemoveNote() {
        notebook.addNote("Test Note", "This is a test note.");
        notebook.removeNote("Test Note");
        List<Note> notes = notebook.getAllNotes();
        assertTrue(notes.isEmpty(), "Notebook should be empty after removing the note.");
    }

    @Test
    void testGetNotesInRangeWithKeywords() {
        notebook.addNote("Test Note", "This is a test note.");
        LocalDateTime start = LocalDateTime.now().minusDays(1);
        LocalDateTime end = LocalDateTime.now().plusDays(1);
        List<String> keywords = List.of("Test");
        List<Note> notes = notebook.getNotesInRangeWithKeywords(start, end, keywords);
        assertFalse(notes.isEmpty(),
                "Should find notes with given keyword in the given date range.");
    }
}
