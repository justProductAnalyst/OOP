package ru.nsu.vetrov;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The class represents a simple note with a title, content, and a timestamp.
 * It implements Serializable, allowing objects of this type to be serialized.
 */
public class Note implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The title of the note.
     */
    private String title;

    /**
     * The content of the note.
     */
    private String content;

    /**
     * The timestamp indicating when the note was created.
     */
    private LocalDateTime timestamp;

    /**
     * Constructs a new Note with the specified title and content.
     * The timestamp is set to the current time.
     *
     * @param title   the title of the note
     * @param content the content of the note
     */
    public Note(String title, String content) {
        this.title = title;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Returns the title of this note.
     *
     * @return the title of the note
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the content of this note.
     *
     * @return the content of the note
     */
    public String getContent() {
        return content;
    }

    /**
     * Returns the timestamp of this note.
     *
     * @return the timestamp of the note
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Returns a string representation of this note, including its title, content, and timestamp.
     * The timestamp is formatted as "dd.MM.yyyy HH:mm".
     *
     * @return a string representation of the note
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return String.format("Title: %s\nContent: %s\nTimestamp: %s\n",
                title, content, timestamp.format(formatter));
    }
}
