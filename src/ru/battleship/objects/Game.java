package ru.battleship.objects;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import ru.battleship.controller.GameController;
import ru.battleship.controller.StartController;

public class Game {
    public static final int GRID_WIDTH = 10, GRID_HEIGHT = 10;
    private Scene gameScene;
    private Scene startScene;
    private StartController startController;
    private GameController gameController;
    private static Game instance;
    private Image image;
    private Image[] arrImage = new Image[5];
    private ImageView imageContainer;
    
    private Game() {
        image = new Image(getClass().getResourceAsStream("/ru/battleship/icons/ship.png"));
        arrImage[4] = image;
    }
    
    public void fillGridPane(GridPane pnGridBox) {
        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                Pane anchorPane = new Pane();
                anchorPane.getStyleClass().add("centre-grid");
                anchorPane.setOnMouseMoved(MouseMovedHandler);
                pnGridBox.add(anchorPane, i, j);
            }
        }
    }
    
    public void fillStackPane(StackPane pnStack, int num) {
        for (int i = 0; i < num; i++) {
            ImageView imageViewer = new ImageView(arrImage[num]);
            imageViewer.setOnMousePressed(MousePressedHandler);
            imageViewer.setOnMouseReleased(MouseReleasedHandler);
            pnStack.getChildren().add(imageViewer);
        }
    }
    
    EventHandler MouseMovedHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e){
            System.out.println(e.getTarget());
            Pane pane = (Pane) e.getTarget();
            
        }
    };
    
    EventHandler MousePressedHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e){
            System.out.println(e.getTarget());
        }
    };
    
        
    EventHandler MouseReleasedHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e){
            setImageContainer((ImageView) e.getTarget());
            System.out.println(e.getTarget());
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

    public ImageView getImageContainer() {
        return imageContainer;
    }

    public void setImageContainer(ImageView imageContainer) {
        this.imageContainer = imageContainer;
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
}
