package ru.nsu.vetrov;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Note implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    private String content;
    private LocalDateTime timestamp;

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return String.format("Title: %s\nContent: %s\nTimestamp: %s\n",
                title, content, timestamp.format(formatter));
    }
}
