package service.winningStrategy;

import model.Board;
import model.Move;
import model.Player;

public interface WinningStrategy {
    Player checkWinner(Board board , Move lastMove);
    public void undoLastMove(int row,int col,char symbol);
}
