package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a mock model that is used to test the cells that the strategy iterates through.
 */
public class MockStrategy3 extends BasicReversi {

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
