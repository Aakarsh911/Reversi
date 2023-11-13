package cs3500.reversi.model;

/**
 * Represents an AI player that uses a strategy to make moves.
 */
public class AIPlayer implements Player {
  ReversiStrategy strategy;

  /**
   * Constructs an AI player with the given strategy.
   * @param strategy the strategy to use
   */
  public AIPlayer(ReversiStrategy strategy) {
    this.strategy = strategy;
  }

  @Override
  public void notifyTurn() {

  }
}
