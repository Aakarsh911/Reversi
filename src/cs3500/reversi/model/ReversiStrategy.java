package cs3500.reversi.model;

import java.util.List;
import java.util.Optional;

/**
 * Represents a strategy for choosing a move in Reversi.
 */
public interface ReversiStrategy {
  /**
   * Chooses a move for the given player.
   * @param model the model to choose a move on
   * @param player the player to choose a move for
   * @return the move to make
   */
  Optional<List<Integer>> chooseMove(ReadOnlyModel model, Player player);
}

