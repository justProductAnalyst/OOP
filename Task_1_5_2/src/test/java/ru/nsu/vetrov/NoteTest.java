package ru.nsu.vetrov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

/**
 * Test class for {@link Note}.
 * This class contains tests to verify the correct functionality of the Note class,
 * specifically focusing on the getters for title, content, and timestamp.
 */
class NoteTest {

    /**
     * Tests the getters of the {@link Note} class.
     * This test verifies that the title and content returned by the getters match
     * what was set at the time of the Note object's creation. It also checks that
     * the timestamp of the note is set correctly to a recent time.
     */
    @Test
    void testGetters() {
        String title = "Test Note";
        String content = "This is a test note.";
        Note note = new Note(title, content);
        assertEquals(title, note.getTitle(), "getTitle should return the correct title.");
        assertEquals(content, note.getContent(), "getContent should return the correct content.");
        assertTrue(note.getTimestamp().isBefore(LocalDateTime.now().plusSeconds(1)),
                "getTimestamp should return the creation time of the note.");
    }
}
