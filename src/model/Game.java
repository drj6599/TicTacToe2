package model;

import exception.InvalidNumberOfBotsException;
import exception.InvalidPlayerSizeException;
import exception.InvalidSymbolSetupException;
import service.winningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Game {
    private int dimension;
    private Board currentBoard;
    private List<Player> players;
    private Player currentPlayer;
    private GameStatus gameStatus;
    private List<Move> moves;
    private List<Board> boardStates;
    private WinningStrategy winningStrategy;
    private int noOfSymbols;

    private Game(int dimension ,Board currentBoard, List<Player> players, WinningStrategy winningStrategy) {
        this.dimension = dimension;
        this.currentBoard = currentBoard;
        this.players = players;
        this.currentPlayer = null;
        this.gameStatus = GameStatus.IN_PROGRESS;
        this.moves = new ArrayList<>();
        this.boardStates = new ArrayList<>();
        this.winningStrategy = winningStrategy;
        this.noOfSymbols = players.size();
    }

    public Builder builder(){
        return new Builder();
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public Board getCurrentBoard() {
        return currentBoard;
    }

    public void setCurrentBoard(Board currentBoard) {
        this.currentBoard = currentBoard;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public List<Board> getBoardStates() {
        return boardStates;
    }

    public void setBoardStates(List<Board> boardStates) {
        this.boardStates = boardStates;
    }

    public WinningStrategy getWinningStrategy() {
        return winningStrategy;
    }

    public void setWinningStrategy(WinningStrategy winningStrategy) {
        this.winningStrategy = winningStrategy;
    }

    public int getNoOfSymbols() {
        return noOfSymbols;
    }

    public void setNoOfSymbols(int noOfSymbols) {
        this.noOfSymbols = noOfSymbols;
    }

    public static class Builder{
        private int dimension;
        private Board currentBoard;
        private List<Player> players;
        private WinningStrategy winningStrategy;

        public Builder setCurrentBoard(Board currentBoard) {
            this.currentBoard = currentBoard;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategy(WinningStrategy winningStrategy) {
            this.winningStrategy = winningStrategy;
            return this;
        }



        public Builder setDimension(int dimension){
            this.dimension = dimension;
            return this;
        }

        private void validateNumberOfPlayers() {
            if(players.size() < currentBoard.getDimension()-2 || players.size() >= currentBoard.getDimension()){
                throw new InvalidPlayerSizeException("Number of Players should be N-1 or N-2 as per the board size");
            }
        }

        private void validatePlayerSymbols(){
            Set<Character> symbols = players.stream().map(player -> player.getSymbol()).collect(Collectors.toSet());
            if(symbols.size() != players.size()){
                throw new InvalidSymbolSetupException("There should be unique symbols for all the players");
            }
        }

        private void validateBotCount(){
            long botCount = players.stream().filter(player -> player.getPlayerType().equals(PlayerType.BOT)).count();
            if(botCount > 1) {
                throw new InvalidNumberOfBotsException("Number of Bots allowed is only 1");
            }
        }

        private void validate(){
            validateBotCount();
            validateNumberOfPlayers();
            validatePlayerSymbols();
        }

        public Game build(){
            validate();
            return new Game(dimension,new Board(dimension),players,winningStrategy);
        }
    }
}
