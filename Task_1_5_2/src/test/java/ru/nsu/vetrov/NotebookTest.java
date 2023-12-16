package ru.nsu.vetrov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;

/**
 * The NotebookTest class contains unit tests for the Notebook class.
 * It tests the functionality of adding, removing, and retrieving notes
 * with specific conditions such as date range and keywords.
 */
class NotebookTest {

    private Notebook notebook;

    /**
     * Sets up the test environment before each test.
     * This method is called before the execution of each test method.
     */
    @BeforeEach
    void setUp() {
        notebook = new Notebook();
    }

    /**
     * Tests the addNote method of the Notebook class.
     * It adds a note to the notebook and checks if the note is added correctly.
     */
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

    /**
     * Tests the removeNote method of the Notebook class.
     * It adds a note and then removes it, checking if the notebook is empty afterward.
     */
    @Test
    void testRemoveNote() {
        notebook.addNote("Test Note", "This is a test note.");
        notebook.removeNote("Test Note");
        List<Note> notes = notebook.getAllNotes();
        assertTrue(notes.isEmpty(), "Notebook should be empty after removing the note.");
    }

    /**
     * Tests the getNotesInRangeWithKeywords method of the Notebook class.
     * It adds a note and retrieves it based on a date range and a set of keywords.
     */
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