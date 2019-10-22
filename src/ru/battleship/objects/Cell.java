package ru.battleship.objects;

public class Cell {
    
    public State state; //статус ячейки
    public int x, y; //координаты ячейки
    public Ship ship; 
    public int value; //ценность ячейки
    
    
    public Cell(State state, int x, int y) {
        this.state = state;
        this.y = y;
        this.x = x;
        this.ship = null;
        this.value = 0;
    }
}
