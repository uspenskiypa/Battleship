package ru.battleship.objects;

import java.util.LinkedList;
import java.util.Random;

public class Ships {
    
    private LinkedList<Ship> ships;
    private int course;
    public final static int HORIZONTAL_COURSE = 0;
    public final static int VERTICAL_COURSE = 2;
    
    public Ships() { 
        ships = new LinkedList<>();
        course = HORIZONTAL_COURSE;
    }
    
    public Ships(int number) { 
        this();
        for (int i = 0; i < number; i++) {
            Ship ship = new Ship(this);
            ships.add(ship);
        }
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

    public void changeCourse() {
        course = (course < 2 ? VERTICAL_COURSE : HORIZONTAL_COURSE);
    }
    
    public void randomCourse() {
        Random rn = new Random();
        course = rn.nextBoolean() ? HORIZONTAL_COURSE : VERTICAL_COURSE;
    }
}
