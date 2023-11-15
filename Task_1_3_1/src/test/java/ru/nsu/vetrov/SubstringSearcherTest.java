package ru.nsu.vetrov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class SubstringSearcherTest {

    private static final String TEST_FILE_NAME = "testInput.txt";
    private static Path testFilePath;

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
                "The indices should match the expected values.");
    }

//    @Test
//    public void testFindSubstringInLargeFile() throws IOException {
//        Path largeFilePath = generateLargeFile(
//                "largeTestFile.txt",
//                15 * 1024L * 1024L * 1024L
//        ); // 15 GB
//        List<Integer> expectedIndices = Arrays.asList(1, 8);
//
//        // Perform the test
//        List<Integer> actualIndices = SubstringSearcher.find(
//                largeFilePath.toString(),
//                "Repeated"
//        );
//        assertEquals(
//                expectedIndices,
//                actualIndices,
//                "Indices should match for large file."
//        );
//
//        // Clean up the large file
//        Files.deleteIfExists(largeFilePath);
//    }
//
//    private Path generateLargeFile(String fileName, long sizeInBytes) throws IOException {
//        Path filePath = Files.createTempFile(null, fileName);
//        try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8)) {
//            while (Files.size(filePath) < sizeInBytes) {
//                writer.write("someRepeatedContent");
//            }
//        }
//        return filePath;
//    }

    @AfterAll
    public static void tearDown() throws IOException {
        Files.deleteIfExists(testFilePath);
    }
}
