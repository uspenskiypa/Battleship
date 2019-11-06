package ru.battleship.objects;

import java.util.LinkedList;
import java.util.Random;

public class Ships {
    
    private LinkedList<Ship> ships;
    private LinkedList<Ship> wrecks;
    private int course;
    public final static int HORIZONTAL_COURSE = 0;
    public final static int VERTICAL_COURSE = 2;
    
    public Ships() { 
        ships = new LinkedList<>();
        wrecks = new LinkedList<>();
    }
    
    public Ships(int number) { 
        this();
        for (int i = 0; i < number; i++) {
            Ship ship = new Ship(this);
            ship.setOpacity(0);
            ships.add(ship);
        }
    }
    
    public void addShip(Ship ship) {
        ships.add(ship);
        ship.setShips(this);
    }
    
    public void removeShip(Ship ship) {
        ships.remove(ship);
        wrecks.add(ship);
    }
    
    public boolean isEmpty() {
        return ships.isEmpty();
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
    
    public LinkedList<Ship> getWrecks() {
        return wrecks;
    }
        
    public void setOpasity(double value) {
        for (Ship ship: ships) {
            ship.setOpacity(value);
        }
    }
    
    public void setDestroyed() {
        for (Ship ship: wrecks) {
            ship.getCell().setState(State.DESTROYED);
        }
    }

    public int getCourse() {
        return course;
    } 
    
    public void setCourse(int course) {
        this.course = course;
    } 

    public void changeCourse() {
        course = (course < 2 ? VERTICAL_COURSE : HORIZONTAL_COURSE);
    }
    
    public void randomCourse() {
        Random rn = new Random();
        course = rn.nextBoolean() ? HORIZONTAL_COURSE : VERTICAL_COURSE;
    }
}
