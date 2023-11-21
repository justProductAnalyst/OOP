package ru.nsu.vetrov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Test class for the GradeBook class.
 */
public class GradeBookTest {
    private GradeBook gradeBook;

    @BeforeEach
    public void setUp() {
        gradeBook = new GradeBook("John Doe", 1);
    }

    @Test
    public void testAddAndCalculateAverageGrade() {
        gradeBook.addGrade(4, "Math", 1);
        gradeBook.addGrade(5, "Science", 1);
        gradeBook.addGrade(3, "History", 1);

        double avg = gradeBook.calculateAverageGrade();
        assertEquals(4.0, avg, 0.01);
    }

    @Test
    public void testEligibilityForHonors() {
        // Assuming final qualifying work grade needs to be set for eligibility
        gradeBook.setFinalQualifyingWorkGrade(5);

        gradeBook.addGrade(5, "Math", 1);
        gradeBook.addGrade(5, "Science", 1);
        gradeBook.addGrade(5, "History", 1);

        assertTrue(gradeBook.isEligibleForHonors());
    }

    @Test
    public void testIneligibilityForHonorsDueToSatisfactoryGrade() {
        gradeBook.setFinalQualifyingWorkGrade(5);

        gradeBook.addGrade(3, "Math", 1); // Satisfactory grade
        gradeBook.addGrade(5, "Science", 1);
        gradeBook.addGrade(5, "History", 1);

        assertFalse(gradeBook.isEligibleForHonors());
    }

    @Test
    public void testEligibilityForIncreasedScholarship() {
        gradeBook.addGrade(5, "Math", 1);
        gradeBook.addGrade(5, "Science", 1);

        assertTrue(gradeBook.isEligibleForIncreasedScholarship());
    }

    @Test
    public void testIneligibilityForIncreasedScholarshipDueToNonExcellentGrade() {
        gradeBook.addGrade(4, "Math", 1); // Not an excellent grade
        gradeBook.addGrade(5, "Science", 1);

        assertFalse(gradeBook.isEligibleForIncreasedScholarship());
    }

    @Test
    public void testIncrementSemester() {
        gradeBook.incrementSemester();
        gradeBook.addGrade(5, "Math", 2);

        // The added grade should be in the second semester
        assertTrue(gradeBook.isEligibleForIncreasedScholarship());
    }
}