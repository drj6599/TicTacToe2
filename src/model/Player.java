package model;

import exception.CellIsOccupiedException;
import exception.InvalidCellInputException;

import java.util.Scanner;

public class Player {
    private int id;
    private String name;
    private char symbol;
    private PlayerType playerType;

    public Player(int id, String name, char symbol, PlayerType playerType) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
    }

    public Player() {
    }

    public Move makeMove(Board board){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the row for the target cell");
        int row = sc.nextInt();
        System.out.println("Enter the col for the target cell");
        int col = sc.nextInt();
        if(row < 0 || row >= board.getDimension() || col < 0 || col >= board.getDimension()){
            throw new InvalidCellInputException("The row and col entered is not present on the borad");
        }
        Cell playeMoveCell = board.getMatrix().get(row).get(col);
        if(playeMoveCell.getCellState().equals(CellState.FILLED)){
            throw new CellIsOccupiedException("Selected cell is already filled");
        }
        playeMoveCell.setCellState(CellState.FILLED);
        playeMoveCell.setPlayer(this);
        return new Move(playeMoveCell,this);
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        symbol = symbol;
    }
}
