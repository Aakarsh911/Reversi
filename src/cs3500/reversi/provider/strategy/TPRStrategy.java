package cs3500.reversi.provider.strategy;

import cs3500.reversi.provider.model.Piece;
import cs3500.reversi.provider.model.ReadonlyReversiModel;
import cs3500.reversi.provider.model.TPRHex;

/**
 * An interface for a strategy for playing Two Player Reversi.
 */
public interface TPRStrategy {
  /**
   * Chooses a move based on the strategy.
   *
   * @param model       ReadonlyReversiModel for obtaining the current game state
   * @param pieceToMove Piece representing the color of the piece the strategy is playing for
   * @return Hexagon to move to based on the strategy
   */
  TPRHex chooseMove(ReadonlyReversiModel model, Piece pieceToMove);
}
