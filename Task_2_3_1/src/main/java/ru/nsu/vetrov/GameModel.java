package ru.nsu.vetrov;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameModel {
    private int width, height;
    private Snake snake;
    private List<Food> foods;
    private boolean gameOver;
    private Random random;

    public GameModel(int width, int height) {
        this.width = width;
        this.height = height;
        this.snake = new Snake(new Point(width / 2, height / 2), width, height);
        this.foods = new ArrayList<>();
        this.gameOver = false;
        this.random = new Random();
        spawnFood();
    }


    public void update() {
        if (!gameOver) {
            Point nextPoint = snake.peekNextMove();
            if (nextPoint.getX() < 0 || nextPoint.getX() >= width || nextPoint.getY() < 0 || nextPoint.getY() >= height || snake.intersects(nextPoint)) {
                gameOver = true;
                return;
            }
            Food consumedFood = null;
            for (Food food : foods) {
                if (food.getPosition().equals(nextPoint)) {
                    consumedFood = food;
                    break;
                }
            }
            if (consumedFood != null) {
                snake.grow();
                foods.remove(consumedFood);
                spawnFood();
            } else {
                snake.move();
            }
        }
    }

    private void spawnFood() {
        Point position;
        do {
            position = new Point(random.nextInt(width), random.nextInt(height));
        } while (snake.intersects(position));
        foods.add(new Food(position));
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Snake getSnake() {
        return snake;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void changeDirection(Direction direction) {
        snake.changeDirection(direction);
    }
}
