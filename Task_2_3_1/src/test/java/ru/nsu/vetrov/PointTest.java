package ru.nsu.vetrov;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Provides unit tests for the {@link Point} class to ensure proper functionality
 * of point creation, equality checks, and hash code consistency.
 */
public class PointTest {

    /**
     * Tests the creation of a {@link Point} object.
     * Ensures the {@code x} and {@code y} coordinates are set correctly through the constructor.
     */
    @Test
    public void testPointCreation() {
        Point p = new Point(5, 10);
        assertEquals(5, p.getPointX());
        assertEquals(10, p.getPointY());
    }

    /**
     * Tests the equality method of the {@link Point} class.
     * Checks that two points with the same coordinates are considered equal, and two points
     * with different coordinates are considered not equal.
     */
    @Test
    public void testEquals() {
        Point p1 = new Point(5, 10);
        Point p2 = new Point(5, 10);
        Point p3 = new Point(10, 5);
        assertTrue(p1.equals(p2));
        assertFalse(p1.equals(p3));
    }

    /**
     * Tests the consistency of the {@link Point} class's {@code hashCode} method.
     * Ensures that two points with the same coordinates produce the same hash code.
     */
    @Test
    public void testHashCode() {
        Point p1 = new Point(5, 10);
        Point p2 = new Point(5, 10);
        assertEquals(p1.hashCode(), p2.hashCode());
    }
}
