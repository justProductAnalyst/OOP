package ru.nsu.vetrov;

/**
 * Represents a food item in the game, located at a specific position.
 */
public class Food {
    private Point position;

    /**
     * Constructs a new Food object at the specified position.
     *
     * @param position the position of the food on the game grid
     */
    public Food(Point position) {
        this.position = position;
    }

    /**
     * Returns the position of this food item.
     *
     * @return the position as a Point object
     */
    public Point getPosition() {
        return position;
    }
}
