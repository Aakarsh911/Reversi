package cs3500.reversi.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public abstract class ReversiViewPanel extends JPanel {

  protected final List<ViewFeatures> featuresList = new ArrayList<>();

  public void addFeaturesListener(ViewFeatures features){
    featuresList.add(features);
  }

  public abstract List<Integer> getSelectedCell();

  public void setDecorator(HintDecorator decorator){
    // do nothing
  }

  public void setSelectCell(int row, int col) {
    // do nothing
  }

}
