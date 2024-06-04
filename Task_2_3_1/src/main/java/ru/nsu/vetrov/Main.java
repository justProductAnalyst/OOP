package ru.nsu.vetrov;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class serves as the main entry point for the Snake game application.
 * It sets up the game environment, including the game view and controller, and
 * initializes the JavaFX application.
 */
public class Main extends Application {

    /**
     * Starts and displays the primary stage with all game components.
     * It initializes the GameController and GameView, sets up the scene, and displays the stage.
     *
     * @param primaryStage the primary window for this application
     */
    @Override
    public void start(Stage primaryStage) {
        GameController controller = new GameController();
        GameView view = new GameView(controller);
        controller.setView(view);

        Scene scene = new Scene(view.asParent(), 500, 500);
        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        view.asParent().requestFocus();
        view.draw();
        controller.startGame();
    }

    /**
     * The main method that launches the JavaFX application.
     *
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {
        launch(args);
    }
}
