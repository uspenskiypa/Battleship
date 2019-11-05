package ru.battleship.objects;

import java.util.LinkedList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
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
    private Ship shipContainer;
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
            ship.setOnMouseReleased(MouseReleasedHandler);
            pnStack.getChildren().add(ship);
            ships.addShip(ship);
        }
        getShipsList().add(ships);
    }
    
//    EventHandler MouseMovedHandler = new EventHandler<MouseEvent>() {
//        @Override
//        public void handle(MouseEvent e){
//
//        }
//    };    
        
    EventHandler MouseReleasedHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
            if (e.getButton() == MouseButton.SECONDARY) {
                return;
            }
            if (shipContainer != null) {
                shipContainer.getShips().setOpasity(1);
            }
            shipContainer = (Ship) e.getTarget();
            shipContainer.getShips().setOpasity(0.3);
        }
    };
    
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

    public Ship getShipContainer() {
        return shipContainer;
    }

    public void setShipContainer(Ship shipContainer) {
        this.shipContainer = shipContainer;
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
