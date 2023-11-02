package cs3500.reversi.view;

import javax.swing.*;

import cs3500.reversi.model.ReadOnlyModel;

public class ReversiFrame extends JFrame implements ReversiGraphicalView {
  private final ReversiPanel panel;

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

}
