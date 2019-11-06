package ru.battleship.objects;

import javafx.scene.layout.Pane;

public class Cell extends Pane {
    
    private int x, y; //координаты ячейки
    private State state; //статус ячейки
    private Ship ship; //ссылка на корабль
    private boolean isHidden; //ячейка скрыта
    private int value; //ценность ячейки
    
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.state = State.AQUA;
        this.ship = null;
        this.isHidden = true;
        this.value = 0;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public boolean getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(boolean isHidden) {
        this.isHidden = isHidden;
        this.getStyleClass().set(1, "visible");
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(int value) {
        this.value = value;
    }
}
