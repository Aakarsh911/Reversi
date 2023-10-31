package cs3500.reversi.model;

/**
 * Represents a model for the game of Reversi.
 */
public interface ReversiModel extends ReadOnlyModel {

  /**
   * Moves the piece at the given coordinates.
   *
   * @param x the x coordinate of the piece to move
   * @param y the y coordinate of the piece to move
   */
  void move(int x, int y);

  /**
   * Passes the turn.
   */
  void pass();
}
