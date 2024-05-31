import model.*;
import controller.*;
import service.winningStrategy.WinningStrategyName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        String replayAns = "Y";
        Scanner sc = new Scanner(System.in);
        do {
            int id = 1;
            int numberOfMoves = 0;
            GameController gameController = new GameController();
            List<Player> players = new ArrayList<>();
            System.out.println("Welcome to TicTacToe");
            System.out.println("Please enter the dimension for the board");
            int dimension = sc.nextInt();
            System.out.println("Do you want a bot in the game? Y or N");
            String botAns = sc.next();
            if (botAns.equalsIgnoreCase("Y")) {
                Player bot = new Bot(id++, '$', BotDifficultyLevel.MEDIUM);
                players.add(bot);
            }
            while (id < dimension) {
                System.out.println("Please enter the player name");
                String name = sc.next();
                System.out.println("Please enter the player's symbol");
                char symbol = sc.next().charAt(0);
                Player newPlayer = new Player(id++, name, symbol, PlayerType.HUMAN);
                players.add(newPlayer);
            }
            Collections.shuffle(players);
            Game game = gameController.createGame(dimension, players, WinningStrategyName.ORDER_ONE);
            int playerIndex = -1;
            while (game.getGameStatus().equals(GameStatus.IN_PROGRESS)) {
                System.out.println("Current board status");
                gameController.displayBoard(game);
                playerIndex++;
                playerIndex = playerIndex % players.size();
                System.out.println("It's " + players.get(playerIndex).getName() +"'s turn");
                Move movePlayed = gameController.executeMove(game, players.get(playerIndex));
                Player winner = gameController.checkWinner(game, movePlayed);
                gameController.saveBoardState(game);
                gameController.saveMoves(game,movePlayed);
                if (winner != null) {
                    game.setGameStatus(GameStatus.FINISHED);
                    System.out.println("Winner is : " + winner.getName());
                    break;
                }
                if(gameController.checkDraw(game)){
                    break;
                }
                numberOfMoves++;
                if(players.get(playerIndex).getPlayerType().equals(PlayerType.HUMAN) && numberOfMoves > 1){
                    System.out.println(players.get(playerIndex).getName() + " do you wish to undo your previous move ? Y or N");
                    String undoAns = sc.next();
                    if (undoAns.equalsIgnoreCase("Y")) {
                        gameController.undoMove(game, movePlayed);
                        playerIndex--;
                        numberOfMoves--;
                    }
                }
            }
            System.out.println("Final Board Status : ");
            gameController.displayBoard(game);
            System.out.println("Do you wish to watch a replay of the Game? Y or N");
            String gameReplayAns = sc.next();
            if(gameReplayAns.equalsIgnoreCase("Y")){
                gameController.displayGameReplay(game);
            }
            System.out.println("Do you want to replay the game ? Y or N");
            replayAns = sc.next();
        }while(replayAns.equalsIgnoreCase("Y"));
    }
}