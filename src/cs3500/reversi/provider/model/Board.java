package cs3500.reversi.provider.model;

import java.util.List;
import java.util.Set;

public interface Board {
  /**
   * Checks if a piece is present at the specified hexagonal position.
   *
   * @param hex The hexagonal position to check.
   * @return True if a piece is present, false otherwise.
   * @throws IllegalArgumentException if the hexagonal position is not a valid part of the board.
   */
   boolean hasPieceAt(TPRHex hex) throws IllegalArgumentException;

  /**
   * Gets a set of all valid hexagonal positions on the board.
   *
   * @return A set of valid hexagonal positions.
   */
   Set<TPRHex> getKeys();

  /**
   * Finds a list of hexagonal positions in a straight line from a given location in a specified
   * direction.
   *
   * @param location  The starting hexagonal position.
   * @param direction The direction to search for hexagonal positions.
   * @return A list of hexagonal positions in a straight line.
   */
   List<TPRHex> getStraightLine(TPRHex location, TPRHex direction);

  /**
   * Returns a copy of any board.
   *
   * @return new Board.
   */
   Board getCopy();

  /**
   * Gets the piece at the specified TPRHex.
   *
   * @param hex The hexagonal position to retrieve the piece from.
   * @return The piece at the specified position or null if no piece is present.
   * @throws IllegalArgumentException if the hexagonal position is not a valid part of the board.
   */
   Piece getPieceAt(TPRHex hex) throws IllegalArgumentException;

  /**
   * Gets the piece at the specified hexagonal coordinates.
   *
   * @param q q coordinate in terms of the Cube/Axial coordinate system
   * @param r r coordinate in terms of the Cube/Axial coordinate system
   * @return the Piece at the specified position or null if no piece is present
   * @throws IllegalArgumentException if the hexagonal position is not a valid part of the board.
   */
   Piece getPieceAt(int q, int r) throws IllegalArgumentException;

  /**
   * Places a piece at the specified hexagonal position on the board.
   *
   * @param piece The piece to place (BLACK or WHITE).
   * @param hex   The hexagonal position to place the piece at.
   * @throws IllegalArgumentException if the piece is null or the hexagonal position is not a valid
   *                                  part of the board.
   */
   void placePieceAt(Piece piece, TPRHex hex);

  /**
   * Gets the TPRHex corners of the board, each board will always have 6 corners.
   * @return List of TPRHex corners
   */
  List<TPRHex> getCorners();
}
