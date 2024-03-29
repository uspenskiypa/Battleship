package ru.battleship.objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ship extends ImageView {

    private Image image;
    private Ships ships;
    private Cell cell;
    
    public Ship() {
        image = new Image(getClass().getResourceAsStream("/ru/battleship/icons/ship.png"));
        setImage(image);
    }
    
    public Ship(Ships ships) {
        this();
        this.ships = ships;
    }

    public Ships getShips() {
        return ships;
    }

    public void setShips(Ships ships) {
        this.ships = ships;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }
}
