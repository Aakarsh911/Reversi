package cs3500.reversi.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.*;

import cs3500.reversi.model.ReadOnlyModel;

public class HintDecorator extends ReversiPanel {
  private final ReversiPanel panel;
  private boolean hintMode = false;
  private final ReadOnlyModel model;
  private final HintDecorator self = this;

  public HintDecorator(ReversiPanel panel, ReadOnlyModel model) {
    super(model);
    this.panel = panel;
    this.model = model;
    this.getInputMap().put(KeyStroke.getKeyStroke("H"), "hint");
    this.getActionMap().put("hint", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("hint");
        hintMode = !hintMode;
        self.paintComponent(self.getGraphics());
      }
    });
  }

  public void changeHintMode() {
    hintMode = !hintMode;
  }

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
    if (selectedCell.get(0) == -1 && selectedCell.get(1) == -1) {
      hintMode = false;
    }
    if (hintMode) {
      g2d.setColor(Color.BLACK);
      int k = Math.abs(model.getBoard().size() / 2 - selectedCell.get(0));
      int x = k * 17 + 17 + (selectedCell.get(1) * 34) + this.getSize().width / 2 - model.getBoard().size() * 17;
      int y = 15 + (selectedCell.get(0) * 30) + this.getSize().height / 2 - model.getBoard().size() * 15;
      String output = "0";
      if (model.isLegalMove(selectedCell.get(0), selectedCell.get(1))) {
        output = model.getCellsToFlip(selectedCell.get(0), selectedCell.get(1)).size() + "";
      }
      g2d.drawString(output, x - 5, y + 5);
    }
  }
}