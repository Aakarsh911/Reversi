package cs3500.reversi.provider.model;

import java.util.List;

/**
 * Interface for a hexagon in a game of Reversi. Pieces can be placed on hexagons.
 */
public interface TPRHex {

  /**
   * Get the q-coordinate of the hexagon.
   *
   * @return The q-coordinate.
   */
  int getQ();

  /**
   * Get the r-coordinate of the hexagon.
   *
   * @return The r-coordinate.
   */
  int getR();

  /**
   * Get the s-coordinate of the hexagon.
   *
   * @return The s-coordinate.
   */
  int getS();

  /**
   * Gets a list of the neighboring hexagons.
   *
   * @return a list of the neighbors
   */
  List<TPRHex> getNeighborDirections();

  /**
   * Get a list of neighboring hexagons for this hexagon.
   *
   * @return A list of neighboring hexagons.
   */
  List<TPRHex> getNeighbors();

  /**
   * Subtract two hexagons and return the result as a new hexagon.
   *
   * @param hex The hexagon to subtract from this hexagon.
   * @return A new hexagon representing the difference between this hexagon and the given hexagon.
   */
  TPRHex subtract(TPRHex hex);

  /**
   * Add two hexagons and return the result as a new hexagon.
   *
   * @param hex The hexagon to add to this hexagon.
   * @return A new hexagon representing the sum of this hexagon and the given hexagon.
   */
  TPRHex add(TPRHex hex);


}
