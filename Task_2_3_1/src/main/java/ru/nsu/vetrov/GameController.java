package ru.nsu.vetrov;

import javafx.animation.AnimationTimer;

/**
 * Manages the game logic and timing for updates,
 * including handling keyboard input and updating the view.
 */
public class GameController {
    private GameModel model;
    private GameView view;
    private AnimationTimer timer;
    private long lastUpdate = 0;
    private long updateInterval = 80_000_000;

    /**
     * Constructs a GameController for managing game state.
     */
    public GameController() {
        model = new GameModel(25, 25);
    }

    /**
     * Sets the GameView associated with this controller.
     *
     * @param view the GameView that visualizes the game state
     */
    public void setView(GameView view) {
        this.view = view;
    }

    /**
     * Starts the game loop, updating game state periodically based on a fixed time interval.
     */
    public void startGame() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= updateInterval) {
                    onUpdate();
                    lastUpdate = now;
                }
            }
        };
        timer.start();
    }

    /**
     * Updates the game state, checks for game over or win conditions, and updates the view.
     */
    private void onUpdate() {
        model.update();
        if (model.isGameWon()) {
            timer.stop();
            view.displayWin();
            return;
        }
        if (model.isGameOver()) {
            timer.stop();
            view.displayGameOver();
            return;
        }
        view.draw();
    }

    /**
     * Returns the model containing the game's logic and state.
     *
     * @return the current game model
     */
    public GameModel getModel() {
        return model;
    }

    /**
     * Handles direction changes based on keyboard input.
     *
     * @param direction the new direction for the snake
     */
    public void onKeyPress(Direction direction) {
        model.changeDirection(direction);
    }
}
