package ru.nsu.vetrov;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Manages the core game logic, state, and interactions of the Snake game,
 * including snake movements, food generation, and game termination conditions.
 */
public class GameModel {
    private int width, height;
    private List<Snake> snakes;
    private List<Food> foods;
    private boolean gameOver;
    private Random random;

    /**
     * Initializes the game model with specified grid dimensions.
     *
     * @param width  the width of the game grid
     * @param height the height of the game grid
     */
    public GameModel(int width, int height) {
        this.width = width;
        this.height = height;
        this.snakes = new ArrayList<>();
        this.foods = new ArrayList<>();
        this.gameOver = false;
        this.random = new Random();

        // Add a human-controlled snake and an AI-controlled snake
        snakes.add(new Snake(new Point(width / 2, height / 2),
                width, height, false, null));
        snakes.add(new Snake(new Point(width / 3, height / 3),
                width, height, true,
                () -> AiStrategy.closestFoodStrategy(snakes.get(snakes.size() - 1), this.foods)));
        spawnFood();
    }

    /**
     * Updates the state of the game,
     * moving snakes and handling food consumption or game over conditions.
     */
    public void update() {
        if (gameOver) {return;}

        for (Snake snake : snakes) {
            Point nextPoint = snake.peekNextMove();
            if (nextPoint.getPointX() < 0 || nextPoint.getPointX() >= width || nextPoint.getPointY() < 0
                    || nextPoint.getPointY() >= height || intersectsOtherSnake(snake, nextPoint)) {
                gameOver = true;
                return;
            }

            Food consumedFood = checkFoodConsumption(nextPoint);
            if (consumedFood != null) {
                snake.grow();
                foods.remove(consumedFood);
                spawnFood();
            } else {
                snake.move();
            }
        }
    }

    /**
     * Checks if a given position is occupied by any food.
     *
     * @param position the position to check
     * @return the food at the given position, null if none is found
     */
    private Food checkFoodConsumption(Point position) {
        for (Food food : foods) {
            if (food.getPosition().equals(position)) {
                return food;
            }
        }
        return null;
    }

    /**
     * Checks if a point is occupied by any snake other than the current snake.
     *
     * @param currentSnake the snake to check against
     * @param point        the point to check
     * @return true if another snake occupies the point, false otherwise
     */
    private boolean intersectsOtherSnake(Snake currentSnake, Point point) {
        for (Snake snake : snakes) {
            if (snake != currentSnake && snake.intersects(point)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Randomly places food on the game grid, ensuring it does not overlap with snakes.
     */
    private void spawnFood() {
        Point position;
        boolean isOccupied;
        do {
            position = new Point(random.nextInt(width), random.nextInt(height));
            isOccupied = false;
            for (Snake snake : snakes) {
                if (snake.intersects(position)) {
                    isOccupied = true;
                    break;
                }
            }
        } while (isOccupied);
        foods.add(new Food(position));
    }

    /**
     * Returns whether the game is over.
     *
     * @return true if the game is over, false otherwise
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Returns the list of snakes in the game.
     *
     * @return a list of snakes
     */
    public List<Snake> getSnakes() {
        return snakes;
    }

    /**
     * Returns the list of foods on the game grid.
     *
     * @return a list of foods
     */
    public List<Food> getFoods() {
        return foods;
    }

    /**
     * Checks if the game has been won based on the length of the first snake.
     *
     * @return true if the game is won, false otherwise
     */
    public boolean isGameWon() {
        return snakes.get(0).getPoints().size() > 15;
    }

    /**
     * Directs the first snake to change its direction.
     *
     * @param direction the new direction for the snake
     */
    public void changeDirection(Direction direction) {
        if (!snakes.isEmpty()) {
            snakes.get(0).changeDirection(direction);
        }
    }

}
