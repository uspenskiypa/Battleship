package ru.battleship.start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ru.battleship.controller.StartController;
import ru.battleship.controller.GameController;
import ru.battleship.objects.Game;

public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        //получение загрузчика и панели для стартового эпизода
        FXMLLoader startPaneLoader = new FXMLLoader(getClass().getResource("/ru/battleship/fxml/start.fxml"));
        Parent startPane = startPaneLoader.load();
        Scene startScene = new Scene(startPane, 1000, 690);
        startScene.getStylesheets().add("/ru/battleship/css/style.css");
        
        //получение загрузчика и панели для игрового эпизода 
        FXMLLoader gamePaneLoader = new FXMLLoader(getClass().getResource("/ru/battleship/fxml/game.fxml"));
        Parent gamePane = gamePaneLoader.load();
        Scene gameScene = new Scene(gamePane, 1280, 690);
        gameScene.getStylesheets().add("/ru/battleship/css/style.css");
        
        //установка эпизодов
        Game game = Game.getInstance();
        game.setGameController(gamePaneLoader.getController());
        game.setStartController(startPaneLoader.getController());
        game.setGameScene(gameScene);
        game.setStartScene(startScene);
        
        //настройка сцены и запуск отображения
        stage.setTitle("Морской бой");
        stage.setResizable(false);
        //stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/so-icon.png")));
        stage.setScene(startScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
