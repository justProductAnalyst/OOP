package ru.nsu.vetrov;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Provides unit tests for the {@link GameModel} class to ensure the game's main logic
 * functions correctly, including game win conditions, food spawning, and direction changes.
 */
class GameModelTest {

    /**
     * Tests the condition for winning the game.
     * Ensures that the game is considered won when the snake grows to a certain size.
     */
    @Test
    void testGameWon() {
        GameModel model = new GameModel(25, 25);
        for (int i = 0; i < 16; i++) {
            model.getSnakes().get(0).grow();
        }
        assertTrue(model.isGameWon());
    }

    /**
     * Tests that food is not spawned on any part of the snake.
     * Ensures that the game logic for food spawning respects the snake's body position,
     * thereby not placing food on the snake.
     */
    @Test
    void testSpawnFoodNotOnSnake() {
        GameModel model = new GameModel(25, 25);
        Snake snake = model.getSnakes().get(0);
        snake.getPoints().clear();
        snake.getPoints().add(new Point(10, 10));

        Food food = new Food(new Point(10, 10));
        model.getFoods().add(food);
        model.update();

        boolean foodOnSnake = model.getFoods().stream().anyMatch(f ->
                snake.getPoints().contains(f.getPosition()));

        assertFalse(foodOnSnake, "Newly spawned food should not be on the snake");
    }

    /**
     * Tests the functionality of changing the direction of the snake within the game model.
     * Ensures that direction changes are updated correctly and affect the snake's movement.
     */
    @Test
    void testDirectionChange() {
        GameModel model = new GameModel(25, 25);
        model.changeDirection(Direction.LEFT);
        assertEquals(Direction.LEFT, model.getSnakes().get(0).getDirection(),
                "Snake direction should change correctly in model");
    }
}
