package ru.nsu.vetrov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Test class for SubstringSearcher.
 * It verifies the functionality of finding substrings within files loaded via getResourceAsStream.
 */
public class SubstringSearcherTest {
    /**
     * Tests the find method for a multi-line text file.
     *
     * @throws IOException if an I/O error occurs
     */
    @Test
    public void testFindSubstringInMultiLineText() throws IOException {
        List<Integer> expectedIndices = Arrays.asList(10, 45);
        List<Integer> actualIndices = SubstringSearcher.find(
                "multiLineTest.txt",
                "test"
        );
        System.out.println(actualIndices);
        assertEquals(
                expectedIndices,
                actualIndices,
                "The indices should match the expected values in multiLineText.txt."
        );
    }

    /**
     * Tests the find method for a text file with repeated patterns.
     *
     * @throws IOException if an I/O error occurs
     */
    @Test
    public void testFindSubstringInRepeatedPatterns() throws IOException {
        List<Integer> expectedIndices = Arrays.asList(0, 2, 4, 6);
        List<Integer> actualIndices = SubstringSearcher.find(
                "repeatedPatterns.txt",
                "aba"
        );
        assertEquals(
                expectedIndices,
                actualIndices,
                "The indices should match the expected values in repeatedPatterns.txt."
        );
    }

    /**
     * Tests the cyrillic text.
     *
     * @throws IOException if an I/O error occurs
     */
    @Test
    public void testFindSubstringWithCyrillicCharacters() throws IOException {
        // Assuming you have a file with Cyrillic text
        List<Integer> expectedIndices = Arrays.asList(27);
        List<Integer> actualIndices = SubstringSearcher.find(
                "cyrillicText.txt",
                "Нужно"
        );
        assertEquals(expectedIndices, actualIndices, "Indices should match for Cyrillic characters in cyrillicText.txt.");
    }

    /**
     * Tests the find method for a very large text file (15GB).
     * Note: This test is commented out due to its resource-intensive nature.
     *
     * @throws IOException if an I/O error occurs
     */
    @Test
    public void veryLargeFileTest() throws IOException {
        File tempFile = File.createTempFile("large_file", ".txt");
        tempFile.deleteOnExit();

        long fileSize = 1024L * 1024 * 1024 * 15;
        List<Integer> res;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("010");
            for (long ind = 3; ind < fileSize; ind++) {
                writer.write('0');
            }
        }

        try {
            res = SubstringSearcher.find(tempFile.getAbsolutePath(), "10");
        } catch (IOException e) {
            throw new RuntimeException("Failed to find substring in the file", e);
        }

        List<Integer> expected = Arrays.asList(1);
        assertEquals(expected, res, "The index should match the expected value in the large file.");
    }

}