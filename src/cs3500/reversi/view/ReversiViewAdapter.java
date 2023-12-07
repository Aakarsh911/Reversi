package cs3500.reversi.view;

import cs3500.reversi.model.ViewFeaturesAdapter;
import cs3500.reversi.provider.model.Piece;
import cs3500.reversi.provider.model.ReadonlyReversiModel;
import cs3500.reversi.provider.view.gui.TPRFrame;

/**
 * Adapts a TPRFrame to a ReversiGraphicalView.
 */
public class ReversiViewAdapter implements ReversiGraphicalView {
  private final TPRFrame frame;

  private final ReadonlyReversiModel model;

  /**
   * Constructs a ReversiViewAdapter.
   *
   * @param model the model displayed in the view
   */
  public ReversiViewAdapter(ReadonlyReversiModel model) {
    this.model = model;
    this.frame = new TPRFrame(model);
  }

  @Override
  public void setVisible(boolean b) {
    frame.setVisible(b);
  }

  @Override
  public void addFeaturesListener(ViewFeatures features) {
    ViewFeaturesAdapter adapter = new ViewFeaturesAdapter(features, model.getSize());
    frame.addPlayerListener(adapter);
  }


  @Override
  public void showErrorMessage(String message) {
    frame.addMessage(message);
  }

  @Override
  public void updateTurn(String s) {
    Piece currentTurn = model.getCurrentTurn();
    if (s.equals("Your Turn")) {
      frame.updateTurn(currentTurn);
    }
  }

  @Override
  public void refresh() {
    frame.refresh();
  }
}
