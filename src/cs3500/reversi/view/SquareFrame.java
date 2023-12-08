package cs3500.reversi.view;

import java.awt.*;

import javax.swing.*;

import cs3500.reversi.model.ReadOnlyModel;

public class SquareFrame extends JFrame implements ReversiGraphicalView {
  private final SquarePanel panel;

  private final JLabel turn;

  private final ReadOnlyModel model;

  /**
   * Constructs a ReversiFrame.
   * @param model the model to use
   */
  public SquareFrame(ReadOnlyModel model) {
    this.model = model;
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.panel = new SquarePanel(model);
    this.add(panel, BorderLayout.CENTER);
    this.turn = new JLabel("Opponent's turn | W: " + model.getWhiteScore() + ", B: "
            + model.getBlackScore());
    turn.setLabelFor(panel);
    this.add(turn, BorderLayout.NORTH);
    this.pack();
  }

  @Override
  public void addFeaturesListener(ViewFeatures features) {
    this.panel.addFeaturesListener(features);
  }

  @Override
  public void showErrorMessage(String message) {
    JOptionPane.showMessageDialog(this, message, "Message", JOptionPane.WARNING_MESSAGE);
  }

  @Override
  public void updateTurn(String s) {
    if (model.isGameOver()) {
      if (model.getBlackScore() > model.getWhiteScore()) {
        this.turn.setText("Game over! Black wins!");
      }
      else if (model.getBlackScore() < model.getWhiteScore()) {
        this.turn.setText("Game over! White wins!");
      }
      else {
        this.turn.setText("Game over! It's a tie!");
      }
    }
    else {
      this.turn.setText(s + " | W: " + model.getWhiteScore() + ", B: " + model.getBlackScore());
    }
  }

  @Override
  public void refresh() {
    this.panel.repaint();
  }
}
