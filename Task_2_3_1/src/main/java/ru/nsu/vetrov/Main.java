package ru.nsu.vetrov;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
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

    public static void main(String[] args) {
        launch(args);
    }
}
