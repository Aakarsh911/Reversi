package cs3500.reversi.model;

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
