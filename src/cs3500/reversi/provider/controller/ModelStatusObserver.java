package cs3500.reversi.provider.controller;

import cs3500.reversi.provider.model.Piece;

/**
 * Interface for an observer of the model, receives updates when certain things in the model change.
 */
public interface ModelStatusObserver {
  /**
   * Called when the model detects that it is now the next player's turn.
   * @param p Piece representing the color of the player whose turn it now is
   */
  void turnUpdate(Piece p);

  /**
   * Called when the model detects that the game is over.
   * @param winner Piece representing the color of the winner of this game
   */
  void gameIsOver(Piece winner);

  /**
   * Called when the score of a piece changes.
   * @param p Piece representing the color of the player
   * @param score int representing the new score of the player
   */
  void updateScore(Piece p, int score);
}
