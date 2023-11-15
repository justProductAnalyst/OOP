package ru.nsu.vetrov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Test class for SubstringSearcher.
 */
public class SubstringSearcherTest {

    private static final String TEST_FILE_NAME = "testInput.txt";
    private static Path testFilePath;

    /**
     * Sets up the test environment before all tests.
     *
     * @throws IOException if an I/O error occurs
     */
    @BeforeAll
    public static void setUp() throws IOException {
        testFilePath = Files.createTempFile(null, TEST_FILE_NAME);
        // Write to the file using UTF-8 encoding
        try (BufferedWriter writer = Files.newBufferedWriter(
                testFilePath,
                StandardCharsets.UTF_8)
        ) {
            writer.write("абракадабра");
        }
    }

    /**
     * Cleans up the test environment after all tests.
     *
     * @throws IOException if an I/O error occurs
     */
    @AfterAll
    public static void tearDown() throws IOException {
        Files.deleteIfExists(testFilePath);
    }

    /**
     * Tests the find method for a multi-line text file.
     */
    @Test
    public void testFindSubstringInMultiLineText() throws IOException {
        Path filePath = Paths.get("main/resources/multiLineText.txt");
        List<Integer> expectedIndices = Arrays.asList(10, 19, 42);
        List<Integer> actualIndices = SubstringSearcher.find(filePath.toString(), "test");
        assertEquals(
                expectedIndices,
                actualIndices,
                "The indices should match the expected values in multiLineText.txt."
        );
    }

    /**
     * Tests the find method for a text file with repeated patterns.
     */
    @Test
    public void testFindSubstringInRepeatedPatterns() throws IOException {
        Path filePath = Paths.get("main/resources/repeatedPatterns.txt");
        List<Integer> expectedIndices = Arrays.asList(0, 2, 4, 6);
        List<Integer> actualIndices = SubstringSearcher.find(filePath.toString(), "aba");
        assertEquals(
                expectedIndices,
                actualIndices,
                "The indices should match the expected values in repeatedPatterns.txt."
        );
    }


    @Test
    public void testFindSubstring() throws IOException {
        List<Integer> expectedIndices = Arrays.asList(1, 8);
        List<Integer> actualIndices = SubstringSearcher.find(
                testFilePath.toString(),
                "бра"
        );
        assertEquals(
                expectedIndices,
                actualIndices,
                "The indices should match the expected values."
        );
    }
}
