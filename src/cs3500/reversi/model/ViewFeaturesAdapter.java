package cs3500.reversi.model;

import java.util.List;

import cs3500.reversi.provider.controller.PlayerActionObserver;
import cs3500.reversi.view.ViewFeatures;

public class ViewFeaturesAdapter implements PlayerActionObserver {
  private final ViewFeatures features;
  private final ReversiModelAdapter model;

  public ViewFeaturesAdapter(ViewFeatures features, int size) {
    this.features = features;
    this.model = new ReversiModelAdapter(size);
  }

  @Override
  public void move(List<Integer> hexCoordinates) {
    List<Integer> move = model.convertHex(new HexAdapter(hexCoordinates.get(0),
            hexCoordinates.get(1), - hexCoordinates.get(0) - hexCoordinates.get(1)));
    features.move(move.get(0), move.get(1));
  }

  @Override
  public void pass() {
    features.pass();
  }
}