package ru.nsu.vetrov;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SubstringSearcher {
    /**
     * Finds all occurrences of a substring within a text file and returns their starting indices.
     *
     * @param filename The name of the file to search within.
     * @param substring The substring to find.
     * @return A list of starting indices of each occurrence of the substring.
     */
    public static List<Integer> find(String filename, String substring) throws IOException {
        List<Integer> indices = new ArrayList<>();
        File file = new File(filename);
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            byte[] buffer = new byte[4096];
            boolean overlap = false;
            int overlapSize = substring.length() - 1;
            int bytesRead;
            int position = 0;

            while ((bytesRead = bis.read(buffer)) != -1) {
                String chunk = new String(buffer, 0, bytesRead, StandardCharsets.UTF_8);

                if (overlap) {
                    int overlapStart = Math.max(0, position - overlapSize);
                    String overlapChunk = new String(buffer, overlapStart, overlapSize, StandardCharsets.UTF_8);
                    chunk = overlapChunk + chunk;
                }

                int index = -1;
                while ((index = chunk.indexOf(substring, index + 1)) != -1) {
                    if (overlap && index < overlapSize) {
                        continue;
                    }
                    indices.add(position + index - (overlap ? overlapSize : 0));
                }

                position += bytesRead;
                overlap = true;
            }
        }
        return indices;
    }
}
