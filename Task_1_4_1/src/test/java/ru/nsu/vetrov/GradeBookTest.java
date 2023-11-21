package ru.nsu.vetrov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Provides unit tests for the GradeBook class, testing its various functionalities
 * including grade management, average grade calculation, and eligibility checks for honors
 * and increased scholarship.
 */
public class GradeBookTest {
    private GradeBook gradeBook;

    /**
     * Sets up the test environment before each test.
     * Initializes a new instance of GradeBook for testing.
     */
    @BeforeEach
    public void setUp() {
        gradeBook = new GradeBook("John Doe", 1);
    }

    /**
     * Tests the addition of grades and the calculation of the average grade.
     * Verifies that the average is calculated correctly based on the added grades.
     */
    @Test
    public void testAddAndCalculateAverageGrade() {
        gradeBook.addGrade(4, "Math", 1);
        gradeBook.addGrade(5, "Science", 1);
        gradeBook.addGrade(3, "History", 1);

        double avg = gradeBook.calculateAverageGrade();
        assertEquals(4.0, avg, 0.01);
    }

    /**
     * Tests the eligibility for honors.
     * Verifies that the student is eligible for honors when all conditions are met.
     */
    @Test
    public void testEligibilityForHonors() {
        gradeBook.setFinalQualifyingWorkGrade(5);
        gradeBook.addGrade(5, "Math", 1);
        gradeBook.addGrade(5, "Science", 1);
        gradeBook.addGrade(5, "History", 1);

        assertTrue(gradeBook.isEligibleForHonors());
    }

    /**
     * Tests the ineligibility for honors due to a satisfactory grade.
     * Verifies that the student is not eligible for honors if they have a satisfactory grade.
     */
    @Test
    public void testIneligibilityForHonorsDueToSatisfactoryGrade() {
        gradeBook.setFinalQualifyingWorkGrade(5);
        gradeBook.addGrade(3, "Math", 1);
        gradeBook.addGrade(5, "Science", 1);
        gradeBook.addGrade(5, "History", 1);

        assertFalse(gradeBook.isEligibleForHonors());
    }

    /**
     * Tests the eligibility for an increased scholarship based on excellent grades in the last semester.
     * Verifies that the student is eligible for an increased scholarship when all grades in the last semester are excellent.
     */
    @Test
    public void testEligibilityForIncreasedScholarship() {
        gradeBook.addGrade(5, "Math", 1);
        gradeBook.addGrade(5, "Science", 1);

        assertTrue(gradeBook.isEligibleForIncreasedScholarship());
    }

    /**
     * Tests the ineligibility for an increased scholarship due to a non-excellent grade.
     * Verifies that the student is not eligible for an increased scholarship if any grade in the last semester is not excellent.
     */
    @Test
    public void testIneligibilityForIncreasedScholarshipDueToNonExcellentGrade() {
        gradeBook.addGrade(4, "Math", 1);
        gradeBook.addGrade(5, "Science", 1);

        assertFalse(gradeBook.isEligibleForIncreasedScholarship());
    }

    /**
     * Tests the functionality of incrementing the current semester.
     * Verifies that grades added after incrementing the semester are associated with the new semester.
     */
    @Test
    public void testIncrementSemester() {
        gradeBook.incrementSemester();
        gradeBook.addGrade(5, "Math", 2);

        assertTrue(gradeBook.isEligibleForIncreasedScholarship());
    }
}
