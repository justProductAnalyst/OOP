package ru.nsu.vetrov;

import javafx.animation.AnimationTimer;

public class GameController {
    private GameModel model;
    private GameView view;
    private AnimationTimer timer;
    private long lastUpdate = 0;
    private long updateInterval = 80_000_000;

    public GameController() {
        model = new GameModel(25, 25);
    }

    public void setView(GameView view) {
        this.view = view;
    }

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

    private void onUpdate() {
        model.update();
        if (model.isGameOver()) {
            timer.stop();
            view.displayGameOver();
        }
        view.draw();
    }

    public GameModel getModel() {
        return model;
    }

    public void onKeyPress(Direction direction) {
        model.changeDirection(direction);
    }
}
