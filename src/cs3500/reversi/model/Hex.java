package cs3500.reversi.model;

import java.util.List;
import java.util.Map;

/**
 * Represents a hexagonal cell in the game of Reversi.
 */
public interface Hex {
  /**
   * Renders the game.
   *
   * @return the string representation of the game
   */
  String toString();

  /**
   * Gets the neighbors of this cell.
   *
   * @return the neighbors of this cell
   */
  List<Hex> neighbors();

  /**
   * Gets the direction of this cell.
   *
   * @param c the cell to get the direction of
   * @return the direction of this cell
   */
  Hex getDirection(Hex c);

  /**
   * Gets all the cells in a given direction from this cell.
   *
   * @param c     the cell to get the direction of
   * @param board the board to get the cells from
   * @return the cells in a given direction from this cell
   */
  List<Hex> cellsInDirection(Hex c, Map<Hex, CellState> board);

  /**
   * Gets the q coordinate of this cell.
   *
   * @return the q coordinate of this cell
   */
  int getQ();

  /**
   * Gets the r coordinate of this cell.
   *
   * @return the r coordinate of this cell
   */
  int getR();

  /**
   * Gets the s coordinate of this cell.
   *
   * @return the s coordinate of this cell
   */
  int getS();
}
