package cs3500.reversi.model;

import java.util.List;

/**
 * Represents a read only model for the game of Reversi.
 */
public interface ReadOnlyModel {
  /**
   * gets the board.
   * @return the board
   */
  List<List<Hex>> getBoard();

  /**
   * gets the state of a given cell.
   * @param h the cell to get the state of
   * @return the state of the given cell
   */
  String getColor(Hex h);

  /**
   * gets which player's turn it is.
   * @return which player's turn it is
   */
  String getTurn();

  /**
   * checks if the game is over.
   * @return whether or not the game is over
   */
  boolean isGameOver();

  /**
   * calculates the score of the game.
   */
  void calculateScore();

  /**
   * gets the score of the white player.
   * @return the score of the game
   */
  int getWhiteScore();

  /**
   * gets the score of the black player.
   * @return the score of the game
   */
  int getBlackScore();

  int getNumRows();

  boolean isLegalMove(int row, int col);

  boolean anyLegalMovesForCurrentPlayer();

}
