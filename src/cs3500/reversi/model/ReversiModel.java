package cs3500.reversi.model;

/**
 * Represents a model for the game of Reversi.
 */
public interface ReversiModel extends ReadOnlyModel {

  /**
   * Moves the piece at the given coordinates.
   *
   * @param x the row number of the piece to move
   * @param y the column number of the piece to move
   */
  void move(int x, int y);

  /**
   * Passes the turn.
   */
  void pass();

  void addListener(ModelListener p);

  void startGame();
}
