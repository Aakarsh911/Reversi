package cs3500.reversi.view;

/**
 * Represents the features that a view can have.
 */
public interface ViewFeatures {
  /**
   * Sets the callback for when a move is made.
   *
   * @param row the row of the move
   * @param col the column of the move
   */
  void move(int row, int col);

  /**
   * Sets the callback for when the a player passes.
   */
  void pass();

}
