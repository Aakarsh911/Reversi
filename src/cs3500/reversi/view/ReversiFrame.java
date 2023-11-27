package cs3500.reversi.view;

import java.awt.*;

import javax.swing.*;

import cs3500.reversi.model.ReadOnlyModel;

/**
 * Represents a JFrame for the Reversi game window.
 */
public class ReversiFrame extends JFrame implements ReversiGraphicalView {
  private final ReversiPanel panel;

  private final JLabel turn;

  private final ReadOnlyModel model;

  /**
   * Constructs a ReversiFrame.
   * @param model the model to use
   */
  public ReversiFrame(ReadOnlyModel model) {
    this.model = model;
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.panel = new ReversiPanel(model);
    this.add(panel, BorderLayout.CENTER);
    this.turn = new JLabel("Opponent's turn | W: " + model.getWhiteScore() + ", B: " + model.getBlackScore());
    turn.setLabelFor(panel);
    this.add(turn, BorderLayout.NORTH);
    this.pack();
  }

  @Override
  public void addFeaturesListener(ViewFeatures features) {
    this.panel.addFeaturesListener(features);
  }

  @Override
  public void setHotKey(KeyStroke key, String featureName) {
    this.panel.getInputMap().put(key, featureName);
  }

  @Override
  public void showErrorMessage(String message) {
    JOptionPane.showMessageDialog(this, message, "Message", JOptionPane.WARNING_MESSAGE);
  }

  @Override
  public void updateTurn(String s) {
    this.turn.setText(s + " | W: " + model.getWhiteScore() + ", B: " + model.getBlackScore());
  }

  @Override
  public void refresh() {
    this.panel.repaint();
  }
}
