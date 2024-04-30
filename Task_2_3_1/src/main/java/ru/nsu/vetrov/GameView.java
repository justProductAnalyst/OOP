package ru.nsu.vetrov;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameView {
    private GameController controller;
    private Pane pane;

    public GameView(GameController controller) {
        this.controller = controller;
        this.pane = new Pane();
        setupKeyBindings();
    }

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

    public void draw() {
        pane.requestFocus();
        pane.getChildren().clear();
        GameModel model = controller.getModel();
        for (Point p : model.getSnake().getPoints()) {
            Rectangle rect = new Rectangle(p.getX() * 20, p.getY() * 20, 20, 20);
            rect.setFill(Color.GREEN);
            pane.getChildren().add(rect);
        }
        for (Food food : model.getFoods()) {
            Rectangle rect = new Rectangle(food.getPosition().getX() * 20, food.getPosition().getY() * 20, 20, 20);
            rect.setFill(Color.RED);
            pane.getChildren().add(rect);
        }
    }

    public Pane asParent() {
        return pane;
    }

    public void displayGameOver() {
        // Display game over message
    }
}
