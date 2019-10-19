package ru.battleship.start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/ru/battleship/fxml/start.fxml"));
        Scene scene = new Scene(root, 1000, 690);
        scene.getStylesheets().add("/ru/battleship/css/style.css");
        
        stage.setTitle("Морской бой");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
