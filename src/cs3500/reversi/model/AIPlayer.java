package cs3500.reversi.model;

import java.util.List;
import java.util.Optional;

import cs3500.reversi.view.ViewFeatures;

/**
 * Represents an AI player that uses a strategy to make moves.
 */
public class AIPlayer implements Player {
  private final ReversiStrategy strategy;
  private final String color;

  private ViewFeatures featuresListener;

  private boolean isTurn = false;

  private final ReadOnlyModel model;

  /**
   * Constructs an AI player with the given strategy.
   * @param strategy the strategy to use
   */
  public AIPlayer(ReversiStrategy strategy, String color, ReadOnlyModel model) {
    this.strategy = strategy;
    this.color = color;
    this.model = model;
  }

  @Override
  public void notifyTurn() {
  isTurn = true;
  this.makeMove();
  }

  @Override
  public String getColor() {
    return color;
  }

  @Override
  public void setFeaturesListener(ViewFeatures features) {
    this.featuresListener = features;
  }

  public void makeMove() {
    Optional<List<Integer>> move = strategy.chooseMove(model,this);
    if (move.isPresent()) {
      featuresListener.move(move.get().get(0), move.get().get(1));
    }
    else {
      featuresListener.pass();
    }
    isTurn = false;
  }
}
