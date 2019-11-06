package ru.battleship.objects;

import java.util.LinkedList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
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
    private Board playerBoard;
    private Board aiBoard;
    
    private Game() {
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
    
    public void createBoard(GridPane pnGridBox, String display, int i) {
        if (display.equals("visible")) {
            playerBoard = new Board(pnGridBox, display, i);
        }
        else {
            aiBoard = new Board(pnGridBox, display, i);
        }
    }

    public void addEvent(StackPane[] arrPane) {
        for (StackPane pn: arrPane) {
            pn.setOnMouseReleased(MouseReleasedHandler);
        }
    }
    
    EventHandler MouseReleasedHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
            if (e.getButton() == MouseButton.SECONDARY) {
                return;
            }
            if (e.getTarget() instanceof Ship) {
                Ships ships = startController.getShipsContainer();
                if (ships != null) {
                    ships.setOpasity(1);
                }
                ships = ((Ship) e.getTarget()).getShips();
                ships.setOpasity(0.3);
                startController.setShipsContainer(ships);
            } 
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
    
    public Board getAIBoard() {
        return aiBoard;
    }
    
    public Board getPlayerBoard() {
        return playerBoard;
    }

    public LinkedList<Ships> getShipsList() {
        return shipsList;
    }

    public LinkedList<Ships> getShipsAIList() {
        return shipsAIList;
    }
}
