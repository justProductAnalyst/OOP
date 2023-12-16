package ru.nsu.vetrov;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;

/**
 * This class provides the functionality to serialize and deserialize Notebook objects to and from JSON format.
 * It uses Jackson's ObjectMapper for handling JSON operations.
 */
public class NotebookSerializer {

    private static final String FILE_PATH = "notebook.json"; // The file path for storing the serialized data
    private final ObjectMapper objectMapper; // ObjectMapper instance for JSON processing

    /**
     * Constructor for NotebookSerializer.
     * Initializes a new ObjectMapper and registers the JavaTimeModule to support LocalDateTime serialization.
     */
    public NotebookSerializer() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Support for LocalDateTime
    }

    /**
     * Serializes the given Notebook object into a JSON file.
     *
     * @param notebook The Notebook object to be serialized.
     * @throws IOException If an I/O error occurs while writing the file.
     */
    public void serialize(Notebook notebook) throws IOException {
        objectMapper.writeValue(new File(FILE_PATH), notebook);
    }

    /**
     * Deserializes a Notebook object from a JSON file.
     * If the file does not exist, returns a new empty Notebook object.
     *
     * @return The deserialized Notebook object.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public Notebook deserialize() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new Notebook();
        }
        return objectMapper.readValue(file, Notebook.class);
    }
}
