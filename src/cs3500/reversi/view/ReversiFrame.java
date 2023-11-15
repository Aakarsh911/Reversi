package cs3500.reversi.view;

import javax.swing.JFrame;
import javax.swing.KeyStroke;

import cs3500.reversi.model.ReadOnlyModel;

/**
 * Represents a JFrame for the Reversi game window.
 */
public class ReversiFrame extends JFrame implements ReversiGraphicalView {
  private final ReversiPanel panel;

  /**
   * Constructs a ReversiFrame.
   * @param model the model to use
   */
  public ReversiFrame(ReadOnlyModel model) {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.panel = new ReversiPanel(model);
    this.add(panel);
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
}
