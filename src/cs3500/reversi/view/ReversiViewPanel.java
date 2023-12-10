package cs3500.reversi.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

/**
 * Represents a panel for the Reversi game.
 */
public abstract class ReversiViewPanel extends JPanel {

  protected final List<ViewFeatures> featuresList = new ArrayList<>();

  /**
   * Adds a ViewFeatures to the list of features.
   *
   * @param features the ViewFeatures to add
   */
  public void addFeaturesListener(ViewFeatures features) {
    featuresList.add(features);
  }

  /**
   * Gets the cell currently highlighted.
   *
   * @return the selected cell or (-1,-1) if none
   */
  public abstract List<Integer> getSelectedCell();

  /**
   * Sets the decorator for the panel.
   *
   * @param decorator the decorator to set
   */
  public void setDecorator(HintDecorator decorator) {
    // do nothing
  }

  /**
   * Sets the selected cell.
   *
   * @param row the row of the cell
   * @param col the column of the cell
   */
  public void setSelectCell(int row, int col) {
    // do nothing
  }

}
