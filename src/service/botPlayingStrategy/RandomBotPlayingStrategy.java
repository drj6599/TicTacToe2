package service.botPlayingStrategy;

import exception.GameOverException;
import model.*;

import java.util.List;
import java.util.Random;

public class RandomBotPlayingStrategy implements BotPlayingStrategy{

        @Override
        public Move makeMove( Board board , Player bot) {
            Random random = new Random();
            while(true){
                int row = random.nextInt(board.getDimension());
                int col = random.nextInt(board.getDimension());
                Cell selectedCell = board.getMatrix().get(row).get(col);
                if(selectedCell.getCellState() == CellState.EMPTY)
                {
                    selectedCell.setCellState(CellState.FILLED);
                    selectedCell.setPlayer(bot);
                    return new Move(selectedCell,bot);
                }
                if(!checkEmptyCells(board.getMatrix()))
                {
                    throw new GameOverException("Game is over as there are no Empty Cells");
                }
            }
        }

        private boolean checkEmptyCells(List<List<Cell>> matrix)
        {
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
                return false;
            }
            return true;
        }
}
