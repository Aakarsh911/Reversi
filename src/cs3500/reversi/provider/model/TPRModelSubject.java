package cs3500.reversi.provider.model;

import cs3500.reversi.provider.controller.ModelStatusObserver;

/**
 * Interface for creating a subject of a Reversi Model to send updates to its observers.
 */
public interface TPRModelSubject {
  /**
   * Adds an observer to its list of observers to update.
   * @param obs ModelStatusObserver to add
   */
  void addStatusListener(ModelStatusObserver obs);

  /**
   * Informs observers that it is now {@param p}'s turn.
   * @param p Piece representing the color of the player whose turn it is
   */
  void announceNextTurn(Piece p);

  /**
   * Informs observers when the game of Reversi is over.
   * @param winner Piece representing the color of the player who won the game
   */
  void announceGameOver(Piece winner);


  /**
   * Informs observers when a player's score has been updated.
   * @param p Piece representing the color of the player whose score has changed
   * @param score int representing the new score of the player
   */
  void updateScore(Piece p, int score);
}
