package service.winningStrategy;

import model.Board;
import model.Move;
import model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneWinningStrategy implements WinningStrategy{

    private int dimension;
    private List<HashMap<Character,Integer>> rowHashMapList;
    private List<HashMap<Character,Integer>> colHashMapList;
    private HashMap<Character,Integer> leftDiagonal;
    private HashMap<Character,Integer> rightDiagonal;

    public OrderOneWinningStrategy(int dimension){
        this.dimension = dimension;
        rowHashMapList = new ArrayList<>();
        colHashMapList = new ArrayList<>();
        leftDiagonal = new HashMap<>();
        rightDiagonal = new HashMap<>();
        for (int i = 0; i < dimension; i++) {
            rowHashMapList.add(new HashMap<>());
            colHashMapList.add(new HashMap<>());
        }
    }

    @Override
    public Player checkWinner(Board board, Move lastMove) {
        Player player = lastMove.getPlayer();
        char symbol = player.getSymbol();
        int row = lastMove.getCell().getRow();
        int col = lastMove.getCell().getCol();

        boolean PlayerIsWinner = playerIsWinner(row,col,symbol);

        if(PlayerIsWinner) return player;
        else return null;
    }

    private boolean playerIsWinner(int row , int col , char symbol){
        return checkAndUpdateRespectiveHashMap(row,symbol,rowHashMapList)
                || checkAndUpdateRespectiveHashMap(col,symbol,colHashMapList)
                || (checkLeftDiagonal(row,col) && updateDiagonal(symbol,leftDiagonal))
                || (checkRightDiagonal(row,col) && updateDiagonal(symbol,rightDiagonal));
    }

    private boolean checkLeftDiagonal(int row,int col){
         return (row == col);
    }

    private boolean checkRightDiagonal(int row,int col){
        return ((row+col)==(dimension-1));
    }

    private boolean checkAndUpdateRespectiveHashMap(int idx,char symbol,List<HashMap<Character,Integer>> mapList){
        HashMap<Character,Integer> map = mapList.get(idx);
        int count = map.getOrDefault(symbol,0)+1;
        map.put(symbol,count);
        return count == dimension;
    }
    private boolean updateDiagonal(char symbol,HashMap<Character,Integer> map){
        int count = map.getOrDefault(symbol,0)+1;
        map.put(symbol,count);
        return count == dimension;
    }

    @Override
    public void undoLastMove(int row , int col , char symbol){
        checkAndRemoveFromRespectiveHashMap(row,symbol,rowHashMapList);
        checkAndRemoveFromRespectiveHashMap(col,symbol,colHashMapList);
        if(checkLeftDiagonal(row,col)){
            removeFromDiagonal(symbol,leftDiagonal);
        }
        if(checkRightDiagonal(row,col)){
            removeFromDiagonal(symbol,rightDiagonal);
        }
    }

    private void checkAndRemoveFromRespectiveHashMap(int idx,char symbol,List<HashMap<Character,Integer>> mapList){
        HashMap<Character,Integer> map = mapList.get(idx);
        int count = map.get(symbol);
        map.put(symbol,count-1);
    }

    private void removeFromDiagonal(char symbol,HashMap<Character,Integer> map){
        int count = map.get(symbol);
        map.put(symbol,count-1);
    }
}
