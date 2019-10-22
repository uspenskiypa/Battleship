package ru.battleship.objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ship extends ImageView {

    private Ship next;
    private Ship prev;
    private Image image;
    
    public Ship() {
        prev = null;
        next = null;
        image = new Image(getClass().getResourceAsStream("/ru/battleship/icons/ship.png"));
        setImage(image);
    }
    
    public Ship(Ship prev) {
        this();
        this.prev = prev;
    }
    
    public boolean hasNext() {
        return next != null;
    }
    
    public boolean hasPrev() {
        return prev != null;
    }

    public Ship getPrev() {
        return prev;
    }

    public void setPrev(Ship prev) {
        this.prev = prev;
    }
    
    public Ship getNext() {
        return next;
    }

    public void setNext(Ship next) {
        this.next = next;
    }
}
