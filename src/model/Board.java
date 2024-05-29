package model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int dimension;
    private List<List<Cell>> matrix;

    public Board(int dimension) {
        this.dimension = dimension;
        this.matrix = new ArrayList<>();
        for (int i = 0; i < dimension; i++) {
            this.matrix.add(new ArrayList<>());
            for (int j = 0; j < dimension; j++) {
                this.matrix.get(i).add(new Cell(i,j));
            }
        }
    }

    public Board(Board board) {
        this.dimension = board.dimension;
        this.matrix = new ArrayList<>();
        for (int i = 0; i < dimension; i++) {
            this.matrix.add(new ArrayList<>());
            for (int j = 0; j < dimension; j++) {
                this.matrix.get(i).add(new Cell(board.matrix.get(i).get(j)));
            }
        }
    }

    public Board clone(){
        return new Board(this);
    }
    public List<List<Cell>> getMatrix() {
        return matrix;
    }

    public void setMatrix(List<List<Cell>> matrix) {
        this.matrix = matrix;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }
    
    public void displayBoard(){
        for (int i = 0; i < dimension; i++) {
            List<Cell> cells = matrix.get(i);
            for(Cell cell : cells){
                cell.displayCell();
            }
            System.out.println();
        }
    }
}
