package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a mock model that is used to test the cells that the strategy iterates through.
 */
public class MockStrategyTranscript extends BasicReversi {

  private List<List<Integer>> iterated = new ArrayList<>();

  @Override
  public boolean isLegalMove(int row, int col) {
    ArrayList<Integer> move = new ArrayList<>();
    move.add(row);
    move.add(col);
    iterated.add(move);
    return true;
  }

  @Override
  public boolean anyLegalMovesForCurrentPlayer() {
    return true;
  }

  public List<List<Integer>> getIterated() {
    return iterated;
  }

  @Override
  public ReversiModel copy() {
    return this;
  }
}
