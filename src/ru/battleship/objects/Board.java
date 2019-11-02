package ru.battleship.objects;

public class Board {
    
    private Cell[][] cellField; //двумерный массив ячеек
    private final int width, height; //ширина и высота поля
    private final int[] dx = {-1, -1, -1, 0, 0, 0, 1, 1, 1}; //изменение координат по х
    private final int[] dy = {-1, 0, 1, -1, 0, 1, -1, 0, 1}; //изменение координат по y

    public Board(int columns, int rows) {
        cellField = new Cell[columns][rows];
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                cellField[i][j] = new Cell(State.AQUA, i, j);
            }
        }
        width = columns;
        height = rows;
    }

    //Геттер поля cellField
    public Cell[][] getCellField() {
        return cellField;
    }

    public boolean isCurrectCell(int[] coords, Ships ships, boolean isReverse) {
        for (int k = 0; k < ships.size(); k++) {
            int x = coords[0];
            int y = coords[1];
            y += isReverse ? k : -k;
            if ((x < 0) || (y < 0) || (x >= width) || (y >= height)) {
                return false;
            }
            for (int i = 0; i < dx.length; i++) {
                int xc = x + dx[i];
                int yc = y + dy[i];
                if ((xc < 0) || (yc < 0) || (xc >= width) || (yc >= height)) {
                    continue;
                }
                if (!ships.contains(cellField[xc][yc].ship)) {
                    if (cellField[xc][yc].state == State.SHIP) {
                        return false;
                    }
                }
            }
            cellField[x][y].ship = ships.get(k);
        }
        return true;
    }

    public void changeStates(int[][] gridBox) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (gridBox[i][j] == 0) {
                    cellField[i][j].state = State.AQUA;
                }
                else {
                    cellField[i][j].state = State.SHIP;
                }
            }
        }
    }
}
