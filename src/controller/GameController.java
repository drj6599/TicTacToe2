package controller;

import model.*;
import service.winningStrategy.WinningStrategy;
import service.winningStrategy.WinningStrategyFactory;
import service.winningStrategy.WinningStrategyName;

import java.util.List;

public class GameController {
    public Game createGame(int dimension , List<Player> players , WinningStrategyName winningStrategyName) {
        return Game.builder()
                .setDimension(dimension)
                .setPlayers(players)
                .setWinningStrategy(WinningStrategyFactory.getWinningStrategy(dimension,winningStrategyName))
                .build();
    }

    public void saveBoardState(Game game){
        game.getBoardStates().add(game.getCurrentBoard().clone());
    }

    public void saveMoves(Game game,Move move){
        game.getMoves().add(move);
    }

    public void displayGameReplay(Game game){
        List<Board> boards = game.getBoardStates();
        List<Move> moves = game.getMoves();
        for (int i = 0; i < boards.size(); i++) {
            Move move = moves.get(i);
            Board board = boards.get(i);
            int row = move.getCell().getRow();
            int col = move.getCell().getCol();
            System.out.println(move.getPlayer().getName() + "'s Move " + row + "," + col);
            board.displayBoard();
            System.out.println();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void displayBoard(Game game) {
        game.getCurrentBoard().displayBoard();
    }

    public GameStatus getGameStatus(Game game) {
        return game.getGameStatus();
    }

    public Move executeMove(Game game , Player player) {
        return player.makeMove(game.getCurrentBoard());
    }

    public Player checkWinner(Game game,Move lastMovePlayed) {
        return game.getWinningStrategy().checkWinner(game.getCurrentBoard(),lastMovePlayed);
    }

    public boolean checkDraw(Game game)
    {
        List<List<Cell>> matrix = game.getCurrentBoard().getMatrix();
        int countOfEmptyCells = 0;
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.size(); j++) {
                if(matrix.get(i).get(j).getCellState() == CellState.EMPTY)
                {
                    countOfEmptyCells++;
                }
            }
        }
        if(countOfEmptyCells == 0)
        {
            game.setGameStatus(GameStatus.DRAW);
            return true;
        }
        return false;
    }

    public void undoMove(Game game , Move lastMovePlayed) {
        int row = lastMovePlayed.getCell().getRow();
        int col = lastMovePlayed.getCell().getCol();
        char symbol = lastMovePlayed.getPlayer().getSymbol();
        lastMovePlayed.getCell().setPlayer(null);
        lastMovePlayed.getCell().setCellState(CellState.EMPTY);
        game.getMoves().removeLast();
        game.getBoardStates().removeLast();
        game.setCurrentBoard(game.getBoardStates().getLast().clone());
        game.getWinningStrategy().undoLastMove(row,col,symbol);
    }
}
