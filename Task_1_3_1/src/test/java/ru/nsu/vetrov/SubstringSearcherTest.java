package ru.nsu.vetrov;

import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for SubstringSearcher.
 * It verifies the functionality of finding substrings within files loaded via getResourceAsStream.
 */
public class SubstringSearcherTest {

    private static final String TEST_RESOURCE_NAME = "testInput.txt";
    private static Path testFilePath;

    /**
     * Tests the find method for a multi-line text file.
     *
     * @throws IOException if an I/O error occurs
     */
    @Test
    public void testFindSubstringInMultiLineText() throws IOException {
        List<Integer> expectedIndices = Arrays.asList(10, 47);
        List<Integer> actualIndices = SubstringSearcher.find(
                "multiLineTest.txt",
                "test"
        );
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
     * Tests the find method for a very large text file (15GB).
     * Note: This test is commented out due to its resource-intensive nature.
     *
     * @throws IOException if an I/O error occurs
     */
    @Test
    public void veryLargeFileTest() {
        String fileName = "src/test/resources/large_file.txt";
        long fileSize = 1024L * 1024 * 1024 * 8; // 8GB
        List<Integer> res;

        File file = new File(fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("010");
            for (long ind = 3; ind < fileSize; ind++) {
                writer.write('0');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            res = SubstringSearcher.find("large_file.txt", "10");
        } catch (IOException e) {
            throw new RuntimeException("Failed to find substring in the file", e);
        } finally {
            file.delete();
        }

        List<Integer> expected = Arrays.asList(1);

        assertEquals(
                expected,
                res,
                "The index should match the expected value in the large file."
        );
    }
}