package ru.battleship.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
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
import ru.battleship.objects.Board;
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
    
    private final static Game game = Game.getInstance();
    private final static Random rn = new Random();
    private Ships shipsContainer;

    @FXML
    private void initialize() {
        game.fillGridPane(pnGridBox);
        StackPane[] arrPane = {pnStack1, pnStack2, pnStack3, pnStack4};
        game.fillShips(arrPane);
    } 
    
    //Обработчик события нажатия на кнопку "Новая игра"
    public void btStartButtonAction(ActionEvent e) throws IOException {
        game.getGameController().initializePlayerBoard(pnGridBox);
        GridPane pnAIGridBox = new GridPane();
        game.fillGridPane(pnAIGridBox);
        game.fillShipsAIList();
        LinkedList<Ships> listShips = new LinkedList<>(game.getShipsAIList());
        Board board = game.getAIBoard();
        smartShipPlacing(listShips, board, pnAIGridBox);
        game.getGameController().initializeAIBoard(pnAIGridBox);
        Stage primaryStage = (Stage)((Node)e.getSource()).getScene().getWindow();
        primaryStage.setScene(game.getGameScene());
    }
    
    //Обработчик события нажатия на игровое поле pnGridBox
    public void pnGridBoxMouseReleasedAction(MouseEvent e) {
        if (shipsContainer != null) {
            if (e.getButton() == MouseButton.SECONDARY) {
                shipsContainer.changeCourse();
                return;
            }
            if (e.getTarget() instanceof Ship) {
                shipsContainer.setOpasity(1);
                shipsContainer = ((Ship) e.getTarget()).getShips();
                shipsContainer.setOpasity(0.3);
            } 
            else if (e.getTarget() instanceof Pane) {
                int[] coords = getCoordinates((Pane) e.getTarget());
                if (game.getPlayerBoard().isCurrectCell(coords, shipsContainer)) {
                    positionShips(shipsContainer, coords, pnGridBox, game.getPlayerBoard());
                } 
                else {
                    shipsContainer.setCourse(shipsContainer.getCourse() + 1);
                    if (game.getPlayerBoard().isCurrectCell(coords, shipsContainer)) {
                        positionShips(shipsContainer, coords, pnGridBox, game.getPlayerBoard());
                    }
                    shipsContainer.setCourse(shipsContainer.getCourse() - 1);
                }
                shipsContainer.setOpasity(1);
                shipsContainer = null;
            }
        } 
        else {
            if (e.getTarget() instanceof Ship) {
                shipsContainer = ((Ship) e.getTarget()).getShips();
                shipsContainer.setOpasity(0.3);
            }
        }
    }
    
    //Обработчик события нажатия на кнопку "Случайная расстановка"
    public void brRandomPlacingButtonAction(ActionEvent e) {
        try {
            LinkedList<Ships> listShips = new LinkedList<>(game.getShipsList());
            Board board = game.getPlayerBoard();
            randomShipPlacing(listShips, board, pnGridBox);
        } 
        catch (Exception ex) {
        }
    }
    
    //Расставляет корабли из списка listShips на поле pnGrid
    //Размещает 4-палубник и 3-палубники по краям поля
    //остальные корабли помещаются в случайные места
    private void smartShipPlacing(LinkedList<Ships> listShips, Board board, GridPane pnGrid) {
        LinkedList<int[]> listCells = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            listCells.add(new int[]{i, 0});
            listCells.add(new int[]{i, 9});
            listCells.add(new int[]{0, i});
            listCells.add(new int[]{9, i});
        }
        Collections.shuffle(listCells);
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                listCells.add(new int[]{i, j});
            }
        } 
        while (!listShips.isEmpty()) {
            Ships ships = listShips.peekLast();
            int[] coords = listCells.poll();
            if (listShips.size() >= 7) {
                if (listShips.size() == 7) {
                    Collections.shuffle(listCells);
                }
                if (coords[0] == 0 || coords[0] == 9) {
                    ships.setCourse(0);
                }
                else {
                    ships.setCourse(2);
                }
            }
            else {
                ships.randomCourse();
            }
            if (board.isCurrectCell(coords, ships)) {
                positionShips(ships, coords, pnGrid, board);
                listShips.remove(ships);
            } 
            else {
                ships.setCourse(ships.getCourse() + 1);
                if (board.isCurrectCell(coords, ships)) {
                    positionShips(ships, coords, pnGrid, board);
                    listShips.remove(ships);
                }
                ships.setCourse(ships.getCourse() - 1);
            }
        }
    }
    
    private void randomShipPlacing(LinkedList<Ships> listShips, Board board, GridPane pnGrid) throws NullPointerException {
        LinkedList<int[]> listCells = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                listCells.add(new int[]{i, j});
            }
        }
        Collections.shuffle(listShips);
        Collections.shuffle(listCells);
        while (!listShips.isEmpty()) {
            int[] coords = listCells.poll();
            Ships ships = listShips.peek();
            ships.randomCourse();
            if (board.isCurrectCell(coords, ships)) {
                positionShips(ships, coords, pnGrid, board);
                listShips.remove(ships);
            } 
            else {
                ships.setCourse(ships.getCourse() + 1);
                if (board.isCurrectCell(coords, ships)) {
                    positionShips(ships, coords, pnGrid, board);
                    listShips.remove(ships);
                }
                ships.setCourse(ships.getCourse() - 1);
            }
        }
    }
    
    //Располагает корабль на игровом поле
    private void positionShips(Ships ships, int[] coords, GridPane pnGrid, Board board) {
        for (int i = 0; i < ships.size(); i++) {
            Pane target = null;
            switch (ships.getCourse()) {
                case 0: target = (Pane) getNodeFromGridPane(coords[0], coords[1] - i, pnGrid); break;
                case 1: target = (Pane) getNodeFromGridPane(coords[0], coords[1] + i, pnGrid); break;
                case 2: target = (Pane) getNodeFromGridPane(coords[0] + i, coords[1], pnGrid); break;
                case 3: target = (Pane) getNodeFromGridPane(coords[0] - i, coords[1], pnGrid); break; 
            }
            target.getChildren().clear();
            target.getChildren().add(ships.get(i));
        }
        board.changeStates(getGridBox(pnGrid));
    }

    //Рассчитывает и возвразает координаты target в GridPane
    private int[] getCoordinates(Node target) {
        int row = GridPane.getRowIndex(target);
        int col = GridPane.getColumnIndex(target);
        return new int[] {row, col};
    }
    
    //Возвращает ситуацию на игровом поле
    private int[][] getGridBox(GridPane pnGrid) {
        int[][] arrGridBox = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Pane pane = (Pane) getNodeFromGridPane(i, j, pnGrid);
                if (!pane.getChildren().isEmpty()) {
                    arrGridBox[i][j] = 1;
                }
            }
        }
        return arrGridBox;
    }
    
    
    //Возвращает объект панели по номеру строки и столбца
    private Node getNodeFromGridPane(int row, int col, GridPane pnGrid) {
        for (Node node : pnGrid.getChildren()) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
                return node;
            }
        }
        return null;
    }
}
