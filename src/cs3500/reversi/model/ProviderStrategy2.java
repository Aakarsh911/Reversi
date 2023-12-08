package cs3500.reversi.model;

import java.util.List;
import java.util.Optional;

import cs3500.reversi.provider.model.Piece;
import cs3500.reversi.provider.strategy.TakeCorners;

/**
 * Adapts the TakeCorners strategy to a ReversiStrategy.
 */
public class ProviderStrategy2 implements ReversiStrategy {

  private final ReversiModelAdapter model;
  private final TakeCorners strategy;

  public ProviderStrategy2(ReversiModelAdapter model) {
    this.model = model;
    this.strategy = new TakeCorners(this.model);
  }

  @Override
  public Optional<List<Integer>> chooseMove(ReadOnlyModel model, Player player) {
    Piece piece;
    if (player.getColor().equalsIgnoreCase("black")) {
      piece = Piece.BLACK;
    }
    else {
      piece = Piece.WHITE;
    }
    List<Integer> coord = this.model.convertHex(strategy.chooseMove(new ReversiModelAdapter(model),
            piece));
    if (coord.isEmpty()) {
      return Optional.empty();
    }
    else {
      return Optional.of(coord);
    }
  }
}
