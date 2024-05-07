package ru.nsu.vetrov;

import java.util.List;

/**
 * Provides AI strategies for the game, helping automated agents (like snakes) make decisions based on game state.
 */
public class AIStrategy {

    /**
     * Determines the optimal direction for a snake
     * to move in order to get closer to the nearest food item.
     * The method calculates the Euclidean distance between the snake's head and each food item,
     * identifying the closest food. It then evaluates possible directions
     * to move towards this food item,
     * selecting the one that minimizes the distance to the food.
     *
     * @param snake the snake for which the strategy is being calculated
     * @param foods the list of food items present in the game
     * @return the best direction to move towards the closest food item
     */
    public static Direction closestFoodStrategy(Snake snake, List<Food> foods) {
        if (foods.isEmpty()) return snake.getDirection();

        Point head = snake.getPoints().get(0);
        Food closestFood = null;
        double minDistance = Double.MAX_VALUE;

        for (Food food : foods) {
            double distance = Math.sqrt(
                    Math.pow(food.getPosition().getX() - head.getX(), 2) +
                    Math.pow(food.getPosition().getY() - head.getY(), 2));
            if (distance < minDistance) {
                minDistance = distance;
                closestFood = food;
            }
        }

        if (closestFood == null) {
            return snake.getDirection();
        }

        Direction bestDirection = snake.getDirection();
        double closestDistance = minDistance;

        for (Direction possibleDirection : Direction.values()) {
            if (snake.canChangeTo(possibleDirection)) {
                Point nextPoint = snake.peekNextMoveInDirection(possibleDirection);
                double newDistance = Math.sqrt(
                        Math.pow(closestFood.getPosition().getX() - nextPoint.getX(), 2) +
                        Math.pow(closestFood.getPosition().getY() - nextPoint.getY(), 2));
                if (newDistance < closestDistance) {
                    closestDistance = newDistance;
                    bestDirection = possibleDirection;
                }
            }
        }

        return bestDirection;
    }
}
