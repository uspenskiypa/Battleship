package ru.battleship.objects;

import java.util.LinkedList;

public class Ships {
    
    private LinkedList<Ship> ships;
    private int course;
    
    public Ships() { 
        ships = new LinkedList<>();
        course = 0;
    }
    
    public void addShip(Ship ship) {
        ships.add(ship);
        ship.setShips(this);
    }

    public int size() {
        return ships.size();
    }
    
    public boolean contains(Ship ship) {
        return ships.contains(ship);
    }
    
    public Ship get(int k) {
        return ships.get(k);
    }
    
    public void reverse() {
        java.util.Collections.reverse(ships);
    }
    
    public void setOpasity(double value) {
        for (Ship ship: ships) {
            ship.setOpacity(value);
        }
    }
    
    public LinkedList<Ship> getShips() {
        return ships;
    }

    public void setShips(LinkedList<Ship> ships) {
        this.ships = ships;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    } 
}
