package cs3500.reversi.model;

import java.util.List;

import cs3500.reversi.provider.controller.PlayerActionObserver;
import cs3500.reversi.view.ViewFeatures;

/**
 * Adapts a ViewFeatures to a PlayerActionObserver.
 */
public class ViewFeaturesAdapter implements PlayerActionObserver {
  private final ViewFeatures features;
  private final ReversiModelAdapter model;

  /**
   * Constructs a ViewFeaturesAdapter.
   *
   * @param features the ViewFeatures to adapt
   * @param size     the size of the board
   */
  public ViewFeaturesAdapter(ViewFeatures features, int size) {
    this.features = features;
    this.model = new ReversiModelAdapter(size);
  }

  @Override
  public void move(List<Integer> hexCoordinates) {
    List<Integer> move = model.convertHex(new BoardPieceAdapter(hexCoordinates.get(0),
            hexCoordinates.get(1), -hexCoordinates.get(0) - hexCoordinates.get(1)));
    features.move(move.get(0), move.get(1));
  }

  @Override
  public void pass() {
    features.pass();
  }
}