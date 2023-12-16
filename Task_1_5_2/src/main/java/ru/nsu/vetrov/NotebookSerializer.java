package ru.nsu.vetrov;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;

public class NotebookSerializer {

    private static final String FILE_PATH = "notebook.json";
    private final ObjectMapper objectMapper;

    public NotebookSerializer() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Support for LocalDateTime
    }

    public void serialize(Notebook notebook) throws IOException {
        objectMapper.writeValue(new File(FILE_PATH), notebook);
    }

    public Notebook deserialize() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new Notebook();
        }
        return objectMapper.readValue(file, Notebook.class);
    }
}
