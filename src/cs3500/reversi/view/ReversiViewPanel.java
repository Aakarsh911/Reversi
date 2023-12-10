package cs3500.reversi.view;

import java.awt.*;
import java.util.List;

public interface ReversiViewPanel {

  /**
   * Adds a features listener to the view.
   * @param features the features listener to add
   */
  void addFeaturesListener(ViewFeatures features);

  List<Integer> getSelectedCell();

  void paintComponent(Graphics g);

  void repaint();

  Dimension getPreferredSize();
}
