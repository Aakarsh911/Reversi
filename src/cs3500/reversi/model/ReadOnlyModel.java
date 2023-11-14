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

  /**
   * gets the number of rows in the board.
   * @return the number of rows in the board
   */
  int getNumRows();

  /**
   * checks if a given move is legal.
   * @param row the row of the move
   * @param col the column of the move
   */
  boolean isLegalMove(int row, int col);

  /**
   * checks if there is any legal move for the current player
   */
  boolean anyLegalMovesForCurrentPlayer();

  /**
   * Creates a copy of this BasicReversi.
   * @return a copy of this BasicReversi
   */
  ReversiModel copy();
}
