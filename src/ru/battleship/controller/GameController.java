package ru.battleship.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import ru.battleship.objects.Game;

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
}
