package cs3500.reversi.model;

import java.util.List;
import java.util.Optional;

import cs3500.reversi.provider.model.Piece;
import cs3500.reversi.provider.model.ReadonlyReversiModel;
import cs3500.reversi.provider.strategy.AsManyPieces;

public class ProviderStrategy1 implements ReversiStrategy {
  private final ReversiModelAdapter model;
  private final AsManyPieces strategy;

  public ProviderStrategy1(ReversiModelAdapter model) {
    this.model = model;
    this.strategy = new AsManyPieces(this.model);
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
    List<Integer> coord = this.model.convertHex(strategy.chooseMove(new ReversiModelAdapter(model), piece));
    if (coord.isEmpty()) {
      return Optional.empty();
    }
    else {
      return Optional.of(coord);
    }
  }
}
