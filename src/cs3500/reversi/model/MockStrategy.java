package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.List;

public class MockStrategy extends BasicReversi {

  private List<List<Integer>> iterated = new ArrayList<>();

  @Override
  public List<Hex> getCellsToFlip(int row, int col) {
    List<Integer> move = new ArrayList<>();
    move.add(row);
    move.add(col);
    iterated.add(move);
    return new ArrayList<>();
  }

  public List<List<Integer>> getIterated() {
    return iterated;
  }
}
