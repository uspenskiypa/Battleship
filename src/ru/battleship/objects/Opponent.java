package ru.battleship.objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Opponent {
    
    private LinkedList<Ships> shipsList;
    private final static Game game = Game.getInstance();
    
    public Opponent() {
        shipsList = new LinkedList<>(); 
        shipsList.add(new Ships(4));
        shipsList.add(new Ships(3));
        shipsList.add(new Ships(2));
    }
    
    public Cell getBestCell(Board board) {
        valuation(board);
        int maxValue = Integer.MIN_VALUE;
        ArrayList<Cell> listBestCell = new ArrayList<>();
        for (Cell[] cells : board.getCellField()) {
            for (Cell cell : cells) {
                    if (cell.getValue() > maxValue) {
                        maxValue = cell.getValue();
                        listBestCell.clear();
                        listBestCell.add(cell);
                    } 
                    else if (cell.getValue() == maxValue) {
                        listBestCell.add(cell);
                    }
                }
            }
        Collections.shuffle(listBestCell);
        for (Cell[] cells : board.getCellField()) { 
            for (Cell cell : cells) {
                cell.setValue(0);
            }
        }
        return listBestCell.get(0);
    }
    
    private void valuation(Board board) {
        Cell[][] cellField = board.getCellField();
        for (int i = 0; i < cellField.length; i++) {
            for (int j = 0; j < cellField[0].length; j++) {
                Cell cell = cellField[i][j];
                if (cell.getIsHidden()) {
                    if (board.check(cell)) {
                        cell.setValue(cell.getValue() + 2);
                    }
                    for (int k = 0; k < 4; k++) {
                        if (game.getPlayerBoard().isGoodCell(new int[]{i,j}, shipsList.get(0))) {
                            cell.setValue(cell.getValue() + 4);
                        }
                        if (game.getPlayerBoard().isGoodCell(new int[]{i,j}, shipsList.get(1))) {
                            cell.setValue(cell.getValue() + 3);
                        }
                        if (game.getPlayerBoard().isGoodCell(new int[]{i,j}, shipsList.get(2))) {
                            cell.setValue(cell.getValue() + 2);
                        }                        
                        shipsList.get(0).setCourse(k+1);
                        shipsList.get(1).setCourse(k+1);
                        shipsList.get(2).setCourse(k+1);
                    }
                    shipsList.get(0).setCourse(0);
                    shipsList.get(1).setCourse(0);
                    shipsList.get(2).setCourse(0);
                }
                else if (cell.getState() == State.WRECK) {
                    board.changeValues(cell);
                }
            }
        }
    }
}
