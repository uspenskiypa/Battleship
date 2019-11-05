package ru.battleship.objects;

import java.util.LinkedList;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import ru.battleship.controller.GameController;
import ru.battleship.controller.StartController;

public class Game {
    
    public final static int GRID_WIDTH = 10, GRID_HEIGHT = 10;
    private Scene gameScene;
    private Scene startScene;
    private StartController startController;
    private GameController gameController;
    private static Game instance;
    private LinkedList<Ships> shipsList;
    private LinkedList<Ships> shipsAIList;
    private PlayerBoard playerBoard;
    private AIBoard aiBoard;
    
    
    private Game() {
        playerBoard = new PlayerBoard(GRID_WIDTH, GRID_HEIGHT);
        aiBoard = new AIBoard(GRID_WIDTH, GRID_HEIGHT);
        shipsList = new LinkedList<>();
        shipsAIList = new LinkedList<>();
    }
    
    public void fillShipsAIList() {
        shipsAIList.add(new Ships(1));
        shipsAIList.add(new Ships(1));
        shipsAIList.add(new Ships(1));
        shipsAIList.add(new Ships(1));
        shipsAIList.add(new Ships(2));
        shipsAIList.add(new Ships(2));
        shipsAIList.add(new Ships(2));
        shipsAIList.add(new Ships(3));
        shipsAIList.add(new Ships(3));
        shipsAIList.add(new Ships(4));
    }
    
    public void fillGridPane(GridPane pnGridBox) {
        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                Pane anchorPane = new Pane();
                anchorPane.getStyleClass().add("centre-grid");
                pnGridBox.add(anchorPane, i, j);
            }
        }
    }
    
    public void fillShips(StackPane[] arrPane) {
        addShip(arrPane[0], 1);
        addShip(arrPane[0], 1);
        addShip(arrPane[0], 1);
        addShip(arrPane[0], 1);
        addShip(arrPane[1], 2);
        addShip(arrPane[1], 2);
        addShip(arrPane[1], 2);
        addShip(arrPane[2], 3);
        addShip(arrPane[2], 3);
        addShip(arrPane[3], 4);
    }
    
    private void addShip(StackPane pnStack, int num) {
        Ships ships = new Ships();
        for (int i = 0; i < num; i++) {
            Ship ship = new Ship();
            pnStack.getChildren().add(ship);
            ships.addShip(ship);
        }
        getShipsList().add(ships);
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }
    
    public Scene getGameScene() {
        return gameScene;
    }

    public void setGameScene(Scene gameScene) {
        this.gameScene = gameScene;
    }

    public Scene getStartScene() {
        return startScene;
    }

    public void setStartScene(Scene startScene) {
        this.startScene = startScene;
    }   

    public StartController getStartController() {
        return startController;
    }

    public void setStartController(StartController startController) {
        this.startController = startController;
    }

    public GameController getGameController() {
        return gameController;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public PlayerBoard getPlayerBoard() {
        return playerBoard;
    }
    
    public AIBoard getAIBoard() {
        return aiBoard;
    }

    public LinkedList<Ships> getShipsList() {
        return shipsList;
    }

    public LinkedList<Ships> getShipsAIList() {
        return shipsAIList;
    }
}
