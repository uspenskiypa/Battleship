package ru.battleship.objects;

import java.util.LinkedList;
import javafx.scene.layout.GridPane;

public class Board {
    
    private Cell[][] cellField; //двумерный массив ячеек
    private final int width, height; //ширина и высота поля
    private final int[] dx = {-1, -1, -1, 0, 0, 0, 1, 1, 1}; //изменение координат по х
    private final int[] dy = {-1, 0, 1, -1, 0, 1, -1, 0, 1}; //изменение координат по y

    public Board(GridPane pnGridBox, String style, int size) {
        height = size;
        width = size;
        cellField = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Cell cell = new Cell(i, j);
                cell.getStyleClass().add("centre-grid");
                cell.getStyleClass().add(style);
                cellField[i][j] = cell;
                pnGridBox.add(cell, j, i);
            }
        }
    }

    //Геттер поля cellField
    public Cell[][] getCellField() {
        return cellField;
    }

    public boolean isCurrectCell(int[] coords, Ships ships) {
        for (int k = 0; k < ships.size(); k++) {
            int x = coords[0];
            int y = coords[1];
            switch (ships.getCourse()) {
                case 0: y -= k; break;
                case 1: y += k; break;
                case 2: x += k; break;
                case 3: x -= k; break;
            }
            if ((x < 0) || (y < 0) || (x >= width) || (y >= height)) {
                return false;
            }
            for (int i = 0; i < dx.length; i++) {
                int xc = x + dx[i];
                int yc = y + dy[i];
                if ((xc < 0) || (yc < 0) || (xc >= width) || (yc >= height)) {
                    continue;
                }
                if (cellField[xc][yc].getState() == State.SHIP) {
                    if (!ships.contains(cellField[xc][yc].getShip())) {
                        return false;
                    }
                }
            }
            cellField[x][y].setShip(ships.get(k));
            ships.get(k).setCell(cellField[x][y]);
        }
        return true;
    }
    
        public boolean isGoodCell(int[] coords, Ships ships) {
        for (int k = 0; k < ships.size(); k++) {
            int x = coords[0];
            int y = coords[1];
            switch (ships.getCourse()) {
                case 0: y -= k; break;
                case 1: y += k; break;
                case 2: x += k; break;
                case 3: x -= k; break;
            }
            if ((x < 0) || (y < 0) || (x >= width) || (y >= height)) {
                return false;
            }
            if (!cellField[x][y].getIsHidden()) {
                    return false;
            }
            for (int i = 0; i < dx.length; i++) {
                int xc = x + dx[i];
                int yc = y + dy[i];
                if ((xc < 0) || (yc < 0) || (xc >= width) || (yc >= height)) {
                    continue;
                }
                if (cellField[xc][yc].getState() == State.DESTROYED) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public void changeStates(int[][] gridBox) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (gridBox[i][j] == 0) {
                    cellField[i][j].setState(State.AQUA);
                }
                else {
                    cellField[i][j].setState(State.SHIP);
                }
            }
        }
    }
    
    public void changeStates(LinkedList<Ship> wrecks) {
        for (int k = 0; k < wrecks.size(); k++) {
            for (int i = 0; i < dx.length; i++) {
                int xc = wrecks.get(k).getCell().getX() + dx[i];
                int yc = wrecks.get(k).getCell().getY() + dy[i];
                if ((xc < 0) || (yc < 0) || (xc >= width) || (yc >= height)) {
                    continue;
                }
                if (cellField[xc][yc].getState() == State.AQUA) {
                   cellField[xc][yc].setIsHidden(false);
                }
            }
        }
    }

    public void changeValues(Cell cell) {
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        int x = cell.getX();
        int y = cell.getY(); 
        for (int i = 0; i < dx.length; i++) {
            for (int j = 0; j < 3; j++) {
                int xc = x + dx[i]*(j+1);
                int yc = y + dy[i]*(j+1);
                if ((xc < 0) || (yc < 0) || (xc >= width) || (yc >= height)) {
                    break;
                }
                xc = x + dx[i];
                yc = y + dy[i];
                Cell dxyCell = cellField[xc][yc];
                if (dxyCell.getIsHidden()) {
                    dxyCell.setValue(dxyCell.getValue() + 40);
                }
                xc -= dx[i]*2;
                yc -= dy[i]*2;
                if ((xc < 0) || (yc < 0) || (xc >= width) || (yc >= height)) {
                    continue;
                }
                Cell nextCell = cellField[xc][yc];
                if (!nextCell.getIsHidden() && nextCell.getState() == State.WRECK) {
                    if (dxyCell.getIsHidden()) {
                        dxyCell.setValue(dxyCell.getValue() + 100);
                        break;
                    }
                }
            }
        }
    }
    
    public boolean check(Cell cell) {
        int x = cell.getX();
        int y = cell.getY();
        for (int i = 0; i < dx.length; i++) {
            int xc = x + dx[i];
            int yc = y + dy[i];
            if ((xc < 0) || (yc < 0) || (xc >= width) || (yc >= height)) {
                continue;
            }
            if (cellField[xc][yc].getState() == State.WRECK || cellField[xc][yc].getState() == State.DESTROYED) {
                return false;
            }
        }
        return true;
    }
    
    public void setVisible() {
        for (Cell[] cells: cellField) {
            for (Cell cell: cells) {
                if (!cell.getChildren().isEmpty() && cell.getChildren().get(0) instanceof Ship) {
                    cell.getShip().getShips().setOpasity(1);
                }
                cell.getStyleClass().set(1, "visible");
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
