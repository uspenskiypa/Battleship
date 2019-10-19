package ru.battleship.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class StartController {
    
    @FXML
    private GridPane pnGridBox;
    
    @FXML
    private StackPane pnStack;
    
    private final int GRID_WIDTH = 10, GRID_HEIGHT = 10;
    private Image image; //изображение иконки компьютера
    private ImageView imageView; //левый контейнер для изображения
    private ImageView imageContainer; //левый контейнер для изображения
    
    @FXML
    private void initialize() {
        fillGridPane();
        image = new Image(getClass().getResourceAsStream("/ru/battleship/icons/ship.png"));
        imageView = new ImageView(image);
        pnStack.getChildren().add(imageView);
        fillStackPane();
    }  
    
    public void pnGridBoxMouseReleasedAction(MouseEvent e) {
        if (imageContainer == null) {
            e.consume();
        }
        try {
            if (e.getTarget() instanceof Pane) {
                Pane pane = (Pane) e.getTarget();
                pane.getChildren().add(imageContainer);
                imageContainer = null;
            }
        }
        catch(Exception ex) {
        }
    }
    
    EventHandler MousePressedHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e){
            
        }
    };
    
    EventHandler MouseDraggedHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e){
            
        }
    };
        
    EventHandler MouseReleasedHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e){
            imageContainer = (ImageView) e.getTarget();
        }
    };
    
    private void fillGridPane() {
        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                Pane anchorPane = new Pane();
                anchorPane.getStyleClass().add("centre-grid");
                pnGridBox.add(anchorPane, i, j);
            }
        }
    }
    
    private void fillStackPane() {
        for (Node node : pnStack.getChildren()) {
            node.setOnMousePressed(MousePressedHandler);
            //node.setOnMouseDragged(MouseDraggedHandler);
            node.setOnMouseReleased(MouseReleasedHandler);
            node.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    node.setLayoutX(e.getSceneX());
                    node.setLayoutY(e.getSceneY());
                }
            });
        } 
    }  
}
