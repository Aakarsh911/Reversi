package cs3500.reversi.model;

import java.util.List;
import java.util.Map;

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
  CellState getColor(Hex h);

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
   * gets the map of the board state.
   * @return the map of the board state
   */
  Map<Hex, CellState> getBoardState();
}
