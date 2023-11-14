package cs3500.reversi.model;

/**
 * Represents an AI player that uses a strategy to make moves.
 */
public class AIPlayer implements Player {
  ReversiStrategy strategy;
  String color;

  /**
   * Constructs an AI player with the given strategy.
   * @param strategy the strategy to use
   */
  public AIPlayer(ReversiStrategy strategy, String color) {
    this.strategy = strategy;
    this.color = color;
  }

  @Override
  public void notifyTurn() {

  }

  @Override
  public String getColor() {
    return color;
  }
}
