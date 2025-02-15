package cs3500.reversi.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.List;


import cs3500.reversi.model.ReadOnlyModel;

/**
 * Represents the panel for the Reversi game with hints.
 */
public class HintDecorator extends ReversiPanel {
  private final ReversiViewPanel panel;
  private boolean hintMode = false;
  private final ReadOnlyModel model;

  /**
   * Constructs a ReversiPanel with hints.
   *
   * @param panel the panel to decorate
   * @param model the model to use
   */
  public HintDecorator(ReversiViewPanel panel, ReadOnlyModel model) {
    super(model);
    this.panel = panel;
    this.model = model;
  }

  /**
   * Changes the hint mode.
   */
  public void changeHintMode() {
    hintMode = !hintMode;
  }

  /**
   * Sets the hint mode.
   *
   * @param hintMode the hint mode
   */
  public void setHintMode(boolean hintMode) {
    this.hintMode = hintMode;
  }

  @Override
  public Dimension getPreferredSize() {
    return panel.getPreferredSize();
  }

  @Override
  public void paintComponent(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(Color.DARK_GRAY);
    g2d.fill(new Rectangle(this.getSize()));
    super.paintComponent(g);
    panel.setSelectCell(this.getSelectedCell().get(0), this.getSelectedCell().get(1));
    List<Integer> selectedCell = this.getSelectedCell();
    if (hintMode && selectedCell.get(0) != -1 && selectedCell.get(1) != -1) {
      g2d.setColor(Color.BLACK);
      int k = Math.abs(model.getBoard().size() / 2 - selectedCell.get(0));
      int x = k * 17 + 17 + (selectedCell.get(1) * 34)
              + this.getSize().width / 2 - model.getBoard().size() * 17;
      int y = 15 + (selectedCell.get(0) * 30)
              + this.getSize().height / 2 - model.getBoard().size() * 15;
      String output = "0";
      if (!model.getColor(model.getBoard().get(selectedCell.get(0))
              .get(selectedCell.get(1))).equals("EMPTY")) {
        output = "";
      } else if (model.isLegalMove(selectedCell.get(0), selectedCell.get(1))) {
        output = model.getCellsToFlip(selectedCell.get(0), selectedCell.get(1)).size() + "";
      }
      g2d.drawString(output, x - 5, y + 5);
    }
  }
}