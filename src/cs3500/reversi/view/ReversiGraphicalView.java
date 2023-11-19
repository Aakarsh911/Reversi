package cs3500.reversi.view;

import javax.swing.KeyStroke;

/**
 * Represents a graphical view for the Reversi game.
 */
public interface ReversiGraphicalView {
  /**
   * Sets the visibility of the view.
   * @param b true if visible, false otherwise
   */
  void setVisible(boolean b);

  /**
   * Adds a features listener to the view.
   * @param features the features listener to add
   */
  void addFeaturesListener(ViewFeatures features);

  /**
   * Sets the hot key for a feature.
   * @param key the key to press
   * @param featureName the name of the feature
   */
  void setHotKey(KeyStroke key, String featureName);


  /**
   * Shows an error message.
   * @param message the message to show
   */
  void showErrorMessage(String message);
}
