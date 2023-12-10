package cs3500.reversi.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.*;

import cs3500.reversi.model.ReadOnlyModel;

public class HintDecorator extends JPanel {
  private final ReversiViewPanel panel;
  private boolean hintMode = false;
  private final ReadOnlyModel model;

  public HintDecorator(ReversiViewPanel panel, ReadOnlyModel model) {
    this.panel = panel;
    this.model = model;
    this.getInputMap().put(KeyStroke.getKeyStroke("H"), "hint");
    this.getActionMap().put("hint", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("hint");
        hintMode = !hintMode;
        repaint();
      }
    });
  }



  @Override
  public void paintComponent(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    panel.paintComponent(g);
    List<Integer> selectedCell = panel.getSelectedCell();
    if (hintMode) {
      g2d.drawString(model.getCellsToFlip(selectedCell.get(0), selectedCell.get(1)).size() + "",
              selectedCell.get(0) - 5,
              selectedCell.get(1) + 5);
    }
  }
}
