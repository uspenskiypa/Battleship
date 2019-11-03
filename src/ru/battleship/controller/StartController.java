package ru.battleship.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ru.battleship.objects.Game;
import ru.battleship.objects.Ship;
import ru.battleship.objects.Ships;

public class StartController {
    
    @FXML
    private GridPane pnGridBox;
    
    @FXML
    private StackPane pnStack4;
    
    @FXML
    private StackPane pnStack3;
        
    @FXML
    private StackPane pnStack2;
            
    @FXML
    private StackPane pnStack1;
    
    @FXML
    private Button btStart;
    
    private final Game game = Game.getInstance();

    @FXML
    private void initialize() {
        game.fillGridPane(pnGridBox);
        StackPane[] arrPane = {pnStack1, pnStack2, pnStack3, pnStack4};
        game.fillShips(arrPane);
    } 
    
    public void btStartButtonAction(ActionEvent e) throws IOException {
        game.getGameController().init(pnGridBox);
        
        Stage primaryStage = (Stage)((Node)e.getSource()).getScene().getWindow();
        primaryStage.setScene(game.getGameScene());
    }
    
    public void pnGridBoxMouseReleasedAction(MouseEvent e) {
        Ship shipContainer = game.getShipContainer();
        if (e.getButton() == MouseButton.SECONDARY && shipContainer != null) {
            shipContainer.getShips().changeCourse();
            return;
        }
        try {
            if (shipContainer != null && e.getTarget() instanceof Pane) {
                Ships ships = shipContainer.getShips();
                int[] coords = getCoordinates((Pane) e.getTarget());
                if (game.getBoard().isCurrectCell(coords, ships)) {
                    positionShips(ships, coords);
                }
                else {
                    ships.setCourse(ships.getCourse() + 1);
                    if (game.getBoard().isCurrectCell(coords, ships)) {
                        positionShips(ships, coords);
                    }
                    ships.setCourse(ships.getCourse() - 1);
                }
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
    //Располагает корабль на игровом поле
    private void positionShips(Ships ships, int[] coords) {
        for (int i = 0; i < ships.size(); i++) {
            Pane target = null;
            switch (ships.getCourse()) {
                case 0: target = (Pane) getNodeFromGridPane(coords[0], coords[1] - i); break;
                case 1: target = (Pane) getNodeFromGridPane(coords[0], coords[1] + i); break;
                case 2: target = (Pane) getNodeFromGridPane(coords[0] + i, coords[1]); break;
                case 3: target = (Pane) getNodeFromGridPane(coords[0] - i, coords[1]); break; 
            }
            target.getChildren().clear();
            target.getChildren().add(ships.get(i));
        }
        game.getBoard().changeStates(getGridBox());
        game.setShipContainer(null);
        ships.setOpasity(1);
    }

    //Рассчитывает и возвразает координаты target в GridPane
    private int[] getCoordinates(Node target) {
        int row = GridPane.getRowIndex(target);
        int col = GridPane.getColumnIndex(target);
        return new int[] {row, col};
    }
    
    //Возвращает ситуацию на игровом поле
    private int[][] getGridBox() {
        int[][] arrGridBox = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Pane pane = (Pane) getNodeFromGridPane(i, j);
                if (pane.getChildren().isEmpty()) {
                    arrGridBox[i][j] = 0;
                }
                else {
                    arrGridBox[i][j] = 1;
                }
            }
        }
        return arrGridBox;
    }
    
    
    //Возвращает объект панели по номеру строки и столбца
    private Node getNodeFromGridPane(int row, int col) {
        for (Node node : pnGridBox.getChildren()) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
                return node;
            }
        }
        return null;
    }
}
