package ru.nsu.vetrov;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Represents a snake in a game, which can be controlled manually or by AI.
 * Handles movements, direction changes, growth, and boundary interactions within a defined grid.
 */
public class Snake {
    private LinkedList<Point> points;
    private Direction direction;
    private int width, height;
    private boolean isAI;
    private Supplier<Direction> aiStrategy;

    /**
     * Constructs a new Snake.
     *
     * @param startPosition the starting position of the snake's head
     * @param width         the width of the grid the snake moves in
     * @param height        the height of the grid the snake moves in
     * @param isAI          whether the snake is controlled by an AI
     * @param aiStrategy    the AI strategy to use for movement decisions
     */
    public Snake(Point startPosition,
                int width,
                int height,
                boolean isAI,
                Supplier<Direction> aiStrategy) {
        points = new LinkedList<>();
        points.add(startPosition);
        this.direction = Direction.RIGHT;
        this.width = width;
        this.height = height;
        this.isAI = isAI;
        this.aiStrategy = aiStrategy;
    }

    /**
     * Moves the snake in its current direction, updating its position on the grid.
     */
    public void move() {
        if (isAI && aiStrategy != null) {
            setDirection(aiStrategy.get());
        }
        Point newHead = peekNextMove();
        points.addFirst(newHead);
        points.removeLast();
    }

    /**
     * Returns the current direction of the snake.
     *
     * @return the current movement direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Sets the snake's direction unless it's directly opposite to the current direction.
     *
     * @param newDirection the new direction to set
     */
    public void setDirection(Direction newDirection) {
        if ((this.direction == Direction.UP && newDirection != Direction.DOWN) ||
                (this.direction == Direction.DOWN && newDirection != Direction.UP) ||
                (this.direction == Direction.LEFT && newDirection != Direction.RIGHT) ||
                (this.direction == Direction.RIGHT && newDirection != Direction.LEFT)) {
            this.direction = newDirection;
        }
    }

    /**
     * Calculates the next position of the snake's head based on the current direction.
     *
     * @return the next head position as a Point
     */
    public Point peekNextMove() {
        Point head = points.getFirst();
        int newX = head.getX();
        int newY = head.getY();

        switch (direction) {
            case UP:
                newY = (newY - 1 + height) % height;
                break;
            case DOWN:
                newY = (newY + 1) % height;
                break;
            case LEFT:
                newX = (newX - 1 + width) % width;
                break;
            case RIGHT:
                newX = (newX + 1) % width;
                break;
        }

        return new Point(newX, newY);
    }

    /**
     * Extends the snake's length by adding a new head at the next position.
     */
    public void grow() {
        Point newHead = peekNextMove();
        points.addFirst(newHead);
    }

    /**
     * Gets reverse direction.
     */
    public Direction getReverseDirection() {
        switch (this.direction) {
            case UP: return Direction.DOWN;
            case DOWN: return Direction.UP;
            case LEFT: return Direction.RIGHT;
            case RIGHT: return Direction.LEFT;
            default: return this.direction;
        }
    }

    /**
     * Returns true if the snake can legally move in the given direction.
     *
     * @param newDirection the direction to check
     * @return true if the change is possible, false otherwise
     */
    public boolean canChangeTo(Direction newDirection) {
        return newDirection != getReverseDirection();
    }

    /**
     * Calculates the next head position in
     * a specific direction without changing the snake's actual direction.
     *
     * @param direction the direction to peek the next move
     * @return the next head position as a Point
     */
    public Point peekNextMoveInDirection(Direction direction) {
        Point head = points.getFirst();
        int newX = head.getX();
        int newY = head.getY();

        switch (direction) {
            case UP:
                newY -= 1;
                break;
            case DOWN:
                newY += 1;
                break;
            case LEFT:
                newX -= 1;
                break;
            case RIGHT:
                newX += 1;
                break;
        }

        newX = (newX + width) % width;
        newY = (newY + height) % height;

        return new Point(newX, newY);
    }

    /**
     * Checks if the snake intersects with a specified point.
     *
     * @param point the point to check for intersection
     * @return true if the point is part of the snake's body
     */
    public boolean intersects(Point point) {
        return points.contains(point);
    }

    /**
     * Sets the snake's direction directly, used for testing or overriding movement.
     *
     * @param direction the new direction to set
     */
    public void changeDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * Returns a list of points that make up the snake's body.
     *
     * @return a list of points representing the snake's body
     */
    public List<Point> getPoints() {
        return points;
    }
}
