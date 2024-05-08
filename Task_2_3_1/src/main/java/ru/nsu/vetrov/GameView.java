package ru.nsu.vetrov;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Represents the visual aspect of the game, managing all
 * UI components such as the game area and the display of game states.
 */
public class GameView {
    private GameController controller;
    private Pane pane;

    /**
     * Constructs a GameView linked to a GameController to handle game interactions.
     *
     * @param controller the game controller that handles logic and state
     */
    @ExcludeFromJacocoGeneratedReport
    public GameView(GameController controller) {
        this.controller = controller;
        this.pane = new Pane();
        setupKeyBindings();
    }

    /**
     * Sets up key bindings for controlling the game via keyboard.
     */
    @ExcludeFromJacocoGeneratedReport
    private void setupKeyBindings() {
        pane.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    controller.onKeyPress(Direction.UP);
                    break;
                case DOWN:
                    controller.onKeyPress(Direction.DOWN);
                    break;
                case LEFT:
                    controller.onKeyPress(Direction.LEFT);
                    break;
                case RIGHT:
                    controller.onKeyPress(Direction.RIGHT);
                    break;
                default:
                    break;
            }
            event.consume();
        });
        pane.setFocusTraversable(true);
    }

    /**
     * Draws the game state to the pane, including all snakes and food items.
     */
    @ExcludeFromJacocoGeneratedReport
    public void draw() {
        pane.requestFocus();
        pane.getChildren().clear();
        GameModel model = controller.getModel();

        int snakeIndex = 0;
        for (Snake snake : model.getSnakes()) {
            Color snakeColor = (snakeIndex == 0) ? Color.GREEN : Color.BLACK;
            for (Point p : snake.getPoints()) {
                Rectangle rect = new Rectangle(p.getPointX() * 20,
                        p.getPointY() * 20, 20, 20);
                rect.setFill(snakeColor);
                pane.getChildren().add(rect);
            }
            snakeIndex++;
        }

        for (Food food : model.getFoods()) {
            Rectangle rect = new Rectangle(food.getPosition().getPointX() * 20,
                    food.getPosition().getPointY() * 20, 20, 20);
            rect.setFill(Color.RED);
            pane.getChildren().add(rect);
        }
    }

    /**
     * Returns the primary Pane used in this view.
     *
     * @return the Pane as the parent container
     */
    @ExcludeFromJacocoGeneratedReport
    public Pane asParent() {
        return pane;
    }

    /**
     * Displays the win message.
     */
    @ExcludeFromJacocoGeneratedReport
    public void displayWin() {
        pane.getChildren().clear();
        Text winText = new Text("You win!");
        winText.setFont(Font.font("Verdana", 50));
        winText.setFill(Color.GOLD);
        winText.setX(150);
        winText.setY(250);
        pane.getChildren().add(winText);
    }

    /**
     * Displays the game over message.
     */
    @ExcludeFromJacocoGeneratedReport
    public void displayGameOver() {
        pane.getChildren().clear();
        Text gameOverText = new Text("Game Over!");
        gameOverText.setFont(Font.font("Verdana", 50));
        gameOverText.setFill(Color.RED);
        gameOverText.setX(100);
        gameOverText.setY(250);
        pane.getChildren().add(gameOverText);
    }
}
