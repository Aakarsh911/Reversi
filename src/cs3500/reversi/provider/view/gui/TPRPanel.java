package cs3500.reversi.provider.view.gui;


import java.awt.Point;

/**
 * A panel interface for a TPR GUI that has the ability to highlight a selected cell at a certain
 * point. The panel is responsible for drawing the board and its pieces.
 */
public interface TPRPanel {

  /**
   * Highlights a cell if it is not currently highlighted, unhighlights a cell if it is currently
   * highlighted and clicked on again, another cell other than the one highlighted is clicked, or
   * the click point is off the board.
   *
   * @param screenCoords a Point representing the coordinates for the click in terms of the screen
   */
  void selectCell(Point screenCoords);

}
