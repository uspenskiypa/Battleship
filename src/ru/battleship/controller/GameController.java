package ru.battleship.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import ru.battleship.objects.Game;
import ru.battleship.objects.Ship;

public class GameController {
    
    @FXML
    private GridPane pnGridPlayer;
    
    @FXML
    private GridPane pnGridAI;
    
    private final Game game = Game.getInstance();
    
    @FXML
    private void initialize() {
        //game.fillGridPane(pnGridPlayer);
        //game.fillGridPane(pnGridAI);  
    }
    
    public void initializePlayerBoard(GridPane pnGridBox) {
        pnGridPlayer.getChildren().addAll(pnGridBox.getChildren());
    }
    
    public void initializeAIBoard(GridPane pnGridBox) {
        pnGridAI.getChildren().addAll(pnGridBox.getChildren());
    }
    
    public void pnGridAIMouseReleasedAction(MouseEvent e) {
        if (e.getButton() == MouseButton.SECONDARY) {
            return;
        }
        if (e.getTarget() instanceof Ship) {
            Ship ship = (Ship) e.getTarget();
            ship.setOpacity(1);
            ship.setImage(new Image(getClass().getResourceAsStream("/ru/battleship/icons/wreck.png")));
        }
        else if (e.getTarget() instanceof Pane) {
            Pane pane = (Pane) e.getTarget();
            if (pane.getChildren().isEmpty()) {
                pane.getChildren().add(new ImageView(
                        new Image(getClass().getResourceAsStream("/ru/battleship/icons/shot.png"))
                ));
            }
        }
    }
}
