package ru.battleship.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import ru.battleship.objects.Cell;
import ru.battleship.objects.Game;
import ru.battleship.objects.Opponent;
import ru.battleship.objects.Ship;
import ru.battleship.objects.Ships;
import ru.battleship.objects.State;

public class GameController {
    
    @FXML
    private GridPane pnGridPlayer;
    
    @FXML
    private GridPane pnGridAI;
    
    private final Game game = Game.getInstance();
    private final Opponent opponent = new Opponent();
    
    public void initializePlayerBoard(GridPane pnGridBox) {
        pnGridPlayer.getChildren().addAll(pnGridBox.getChildren());
    }
    
    public void initializeAIBoard(GridPane pnGridBox) {
        pnGridAI.getChildren().addAll(pnGridBox.getChildren());
    }
    
    public void pnGridAIMouseReleasedAction(MouseEvent e) {
        if (e.getButton() == MouseButton.SECONDARY || e.getTarget() instanceof GridPane) {
            return;
        }
        if (e.getTarget() instanceof Ship) {
            Ship ship = (Ship) e.getTarget();
            if (!ship.getCell().getIsHidden()) {
                return;
            }
            Ships ships = ship.getShips();
            ship.setOpacity(1);
            ship.setImage(new Image(getClass().getResourceAsStream("/ru/battleship/icons/wreck.png")));
            ship.getCell().setState(State.WRECK);
            ship.getCell().setIsHidden(false);
            ship.getParent().getStyleClass().set(1, "visible");
            ships.removeShip(ship);
            if (ships.isEmpty()) {
                game.getAIBoard().changeStates(ships.getWrecks());
            }
            return;
        }
        else if (e.getTarget() instanceof Cell) {
            Cell cell = (Cell) e.getTarget();
            if (!cell.getIsHidden()) {
                return;
            }
            if (cell.getChildren().isEmpty()) {
                cell.getChildren().add(new ImageView(
                        new Image(getClass().getResourceAsStream("/ru/battleship/icons/shot.png"))
                ));
                cell.setIsHidden(false);
                cell.getStyleClass().set(1, "visible");
            } 
            while(hit());
        } 
    }
    
    private boolean hit() {
        boolean isHit = false;
        Cell bestCell = opponent.getBestCell(game.getPlayerBoard());
        bestCell.setIsHidden(false);
        if (bestCell.getChildren().isEmpty()) {
            bestCell.getChildren().add(new ImageView(
                        new Image(getClass().getResourceAsStream("/ru/battleship/icons/shot.png"))
            ));
        }
        else if ((bestCell.getChildren().get(0)) instanceof Ship) {
            Ship ship = (Ship) bestCell.getChildren().get(0);
            Ships ships = ship.getShips();
            ship.setImage(new Image(getClass().getResourceAsStream("/ru/battleship/icons/wreck.png")));
            ship.getCell().setState(State.WRECK);
            ships.removeShip(ship);
            if (ships.isEmpty()) {
                ships.setDestroyed();
            }
            isHit = true;
        }
        return isHit;
    }
}
