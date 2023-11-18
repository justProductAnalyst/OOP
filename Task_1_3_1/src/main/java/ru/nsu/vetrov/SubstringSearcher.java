package ru.nsu.vetrov;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides a method to find all occurrences of a substring in a text file.
 */
public class SubstringSearcher {
    /**
     * Finds all occurrences of a substring within a text file and returns their starting indices.
     *
     * @param resourceName The name of the file to search within.
     * @param substring    The substring to find.
     * @return A list of starting indices of each occurrence of the substring.
     * @throws IOException If an I/O error occurs.
     */
    public static List<Integer> find(String resourceName, String substring) throws IOException {
        List<Integer> indices = new ArrayList<>();
        InputStream is = SubstringSearcher.class.getClassLoader().getResourceAsStream(resourceName);

        // If the resource is not found via class loader, try file input stream
        if (is == null) {
            try {
                is = new FileInputStream(resourceName);
            } catch (FileNotFoundException e) {
                throw new IOException("File not found: " + resourceName, e);
            }
        }

        try (BufferedInputStream bis = new BufferedInputStream(is)) {
            byte[] buffer = new byte[4096];
            int overlapSize = substring.length() - 1;
            int bytesRead;
            int position = 0;
            String previousEnd = "";

            while ((bytesRead = bis.read(buffer)) != -1) {
                String chunk = previousEnd + new String(
                        buffer,
                        0,
                        bytesRead,
                        StandardCharsets.UTF_8
                );

                int index = -1;
                while ((index = chunk.indexOf(substring, index + 1)) != -1) {
                    indices.add(position + index);
                }

                if (bytesRead >= overlapSize) {
                    previousEnd = new String(
                            buffer,
                            bytesRead - overlapSize,
                            overlapSize,
                            StandardCharsets.UTF_8
                    );
                } else {
                    previousEnd = new String(buffer, 0, bytesRead, StandardCharsets.UTF_8);
                }

                position += bytesRead - overlapSize > 0 ? bytesRead - overlapSize : 0;
            }
        }
        return indices;
    }
}
