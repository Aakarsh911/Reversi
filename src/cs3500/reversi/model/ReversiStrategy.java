package cs3500.reversi.model;

import java.util.List;
import java.util.Optional;

public interface ReversiStrategy {
  /**
   * Chooses a move for the given player.
   * @param board the board to choose a move on
   * @param player the player to choose a move for
   * @return the move to make
   */
  Optional<List<Integer>> chooseMove(ReadOnlyModel board, Player player);
}

