package service.winningStrategy;

public class WinningStrategyFactory {
    public static WinningStrategy getWinningStrategy(int dimension,WinningStrategyName name){
        switch (name){
            case ORDER_ONE -> {
                return new OrderOneWinningStrategy(dimension);
            }
            default -> {
                return null;
            }
        }
    }
}
