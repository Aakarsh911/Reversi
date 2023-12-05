package cs3500.reversi.provider.model;

/**
 * Represents a public model interface for a game of Reversi. These are the mutator methods.
 */

public interface ReversiModel extends ReadonlyReversiModel, TPRModelSubject {
  void startGame();

  /**
   * Places a piece at one hexagon and flips any other pieces of the same color as needed.
   *
   * @throws IllegalArgumentException if piece or location are not valid
   * @throws IllegalStateException    if move is not allowed.
   */
  void movePiece(Piece piece, TPRHex location)
          throws IllegalStateException, IllegalArgumentException;

  /**
   * Indicate that the player has passed their turn.
   *
   * @param player resembles one of the 2 players.
   */
  void passTurn(Piece player);
}
