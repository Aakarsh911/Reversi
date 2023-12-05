package cs3500.reversi.provider.view.gui;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

/**
 * A class for a mouse adapter for TwoPlayerReversi. Listens for clicks.
 */
public class TPRMouseAdapter extends MouseAdapter {
  private final TPRPanel panel;

  /**
   * Constructor for TPRMouseAdapter that takes in a TPRPanel.
   *
   * @param panel TPRPanel that does something with the click
   */
  public TPRMouseAdapter(TPRPanel panel) {
    this.panel = Objects.requireNonNull(panel);
  }


  @Override
  public void mouseClicked(MouseEvent e) {
    //get screen coordinates of clicked cell
    //a) convert to hex and use new TPRBoardCell() to change color
    //b) keep it in screen and use bounds of cells to find respective cell

    Point screenCoords = e.getPoint();
    panel.selectCell(screenCoords);
  }

}
