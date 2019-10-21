package ru.battleship.controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ru.battleship.objects.Game;

public class StartController {
    
    @FXML
    private GridPane pnGridBox;
    
    @FXML
    private StackPane pnStack;
    
    @FXML
    private Button btStart;
    
    private final Game game = Game.getInstance();

    @FXML
    private void initialize() {
        game.fillGridPane(pnGridBox);
        game.fillStackPane(pnStack, 4);
    } 
    
    public void btStartButtonAction(ActionEvent e) throws IOException {
        game.getGameController().init(pnGridBox);
        Stage primaryStage = (Stage)((Node)e.getSource()).getScene().getWindow();
        primaryStage.setScene(game.getGameScene());
    }
    
    public void pnGridBoxMouseReleasedAction(MouseEvent e) {
        ImageView imageContainer = game.getImageContainer(); 
        try {
            if (imageContainer != null && e.getTarget() instanceof Pane) {
                Pane pane = (Pane) e.getTarget();
                pane.getChildren().add(imageContainer);
                game.setImageContainer(null);
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
