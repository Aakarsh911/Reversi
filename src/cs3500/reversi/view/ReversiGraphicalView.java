package cs3500.reversi.view;

import javax.swing.*;

public interface ReversiGraphicalView {
  void setVisible(boolean b);
  void addFeaturesListener(ViewFeatures features);

  void setHotKey(KeyStroke key, String featureName);
}
