package ru.nsu.vetrov;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Provides unit tests for the {@link Snake} class to ensure proper functionality
 * of snake movements, growth, direction changes, and boundary behaviors.
 */
class SnakeTest {

    /**
     * Tests the basic movement functionality of a {@link Snake}.
     * Ensures the snake moves correctly from its starting position.
     */
    @Test
    void testMove() {
        Point startPosition = new Point(10, 10);
        Snake snake = new Snake(startPosition, 20, 20, false, null);
        snake.move();
        assertEquals(new Point(11, 10), snake.getPoints().get(0));
    }

    /**
     * Tests the growth functionality of a {@link Snake}.
     * Ensures the snake grows in length correctly after the grow method is called.
     */
    @Test
    void testGrow() {
        Point startPosition = new Point(10, 10);
        Snake snake = new Snake(startPosition, 20, 20, false, null);
        snake.grow();
        assertEquals(2, snake.getPoints().size());
    }

    /**
     * Tests the ability of a {@link Snake} to change its direction.
     * Ensures the snake responds to direction change commands and moves accordingly.
     */
    @Test
    void testChangeDirection() {
        Point startPosition = new Point(10, 10);
        Snake snake = new Snake(startPosition, 20, 20, false, null);
        snake.setDirection(Direction.UP);
        snake.move();
        assertEquals(new Point(10, 9), snake.getPoints().get(0));
    }

    /**
     * Tests for self-intersection in a {@link Snake}.
     * Checks if the snake can intersect itself after a series of moves and growths.
     */
    @Test
    void testSelfIntersection() {
        Point startPosition = new Point(10, 10);
        Snake snake = new Snake(startPosition, 20, 20, false, null);
        snake.grow();
        snake.setDirection(Direction.DOWN);
        snake.move();
        snake.setDirection(Direction.LEFT);
        snake.move();
        snake.setDirection(Direction.UP);
        snake.move();
        assertTrue(snake.intersects(new Point(10, 10)));
    }

    /**
     * Tests if a {@link Snake} correctly ignores an attempt to reverse its direction.
     * This test ensures that the snake does not allow reversing into itself.
     */
    @Test
    void testReverseDirectionIgnore() {
        Point startPosition = new Point(10, 10);
        Snake snake = new Snake(startPosition, 20, 20, false, null);
        snake.setDirection(Direction.UP);
        snake.setDirection(Direction.DOWN);
        assertEquals(Direction.UP, snake.getDirection(),
                "Snake should ignore reverse direction");
    }

    /**
     * Tests the boundary wrap-around behavior of a {@link Snake}.
     * Ensures the snake appears at the opposite boundary when it moves beyond the grid limits.
     */
    @Test
    void testBoundaryWrapAround() {
        Point startPosition = new Point(20, 0);
        Snake snake = new Snake(startPosition, 20, 20, false, null);
        snake.move();
        assertEquals(new Point(1, 0), snake.getPoints().get(0),
                "Snake should wrap around to the opposite boundary");
    }

    /**
     * Tests the AI-controlled movement of a {@link Snake}.
     * Ensures that the snake moves correctly based on the AI's decision.
     */
    @Test
    void testAiControlledMove() {
        Point startPosition = new Point(5, 5);
        Snake snake = new Snake(startPosition,
                20, 20, true, () -> Direction.DOWN);
        snake.move();
        assertEquals(new Point(5, 6), snake.getPoints().get(0),
                "AI should move the snake correctly");
    }

    /**
     * Tests intersection detection between two {@link Snake} instances.
     * Checks if one snake correctly detects when it intersects with another snake.
     */
    @Test
    void testIntersectionWithOtherSnake() {
        Snake snake1 = new Snake(new Point(5, 5),
                20, 20, false, null);
        Snake snake2 = new Snake(new Point(5, 6),
                20, 20, false, null);
        snake2.setDirection(Direction.UP);
        snake2.move();
        assertTrue(snake1.intersects(snake2.getPoints().get(0)),
                "Snake1 should intersect with Snake2");
    }
}
