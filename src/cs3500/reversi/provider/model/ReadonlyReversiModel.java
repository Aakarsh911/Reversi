package cs3500.reversi.provider.model;

import java.util.List;
import java.util.Set;

/**
 * A public interface for the Reversi Model with observation methods.
 */
public interface ReadonlyReversiModel {

  /**
   * Gets the size of a valid game board.
   *
   * @return size of the board (width and height)
   */
  int getSize();

  /**
   * Checks if the piece can be moved to a hexagon.
   *
   * @return true if the piece can be moved, false otherwise
   * @throws IllegalArgumentException if piece or location are not valid
   */
  boolean canMove(Piece piece, TPRHex location) throws IllegalArgumentException;

  /**
   * Checks if the game is over.
   *
   * @return true if game is over, false otherwise
   */
  boolean isGameOver();

  /**
   * Gets the current board of the game.
   *
   * @return the board for the current game
   */
  Board getBoard();

  /**
   * Finds out whose turn it is during the game.
   *
   * @return the player whose turn it is.
   */
  Piece getCurrentTurn();

  /**
   * Finds the list of valid moves.
   *
   * @return a set of a list of all the valid moves
   *     and the pieces in that line that will be flipped.
   */
  Set<List<TPRHex>> getAllValidMoves(Piece color);

  /**
   * Gets the current score for a particular piece color.
   *
   * @param color the color of the piece to retrieve the score of
   * @return int representing the score
   */
  int getScore(Piece color);

}
